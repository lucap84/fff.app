package it.fff.persistence.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.PlaceMapper;
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
	public List<PlaceBO> getPlacesByDescription(String description) throws Exception {
		List<PlaceBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			String hqlSelect = "FROM PlaceEO WHERE nome LIKE :description OR tags LIKE :description ";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("description", "%"+description+"%");
	    	
	    	List<PlaceEO> placesEO = query.list();
	    	
	    	bos = PlaceMapper.getInstance().mapEOs2BOs(placesEO);
	    	
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during searchEvents() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}
}
