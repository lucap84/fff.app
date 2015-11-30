package it.fff.persistence.service.impl;


import java.util.Date;
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

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.SessionEO;
import it.fff.business.common.mapper.SessionMapper;
import it.fff.business.common.mapper.UserMapper;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.persistence.service.SecurityPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class SecurityPersistenceServiceHibernate implements SecurityPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(SecurityPersistenceServiceHibernate.class);

	@Override
	public UpdateResultBO logout(int userId, String deviceId) throws Exception {
		logger.info("logout client and device...");
		
		UpdateResultBO result = new UpdateResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String logoutDate = DHSecureConfiguration.DATE_FORMATTER.format(new Date());
			String hqlUpdate = "UPDATE SessionEO set isLogged = 0, dataLogout = :logoutDate, sharedKey=''  WHERE account.id = :userId AND deviceId = :deviceId";	    	  

			tx = session.beginTransaction();
			Query query = session.createQuery(hqlUpdate);
			query.setParameter("logoutDate", logoutDate);
			query.setParameter("userId", userId);
			query.setParameter("deviceId", deviceId);
			int recordUpdated = query.executeUpdate();
			tx.commit();
			
			result.setNumRecordsUpdated(recordUpdated);
			result.setUpdatedKey(userId);
			result.setSuccess(true);
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during logout() ",e);
	    }finally {
	    	session.close(); 
	    }			
		logger.info("...logout client performed");
		return result;
	}
	
	@Override
	public UpdateResultBO login(SessionBO sessionBO) throws Exception {
		logger.info("logout client and device...");
		
		SessionEO sessionEO = new SessionEO();
		SessionMapper.mapBO2EO(sessionBO, sessionEO);
		
		String email = sessionEO.getAccount().getEmail();
		String password = sessionEO.getAccount().getPassword();
		
		UpdateResultBO result = new UpdateResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String hqlSelectAccount = "SELECT A.id  FROM AccountEO A WHERE A.flgValidita = 1 AND A.email = :email AND A.password = :password";	    	  
	    	
	    	tx = session.beginTransaction();
	    	
	    	Query query = session.createQuery(hqlSelectAccount);
	    	query.setParameter("email", email);
	    	query.setParameter("password", password);
	    	Integer idAccount = (Integer)query.uniqueResult();
//	    	if(results.isEmpty()){
//	    		result.setSuccess(false);
//	    		result.setNumRecordsUpdated(0);
//	    		return result;
//	    	}
//	    	int[] integers = (int[])results.get(0);
//	    	Integer idAccount = integers[0];
	    	if(idAccount==null || idAccount<=0){
	    		result.setSuccess(false);
	    		result.setNumRecordsUpdated(0);
	    		return result;
	    	}
	    	sessionEO.getAccount().setId(idAccount);
	    	Integer sessionId = (Integer)session.save(sessionEO);
	    	tx.commit();
	    	
	    	result.setSuccess(true);
	    	result.setUpdatedKey(idAccount);
	    	result.setNumRecordsUpdated(1);
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during login() ",e);
	    }finally {
	    	session.close(); 
	    }			
		logger.info("...logout client performed");
		return result;
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
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws Exception {
		logger.info("retrieving client secrets");
		
		Map<Integer, Map<String, String>> secrets = new HashMap<Integer, Map<String, String>>();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	      try{
	    	  String hqlSelect = "SELECT S.account.id, S.deviceId, S.sharedKey  FROM SessionEO S WHERE S.isLogged = 1";	    	  
	    	  Query query = session.createQuery(hqlSelect);
	    	  List<Object[]> results = query.list();
	    	  
	    	  Integer userId = null;
	    	  String deviceId = null;
	    	  String sharedKey = null;
	    	  
	    	  for (Object[] row: results) {
	    		  userId = (Integer)row[0];
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
