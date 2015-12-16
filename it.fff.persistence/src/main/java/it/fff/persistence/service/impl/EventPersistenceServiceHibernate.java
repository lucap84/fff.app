package it.fff.persistence.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventCategoryEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.eo.MessageEO;
import it.fff.business.common.eo.MessageStandardEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.persistence.init.TypologicalLoader;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class EventPersistenceServiceHibernate implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceHibernate.class);
	
	@Override
	public EventBO retrieveEvent(int eventId) throws Exception{

		EventBO bo = null;
		EventEO eo = null;
				
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	      try{
	    	 eo = (EventEO) session.get(EventEO.class, eventId);
	      }catch (HibernateException e) {
	         e.printStackTrace();
	         throw new Exception("HibernateException during retrieveEvent() ",e);
	      }finally {
	         session.close(); 
	      }	        
	      bo = EventMapper.getInstance().mapEO2BO(eo);
		return bo;
	}

	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws Exception {
		logger.info("annullo evento...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    try{
	    	String hqlSelectOrganizerAttendance = "FROM AttendanceEO A WHERE A.event.id = :eventId AND A.utente.id = :organizerId AND isOrganizer=1 AND A.isValid = 1";
	    	Query querySelectOrganizerAttendance = session.createQuery(hqlSelectOrganizerAttendance);
	    	querySelectOrganizerAttendance.setParameter("eventId", eventId);
	    	querySelectOrganizerAttendance.setParameter("organizerId", organizerId);
	    	
	    	AttendanceEO organizerAttendance =(AttendanceEO)querySelectOrganizerAttendance.uniqueResult();
	    	if(organizerAttendance==null){
	    		throw new HibernateException("Evento dell organizzatore da annullare non trovato");
	    	}

	    	result = this.updateEventState(eventId, EventStateEnum.CANCELED);
			
	    }catch (HibernateException e) {
	    	e.printStackTrace();
	        throw new Exception("HibernateException during cancelEvent() ",e);
	    }finally {
	    	session.close(); 
	    }			
		logger.info("...eveno annullato");
		return result;
	}

	@Override
	public WriteResultBO createEvent(EventBO eventBO) throws Exception {
		logger.info("creating event");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Integer eventId = null;
	      try{
	    	EventEO eventEO = EventMapper.getInstance().mergeBO2EO(eventBO, null);
			
	    	Integer eventStateId = TypologicalLoader.eventStateEnum2ID.get(eventBO.getStato());

	    	tx = session.beginTransaction();
	    	
	    	//Rendo managed tutti gli oggetti collegati all'evento
	    	eventEO.setLocation((PlaceEO) session.load(PlaceEO.class, eventEO.getLocation().getId()));
	    	eventEO.setStato((EventStateEO) session.load(EventStateEO.class, eventStateId));
	    	eventEO.setCategoria((EventCategoryEO) session.load(EventCategoryEO.class, eventEO.getCategoria().getId()));
	    	
	    	//ora posso salvare l'evento
			eventId = (Integer)session.save(eventEO);
			
			//salvo anche le sue partecipazioni ora che ho l'ID evento (non sono salvate in cascade)
			for (AttendanceEO a : eventEO.getPartecipazioni()) {
				Integer attStateId = TypologicalLoader.attendanceStateEnum2ID.get(AttendanceStateEnum.valueOf(a.getStato().getNome()));
				a.getStato().setId(attStateId);
				a.setEvent(eventEO);
				session.save(a);
			}
			
			
	        tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during registerUser() ",e);
	      }finally {
	         session.close(); 
	      }			
		
		logger.info("user registered");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(eventId);
		resultBO.setAffectedRecords(1);
		
		return resultBO;
	}

	@Override
	public WriteResultBO createEventMessage(int attendanceId, String message) throws Exception {
		logger.info("post messaggio custom...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String dataCreazione = DHSecureConfiguration.DATE_FORMATTER.format(new Date());
	    	
	    	tx = session.beginTransaction();

	    	MessageEO messageEO = new MessageEO();
	    	messageEO.setText(message);
	    	messageEO.setDataCreazione(dataCreazione);
	    	messageEO.setMsgStd(null);
	    	
	    	AttendanceEO attendanceEO = (AttendanceEO)session.get(AttendanceEO.class, attendanceId);
	    	EventEO eventEO = attendanceEO.getEvent();
	    	messageEO.setAttendance(attendanceEO);
	    	messageEO.setEvent(eventEO);

	    	session.save(messageEO);
			
	    	tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during createEventMessage() ",e);
	      }finally {
	         session.close(); 
	      }	
	    
		logger.info("...post messaggio custom");
		return result;
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception {
		logger.info("post messaggio standard...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String dataCreazione = DHSecureConfiguration.DATE_FORMATTER.format(new Date());
	    	
	    	tx = session.beginTransaction();

	    	MessageEO messageEO = new MessageEO();
	    	messageEO.setDataCreazione(dataCreazione);
	    	messageEO.setMsgStd(null);
	    	
	    	MessageStandardEO msgStndEO = (MessageStandardEO)session.get(MessageStandardEO.class, stdMsgId);
	    	messageEO.setText(msgStndEO.getStandardText());
	    	
	    	AttendanceEO attendanceEO = (AttendanceEO)session.get(AttendanceEO.class, attendanceId);
	    	EventEO eventEO = attendanceEO.getEvent();
	    	messageEO.setAttendance(attendanceEO);
	    	messageEO.setEvent(eventEO);

	    	session.save(messageEO);
			
	    	tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during createEventMessage() ",e);
	      }finally {
	         session.close(); 
	      }	
	    
		logger.info("...post messaggio standard");
		return result;
	}

	@Override
	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws Exception {
		logger.info("addFeedback...");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();

	    	  AttendanceEO eo = (AttendanceEO) session.get(AttendanceEO.class, new Integer(attendanceId));
				
	    	  switch (feedback) {
				case POSITIVE:
					eo.setPositiveFeedback(true);
					break;
				case NEGATIVE:
					eo.setPositiveFeedback(false);
					break;
				case UNKNOW:
					eo.setPositiveFeedback(null);
					break;				
				default:
					break;
				}
			
			session.update(eo);
			
	        tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during addFeedback() ",e);
	      }finally {
	         session.close(); 
	      }			
		
		logger.info("...addFeedback");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(attendanceId);
		resultBO.setAffectedRecords(1);
		
		return resultBO;
	}

	@Override
	public WriteResultBO createAttandance(AttendanceBO bo) throws Exception {
		logger.info("creating attendance (join event)");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Integer eventId = null;
	      try{
	    	AttendanceEO attendanceEO = AttendanceMapper.getInstance().mergeBO2EO(bo, null);
	    	  
			tx = session.beginTransaction();
			eventId = (Integer)session.save(attendanceEO); 
	        tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during createAttandance() ",e);
	      }finally {
	         session.close(); 
	      }			
		
		logger.info("attendance created");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(eventId);
		resultBO.setAffectedRecords(1);
		
		return resultBO;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws Exception {
		List<EventBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			String hqlSelect = "FROM EventEO e JOIN AttendanceEO a WHERE a.utente.id = :userId AND a.isValid = 1 ";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("userId", userId);
	    	
	    	List<EventEO> eventsEO = query.list();
	    	
	    	bos = EventMapper.getInstance().mapEOs2BOs(eventsEO);
	    	
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during searchEvents() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(	double gpsLatFrom, 
										double gpsLatTo, 
										double gpsLongFrom,
										double gpsLongTo, 
										int idCategoria, 
										int minPartecipanti)	throws Exception {

		List<EventBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			String hqlSelect = "SELECT FROM EventEO e JOIN PlaceEO p JOIN CategoriaEventoEO c WHERE c.id = :idCategoria AND p.gpsLat BETWEEN :gpsLatFrom AND :gpsLatTo AND p.gpsLong BETWEEN :gpsLongFrom AND :gpsLongTo";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("idCategoria", idCategoria);
	    	query.setParameter("gpsLatFrom", gpsLatFrom);
	    	query.setParameter("gpsLatTo", gpsLatTo);
	    	query.setParameter("gpsLongFrom", gpsLongFrom);
	    	query.setParameter("gpsLongTo", gpsLongTo);
	    	
	    	List<Object[]> list = query.list();
	    	list.size();
			
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during searchEvents() ",e);
	     }finally {
	        session.close(); 
	     }	
		return bos;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO updateEventState(int eventId, EventStateEnum state) throws Exception {
		logger.info("modifico stato evento...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();

	    	Integer eventStateId = TypologicalLoader.eventStateEnum2ID.get(state);
	    	
	    	String hqlUpdateEvent = "UPDATE EventEO set stato.id = :eventStateId  WHERE id=:eventId";	    	  
			Query queryUpdateEvent = session.createQuery(hqlUpdateEvent);
			queryUpdateEvent.setParameter("eventStateId", eventStateId);
			queryUpdateEvent.setParameter("eventId", eventId);
			
			int recordUpdated = queryUpdateEvent.executeUpdate();
			
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			if(recordUpdated>0){
				result.setWrittenKey(eventId);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
			
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during updateEventState() ",e);
	    }finally {
	    	session.close(); 
	    }			
		logger.info("...stato evento modificato");
		return result;
	}

}
