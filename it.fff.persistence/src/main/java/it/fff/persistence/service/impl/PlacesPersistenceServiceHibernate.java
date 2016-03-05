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

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.KeywordEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.PlaceMapper;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.persistence.service.PlacesPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class PlacesPersistenceServiceHibernate implements PlacesPersistenceService {

	private static final Logger logger = LogManager.getLogger(PlacesPersistenceServiceHibernate.class);

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO bo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws Exception {
		List<PlaceBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	tx = session.beginTransaction();
			
	    	String hqlSelect = "FROM KeywordEO WHERE token = :token";
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("token",token);
	    	
	    	KeywordEO keyword = (KeywordEO)query.uniqueResult();
	    	List<PlaceEO> relatedPlaces = keyword.getRelatedPlaces();
	    	
	    	tx.commit();
	    	
	    	bos = PlaceMapper.getInstance().mapEOs2BOs(relatedPlaces);
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during searchEvents() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public WriteResultBO saveOrUpdatePlace(PlaceBO place, String token) throws Exception {
		logger.info("salvo Place e/o token...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();

	    	int placeId = 0;
//	    	Query querySelectOrganizerAttendance = session.getNamedQuery(Constants.QY_GET_ATTENDANCE_BY_EVENT_ORGANIZER);
//	    	querySelectOrganizerAttendance.setParameter("eventId", eventId);
//	    	querySelectOrganizerAttendance.setParameter("organizerId", organizerId);
//	    	
//	    	AttendanceEO organizerAttendance =(AttendanceEO)querySelectOrganizerAttendance.uniqueResult();
//	    	if(organizerAttendance==null){
//	    		throw new HibernateException("Evento dell organizzatore da annullare non trovato");
//	    	}
//
//	    	Integer eventStateId = EventStateEnum.CANCELED.getId();
//	    	String dataAggiornamento = Constants.DATE_FORMATTER.format(new Date());
//	    	
//	    	String hqlUpdateEvent = "UPDATE EventEO set stato.id = :eventStateId, dataAggiornamento = :dataAggiornamento  WHERE id=:eventId";	    	  
//			Query queryUpdateEvent = session.createQuery(hqlUpdateEvent);
//			queryUpdateEvent.setParameter("eventStateId", eventStateId);
//			queryUpdateEvent.setParameter("dataAggiornamento", dataAggiornamento);
//			queryUpdateEvent.setParameter("eventId", eventId);
//			
//			int recordUpdated = queryUpdateEvent.executeUpdate();
			
			tx.commit();
			
			result.setAffectedRecords(1);
			result.setSuccess(true);
			result.setWrittenKey(placeId);
	    	
			
	    }catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during saveOrUpdatePlace() ",e);
	    }finally {
	    	session.close(); 
	    }			
	    logger.info("...Place e/o token salvati");
		return result;
	}

}
