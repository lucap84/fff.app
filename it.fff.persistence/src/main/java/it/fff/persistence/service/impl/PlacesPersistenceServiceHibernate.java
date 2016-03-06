package it.fff.persistence.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.KeywordEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.KeywordMapper;
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
	    	Set<PlaceEO> relatedPlaces = keyword.getRelatedPlaces();
	    	
	    	tx.commit();

	    	bos = PlaceMapper.getInstance().mapEOs2BOs(new ArrayList<PlaceEO>(relatedPlaces));
	    	
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
	public WriteResultBO saveOrUpdatePlace(PlaceBO placeBO, String token) throws Exception {
		logger.info("salvo Place e/o token associato...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer keywordId = null;
		Integer placeId = null;
	    try{
	    	tx = session.beginTransaction();

	    	String hqlSelectKeyword = "FROM KeywordEO WHERE token = :token";
	    	Query querySelectKeyword = session.createQuery(hqlSelectKeyword);
	    	querySelectKeyword.setParameter("token",token);
	    	
	    	KeywordEO keywordEO = (KeywordEO)querySelectKeyword.uniqueResult();
	    	
	    	if(keywordEO==null){
	    		keywordEO = KeywordMapper.getInstance().mergeBO2EO(token, null, session);
	    		keywordId = (Integer)session.save(keywordEO);
	    	}
	    	else{
	    		keywordId = keywordEO.getId();
	    	}
	    	
	    	String hqlSelectPlace = "FROM PlaceEO WHERE placeKey = :placeKey";
	    	Query querySelectPlace = session.createQuery(hqlSelectPlace);
	    	querySelectPlace.setParameter("placeKey",placeBO.getPlaceKey());
	    	
	    	PlaceEO placeEO = (PlaceEO)querySelectPlace.uniqueResult();
	    	
	    	if(placeEO==null){
	    		placeEO = PlaceMapper.getInstance().mergeBO2EO(placeBO, null, session);
	    		placeEO.getKeywords().add(keywordEO);
	    		
	    		placeId = (Integer)session.save(placeEO);
	    	}
	    	else{
	    		placeId = placeEO.getId();
	    	}	    	
	    	
	    	//Ora sia la keyword che il place esistono su DB anche se non erano presenti precedentemente
	    	//devo adesso legarli da un mapping
			
	    	keywordEO.getRelatedPlaces().add(placeEO);
	    	placeEO.getKeywords().add(keywordEO);
	    	
	    	session.update(keywordEO);
	    	session.update(placeEO);
	    	
			tx.commit();
			
	    }catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during saveOrUpdatePlace() ",e);
	    }finally {
	    	session.close(); 
	    }			
	    logger.info("...Place e/o token associato salvati");

		result.setAffectedRecords(1);
		result.setSuccess(true);
		result.setWrittenKey(placeId);
		
	    return result;
	}

}
