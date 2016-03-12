package it.fff.persistence.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.CityEO;
import it.fff.business.common.eo.KeywordEO;
import it.fff.business.common.eo.NationEO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.mapper.CityMapper;
import it.fff.business.common.mapper.KeywordMapper;
import it.fff.business.common.mapper.NationMapper;
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
	public Set<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws Exception {
		Set<PlaceBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	tx = session.beginTransaction();
			
	    	String hqlSelect = "FROM KeywordEO WHERE token = :token";
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("token",token);
	    	
	    	KeywordEO keyword = (KeywordEO)query.uniqueResult();
	    	
	    	if(keyword!=null){
	    		Set<PlaceEO> relatedPlaces = keyword.getRelatedPlaces();
	    		bos = new HashSet<PlaceBO>(PlaceMapper.getInstance().mapEOs2BOs(new ArrayList<PlaceEO>(relatedPlaces)));
	    	}
	    	
	    	tx.commit();

	    	
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
	    		
	    		//verifico se almeno la citta relativa esiste
	    		String cityName = placeBO.getCity().getNome();
	    		String nationCode = placeBO.getCity().getNazione().getInternationalCodeAplha3();
	    		if(nationCode==null || "".equals(nationCode)){
	    			nationCode = placeBO.getCity().getNazione().getInternationalCodeAplha2();
	    		}
	    		
	    		CityBO existingCityBO = null;
	    		NationBO existingNationBO = null;
	    		
	    		existingCityBO = this.getCityByName(cityName, nationCode);
	    		
	    		if(existingCityBO==null){
	    			existingNationBO = this.getNationByInternationalCode(nationCode);
	    			
	    			if(existingNationBO==null){
	    				logger.debug("non esiste ne' citta ne' nazione: verranno creati entrambi");
	    			}
	    			else{
	    				//Se la citta quindi non esiste ma in caso esista almeno la Nazione la imposta sulla nuova citta'
	    				placeBO.getCity().setNazione(existingNationBO);
	    			}
	    			
	    		}else{
	    			placeBO.setCity(existingCityBO);
	    		}

	    		//creo l'oggetto Entity: se la citta esisteva sara' ora managed e mappata dentro al place (insieme alla Nation)
	    		//se la citta' non esisteva, avra' tutti i dati e verra' salvata in cascade
	    		placeEO = PlaceMapper.getInstance().mergeBO2EO(placeBO, null, session);
	    		session.save(placeEO);
	    	}
	    	else{
	    		//aggiorno il place esistente
	    		placeEO = PlaceMapper.getInstance().mergeBO2EO(placeBO, placeEO, session);
	    	}	    	
	    	
	    	placeId = placeEO.getId();
	    	
	    	//Ora sia la keyword che il place esistono su DB anche se non erano presenti precedentemente
	    	//devo adesso legarli da un mapping
			
	    	keywordEO.getRelatedPlaces().add(placeEO);
	    	placeEO.getKeywords().add(keywordEO);
	    	
	    	session.update(keywordEO);
	    	session.update(placeEO);
	    	
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
	    logger.info("...Place e/o token associato salvati");


		
	    return result;
	}

	@Override
	public CityBO getCityByName(String cityName, String nationCode) throws Exception {
		CityBO bo = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	tx = session.beginTransaction();
			
	    	String hqlSelect = "FROM CityEO c WHERE c.nome = :cityName AND (c.nazione.internationalCodeAplha2 = :nationCode OR c.nazione.internationalCodeAplha3 = :nationCode)";
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("cityName",cityName);
	    	query.setParameter("nationCode",nationCode);
	    	
	    	CityEO cityEO = (CityEO)query.uniqueResult();
	    	
	    	tx.commit();

	    	bo = CityMapper.getInstance().mapEO2BO(cityEO);
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during getCityByName() ",e);
	     }finally {
	        session.close(); 
	     }
		return bo;
	}
	
	@Override
	public NationBO getNationByInternationalCode(String nationCode) throws Exception {
		NationBO bo = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	tx = session.beginTransaction();
			
	    	String hqlSelect = "FROM NationEO n WHERE (n.internationalCodeAplha2 = :nationCode OR n.internationalCodeAplha3 = :nationCode)";
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("nationCode",nationCode);
	    	
	    	NationEO nationEO = (NationEO)query.uniqueResult();
	    	
	    	tx.commit();

	    	bo = NationMapper.getInstance().mapEO2BO(nationEO);
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during getNationByInternationalKey() ",e);
	     }finally {
	        session.close(); 
	     }
		return bo;
	}	

}
