package it.fff.persistence.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.mapper.EventMapper;
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
	      bo = EventMapper.mapEO2BO(eo);
		return bo;
	}

	@Override
	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO cancelEvent(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createEvent(EventBO bo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createEventMessage(int attendanceId, String message) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO addFeedback(AttendanceBO bo, boolean isPositiveFeedback) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createStandardEventMessage(AttendanceBO bo) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	    	
	    	bos = EventMapper.mapEOs2BOs(eventsEO);
	    	
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

}
