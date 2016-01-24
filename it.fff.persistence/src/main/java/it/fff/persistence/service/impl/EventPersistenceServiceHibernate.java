package it.fff.persistence.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
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
import it.fff.business.common.mapper.MessageMapper;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
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
		Transaction tx = null;
	      try{
	    	 tx = session.beginTransaction();
	    	 
	    	 eo = (EventEO) session.get(EventEO.class, eventId);
	    	 if(eo!=null){
	    		 Hibernate.initialize(eo);
	    	 }
	    	 
	    	 tx.commit();
	      }catch (HibernateException e) {
	    	 if(tx!=null)tx.rollback();
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
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
//	    	String hqlSelectOrganizerAttendance = "FROM AttendanceEO A WHERE A.event.id = :eventId AND A.utente.id = :organizerId AND isOrganizer=1 AND A.isValid = 1";
//	    	Query querySelectOrganizerAttendance = session.createQuery(hqlSelectOrganizerAttendance);
	    	Query querySelectOrganizerAttendance = session.getNamedQuery(Constants.QY_GET_ATTENDANCE_BY_EVENT_ORGANIZER);
	    	querySelectOrganizerAttendance.setParameter("eventId", eventId);
	    	querySelectOrganizerAttendance.setParameter("organizerId", organizerId);
	    	
	    	AttendanceEO organizerAttendance =(AttendanceEO)querySelectOrganizerAttendance.uniqueResult();
	    	if(organizerAttendance==null){
	    		throw new HibernateException("Evento dell organizzatore da annullare non trovato");
	    	}

	    	Integer eventStateId = EventStateEnum.CANCELED.getId();
	    	String dataAggiornamento = Constants.DATE_FORMATTER.format(new Date());
	    	
	    	String hqlUpdateEvent = "UPDATE EventEO set stato.id = :eventStateId, dataAggiornamento = :dataAggiornamento  WHERE id=:eventId";	    	  
			Query queryUpdateEvent = session.createQuery(hqlUpdateEvent);
			queryUpdateEvent.setParameter("eventStateId", eventStateId);
			queryUpdateEvent.setParameter("dataAggiornamento", dataAggiornamento);
			queryUpdateEvent.setParameter("eventId", eventId);
			
			int recordUpdated = queryUpdateEvent.executeUpdate();
			
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			result.setSuccess(recordUpdated>0);
			result.setWrittenKey(eventId);
	    	
			
	    }catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
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
				tx = session.beginTransaction();
				
				EventEO eventEO = EventMapper.getInstance().mergeBO2EO(eventBO, null, session);
				
				Integer eventStateId = eventBO.getStato().getId();
	    	
				//Rendo managed tutti gli oggetti collegati all'evento
				eventEO.setLocation((PlaceEO) session.load(PlaceEO.class, eventEO.getLocation().getId()));
				eventEO.setStato((EventStateEO) session.load(EventStateEO.class, eventStateId));
				eventEO.setCategoria((EventCategoryEO) session.load(EventCategoryEO.class, eventEO.getCategoria().getId()));
				
				//ora posso salvare l'evento
				eventId = (Integer)session.save(eventEO);
				
				//salvo anche le sue partecipazioni ora che ho l'ID evento (non sono salvate in cascade)
				for (AttendanceEO a : eventEO.getPartecipazioni()) {
					Integer attStateId = AttendanceStateEnum.valueOf(a.getStato().getNome()).getId();
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
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer messageId = null;
	    try{
	    	String dataCreazione = Constants.DATE_FORMATTER.format(new Date());
	    	
	    	tx = session.beginTransaction();

	    	MessageEO messageEO = new MessageEO();
	    	messageEO.setText(message);
	    	messageEO.setDataCreazione(dataCreazione);
	    	messageEO.setMsgStd(null);
	    	
	    	AttendanceEO attendanceEO = (AttendanceEO)session.get(AttendanceEO.class, attendanceId);
	    	
	    	if(attendanceEO!=null){
		    	//Check attendee is the organizer
		    	if(!attendanceEO.isOrganizer()){
		    		if (tx!=null) tx.rollback();
		    		throw new Exception("User is not the organizer!");
		    	}
		    	
		    	EventEO eventEO = attendanceEO.getEvent();
		    	messageEO.setAttendance(attendanceEO);
		    	messageEO.setEvent(eventEO);
	
		    	messageId = (Integer)session.save(messageEO);
	    	}
	    	else{
	    		throw new Exception("Attendance ("+attendanceId+") not found");
	    	}
			
	    	tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();	         
	         e.printStackTrace();
	         throw new Exception("HibernateException during createEventMessage() ",e);
	      }finally {
	         session.close(); 
	      }	
	    
		logger.info("...post messaggio custom");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(messageId);
		resultBO.setAffectedRecords(1);		
		return resultBO;
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception {
		logger.info("post messaggio standard...");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer messageId = null;
	    try{
	    	String dataCreazione = Constants.DATE_FORMATTER.format(new Date());
	    	
	    	tx = session.beginTransaction();

	    	MessageStandardEO msgStndEO = (MessageStandardEO)session.get(MessageStandardEO.class, stdMsgId);
	    	AttendanceEO attendanceEO = (AttendanceEO)session.get(AttendanceEO.class, attendanceId);
	    	
	    	if(msgStndEO!=null && attendanceEO!=null){
		    	EventEO eventEO = attendanceEO.getEvent();
	
		    	MessageEO messageEO = new MessageEO();
		    	messageEO.setMsgStd(msgStndEO);
		    	messageEO.setDataCreazione(dataCreazione);
		    	messageEO.setText(null);
		    	messageEO.setAttendance(attendanceEO);
		    	messageEO.setEvent(eventEO);
	
		    	messageId = (Integer)session.save(messageEO);
	    	}
	    	else{
	    		throw new Exception("Attendance ("+attendanceId+") e/o Standard message ("+stdMsgId+")non trovati");
	    	}
	    	tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during createEventMessage() ",e);
	      }finally {
	         session.close(); 
	      }	
	    
		logger.info("...post messaggio standard");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(messageId);
		resultBO.setAffectedRecords(1);			
		return resultBO;
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
			  
	    	  if(eo!=null){
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
	    	  }
	    	  else{
	    		  throw new Exception("Attendance ("+attendanceId+") non trovato");
	    	  }
			
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
	    Integer attendaceId = null;
	      try{
	    	tx = session.beginTransaction();

	    	AttendanceEO attendanceEO = AttendanceMapper.getInstance().mergeBO2EO(bo, null, session);
	    	
	    	String dataCreazione = Constants.DATE_FORMATTER.format(new Date());
	    	attendanceEO.setDataCreazione(dataCreazione);
	    	
			attendaceId = (Integer)session.save(attendanceEO); 
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
		resultBO.setWrittenKey(attendaceId);
		resultBO.setAffectedRecords(1);
		
		return resultBO;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws Exception {
		List<AttendanceBO> bos = null;
		List<AttendanceEO> eos = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	 tx = session.beginTransaction();
			String hqlSelect = "FROM AttendanceEO a WHERE a.event.id = :eventId AND a.isValid = 1 ";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("eventId", eventId);
	    	
	    	eos = query.list();
	    	
	    	tx.commit();
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAttendancesByEvent() ",e);
	     }finally {
	        session.close(); 
	     }

		bos = AttendanceMapper.getInstance().mapEOs2BOs(eos);
		return bos;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws Exception {
		List<EventBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();

			String hqlSelect = "SELECT ev FROM EventEO AS ev WHERE ev.id IN(SELECT e.id FROM AttendanceEO AS a JOIN a.event AS e WHERE a.utente.id = :userId AND a.isValid = 1 AND (e.stato.id= :eventStateActive OR e.stato.id= :eventStateOngoing))";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("userId", userId);
	    	query.setParameter("eventStateActive", EventStateEnum.ACTIVE.getId());
	    	query.setParameter("eventStateOngoing", EventStateEnum.ONGOING.getId());
	    	
	    	List<EventEO> eventsEO = query.list();
	    	
	    	for (EventEO eventEO : eventsEO) { //recupero e inizializzo le partecipazioni
				for (AttendanceEO attendanceEO :  eventEO.getPartecipazioni()) {
			    	 if(attendanceEO!=null){
			    		 Hibernate.initialize(attendanceEO);
			    	 }
				}
			}

	    	tx.commit();
	    	
	    	bos = EventMapper.getInstance().mapEOs2BOs(eventsEO);
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during getEventsByUser() ",e);
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
		List<EventEO> eos = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	tx = session.beginTransaction();
			
	    	Integer stateActiveId = EventStateEnum.ACTIVE.getId();
			Integer stateOngoingId = EventStateEnum.ONGOING.getId();
			
			String hqlSelect = "SELECT e FROM EventEO AS e JOIN e.location AS p JOIN e.categoria AS c WHERE c.id = :idCategoria AND (e.stato.id= :stateActiveId OR e.stato.id= :stateOngoingId) AND p.gpsLat BETWEEN :gpsLatFrom AND :gpsLatTo AND p.gpsLong BETWEEN :gpsLongFrom AND :gpsLongTo";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("idCategoria", idCategoria);
	    	query.setParameter("stateActiveId", stateActiveId);
	    	query.setParameter("stateOngoingId", stateOngoingId);
	    	query.setParameter("gpsLatFrom", gpsLatFrom);
	    	query.setParameter("gpsLatTo", gpsLatTo);
	    	query.setParameter("gpsLongFrom", gpsLongFrom);
	    	query.setParameter("gpsLongTo", gpsLongTo);
	    	
	    	eos = query.list();
			
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during searchEvents() ",e);
	     }finally {
	        session.close(); 
	     }
		
		EventMapper eventMapper = EventMapper.getInstance();
		bos = eventMapper.mapEOs2BOs(eos);
		
		return bos;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws Exception {
		List<MessageBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();

			String hqlSelect = "SELECT m FROM MessageEO AS m WHERE m.event.id= :eventId";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("eventId", eventId);
	    	
	    	List<MessageEO> messagesEO = query.list();

	    	tx.commit();
	    	
	    	bos = MessageMapper.getInstance().mapEOs2BOs(messagesEO);
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during getEventMessages() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public WriteResultBO updateEventState(int eventId, EventStateEnum eventState) throws Exception {
		logger.info("modifico stato evento...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();

	    	Integer eventStateId = eventState.getId();
	    	
	    	String hqlUpdateEvent = "UPDATE EventEO set stato.id = :eventStateId  WHERE id=:eventId";	    	  
			Query queryUpdateEvent = session.createQuery(hqlUpdateEvent);
			queryUpdateEvent.setParameter("eventStateId", eventStateId);
			queryUpdateEvent.setParameter("eventId", eventId);
			
			int recordUpdated = queryUpdateEvent.executeUpdate();
			
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			result.setSuccess(recordUpdated>0);
			result.setWrittenKey(eventId);
			
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
