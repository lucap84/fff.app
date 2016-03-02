package it.fff.persistence.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.PlaceMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
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
		Transaction tx = null;
	      try{
	    	tx = session.beginTransaction();
			
	    	String hqlSelect = "FROM PlaceEO WHERE nome LIKE :description OR tags LIKE :description ";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("description", "%"+description+"%");
	    	
	    	List<PlaceEO> placesEO = query.list();
	    	
	    	tx.commit();
	    	
	    	bos = PlaceMapper.getInstance().mapEOs2BOs(placesEO);
	    	
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
	public List<PlaceBO> getPlacesByDescriptionFromExternalService(String description) throws Exception {
		
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String apiKEY = confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_APIKEY);
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKEY);
		
//		GeocodingResult[] results =  GeocodingApi.geocode(context,description).await();
		GeocodingApiRequest geocodingRequest = GeocodingApi.newRequest(context);
		GeocodingResult[] results = geocodingRequest.address(description).await();
		
		System.out.println(results[0].formattedAddress);
		
		return null;
	}
}
