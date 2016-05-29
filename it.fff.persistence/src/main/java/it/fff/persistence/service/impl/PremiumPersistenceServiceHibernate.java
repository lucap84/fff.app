package it.fff.persistence.service.impl;


import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.business.common.mapper.SubscriptionMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.persistence.service.PremiumPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class PremiumPersistenceServiceHibernate implements PremiumPersistenceService{

	private static final Logger logger = LogManager.getLogger(PremiumPersistenceServiceHibernate.class);
	
	@Override
	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws Exception {
		logger.info("upgradeToPremium...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer subscriptionId = null;
	    try{
	    	tx = session.beginTransaction();

	    	//TODO check se utente ha già un premium nello stesso intervallo temporale (in caso sarebbe meglioaggiungere il premium a partire dalla data fine di quello corrente)
	    	SubscriptionMapper mapper = SubscriptionMapper.getInstance();
	    	SubscriptionEO subscriptionEO = mapper.mergeBO2EO(bo, null, session);
	    	subscriptionId = (Integer)session.save(subscriptionEO);
			
	    	tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during upgradeToPremium() ",e);
	      }finally {
	         session.close(); 
	      }	
	    
		logger.info("...upgradeToPremium");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(subscriptionId);
		resultBO.setAffectedRecords(1);		
		return result;
	}

}
