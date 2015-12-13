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
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
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
	    	Integer eventStateId = TypologicalLoader.eventStateEnum2ID.get(eventBO.getStato());
	    	EventEO eventEO = EventMapper.getInstance().mergeBO2EO(eventBO, null);
	    	EventStateEO stato = eventEO.getStato();
			stato.setId(eventStateId);
			
	    	tx = session.beginTransaction();//TODO controlla se salva anche le partecipazioni (ho messo un cascade hibernate su EventEO)
	    	
	    	PlaceEO location = eventEO.getLocation();
	    	session.saveOrUpdate(location);
	    	session.saveOrUpdate(stato);

	    	//	    	Query qGetPlace = session.createQuery("FROM PlaceEO WHERE id=:placeId");
//	    	qGetPlace.setParameter("placeId", eventEO.getLocation().getId());
//	    	PlaceEO placeEO = (PlaceEO)qGetPlace.uniqueResult();
//	    	eventEO.setLocation(placeEO);
	    	
			eventId = (Integer)session.save(eventEO);
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO addFeedback(AttendanceBO bo, boolean isPositiveFeedback) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
