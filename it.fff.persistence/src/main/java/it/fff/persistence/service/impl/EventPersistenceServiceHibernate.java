package it.fff.persistence.service.impl;

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
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.UserMapper;
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
	public WriteResultBO cancelAttendance(int eventId, int attendanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO cancelEvent(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO createEvent(EventBO eventBO) throws Exception {
		logger.info("creating event");
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//	    Transaction tx = null;
//	    Integer id = null;
//	      try{
//	    	  EventEO eventEO = EventMapper.getInstance().mergeBO2EO(eventBO,eventEO);
//	    	  
//			tx = session.beginTransaction();
//			id = (Integer)session.save(eventEO); 
//			 
//	         
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace();
//	         throw new Exception("HibernateException during registerUser() ",e);
//	      }finally {
//	         session.close(); 
//	      }			
//		
//		logger.info("user registered");
//		WriteResultBO resultBO = new WriteResultBO();
//		resultBO.setSuccess(true);
//		resultBO.setWrittenKeyid);
//		resultBO.setNumRecordsCreated(1);
		
		return null;
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
	public WriteResultBO createStandardEventMessage(AttendanceBO bo) throws Exception {
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

}
