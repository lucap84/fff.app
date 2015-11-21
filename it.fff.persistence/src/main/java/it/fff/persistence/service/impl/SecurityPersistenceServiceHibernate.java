package it.fff.persistence.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.service.SecurityPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class SecurityPersistenceServiceHibernate implements SecurityPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(SecurityPersistenceServiceHibernate.class);

	@Override
	public UpdateResultBO logout(int userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO generateVerficationCode(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Map<String, String>> retrieveClientSecrets() throws Exception {
		logger.info("retrieving client secrets");
		
		Map<String, Map<String, String>> secrets = new HashMap<String, Map<String, String>>();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	      try{
	    	  String hql = "SELECT S.account.id, S.deviceId, S.sharedKey  FROM SessionEO S WHERE S.isValidSession = 1";	    	  
	    	  Query query = session.createQuery(hql);
	    	  List<Object[]> results = query.list();
	    	  
	    	  String userId = null;
	    	  String deviceId = null;
	    	  String sharedKey = null;
	    	  
	    	  for (Object[] row: results) {
	    		  userId = String.valueOf((Integer)row[0]);
	    		  deviceId = (String)row[1];
	    		  sharedKey = (String)row[2];
	    		  
	    		  Map<String, String> userSecrets = secrets.get(userId);
	    		  if(userSecrets==null){
	    			  userSecrets = new HashMap<String,String>();
	    		  }
	    		  userSecrets.put(deviceId, sharedKey);
	    		  secrets.put(userId, userSecrets);
	    		}
	      }catch (HibernateException e) {
	         e.printStackTrace();
	         throw new Exception("HibernateException during retrieveClientSecrets() ",e);
	      }finally {
	         session.close(); 
	      }			
		
		logger.info("user registered");
		
		return secrets;
	}

}
