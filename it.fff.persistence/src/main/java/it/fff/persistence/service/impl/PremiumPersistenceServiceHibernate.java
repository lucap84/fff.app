package it.fff.persistence.service.impl;


import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.MessageEO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.SubscriptionMapper;
import it.fff.business.common.util.Constants;

import java.util.Date;

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
	    try{
	    	tx = session.beginTransaction();

	    	SubscriptionMapper mapper = SubscriptionMapper.getInstance();
	    	SubscriptionEO subscriptionEO = mapper.mergeBO2EO(bo, null, session);

	    	//RendoManaged i campi Entity di Subscription
	    	subscriptionEO.setAbbonato((UserEO)session.load(UserEO.class, subscriptionEO.getAbbonato().getId()));
	    	subscriptionEO.setTipo((SubscriptionTypeEO)session.load(SubscriptionTypeEO.class, subscriptionEO.getTipo().getId()));
	    	
	    	session.save(subscriptionEO);
			
	    	tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during upgradeToPremium() ",e);
	      }finally {
	         session.close(); 
	      }	
	    
		logger.info("...upgradeToPremium");
		return result;
	}

}
