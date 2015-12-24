package it.fff.persistence.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.SessionEO;
import it.fff.business.common.mapper.SessionMapper;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.persistence.service.SecurityPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class SecurityPersistenceServiceHibernate implements SecurityPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(SecurityPersistenceServiceHibernate.class);

	@Override
	public WriteResultBO logout(int userId, String deviceId) throws Exception {
		logger.info("logout client and device...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String logoutDate = Constants.DATE_FORMATTER.format(new Date());
			String hqlUpdate = "UPDATE SessionEO S SET S.isLogged=0 , S.dataLogout=:logoutDate, S.sharedKey='' WHERE S.deviceId=:deviceId AND account.id IN (SELECT a.id FROM AccountEO a WHERE a.id = :userId AND a.flgValidita=1)";	    	  

			Query query = session.createQuery(hqlUpdate);
			query.setString("logoutDate", logoutDate);
			query.setInteger("userId", userId);
			query.setString("deviceId", deviceId);

			tx = session.beginTransaction();
			int recordUpdated = query.executeUpdate();
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			result.setWrittenKey(userId);
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
	public WriteResultBO login(SessionBO sessionBO) throws Exception {
		logger.info("logout client and device...");
		
		
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	
	    	tx = session.beginTransaction();

	    	SessionEO sessionEO = SessionMapper.getInstance().mergeBO2EO(sessionBO, null, session);
	    	String email = sessionEO.getAccount().getEmail();
	    	String password = sessionEO.getAccount().getPassword();
	    	
	    	String hqlSelectAccount = "SELECT A.id  FROM AccountEO A WHERE A.flgValidita = 1 AND A.email = :email AND A.password = :password";	    	  
	    	Query query = session.createQuery(hqlSelectAccount);
	    	query.setParameter("email", email);
	    	query.setParameter("password", password);
	    	Integer idAccount = (Integer)query.uniqueResult();

	    	if(idAccount==null || idAccount<=0){
	    		throw new Exception("Account non trovato!");
	    	}
	    	sessionEO.getAccount().setId(idAccount);
	    	Integer sessionId = (Integer)session.save(sessionEO);//insert nuovo record session
	    	
	    	tx.commit();
	    	
	    	result.setSuccess(true);
	    	result.setWrittenKey(idAccount);
	    	result.setAffectedRecords(1);
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
	public WriteResultBO checkVerificationCode(String email, String verificationCode) throws Exception {
		logger.info("checkVerificationCode...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String hqlSelectAccount = "FROM AccountEO A WHERE A.flgValidita = 1 AND A.email = :email AND A.verificationCode = :verificationCode";	    	  
	    	
	    	Query query = session.createQuery(hqlSelectAccount);
	    	query.setParameter("email", email);
	    	query.setParameter("verificationCode", verificationCode);
	    	
	    	tx = session.beginTransaction();
	    	
	    	AccountEO accountEO = (AccountEO)query.uniqueResult();
	    	if(accountEO==null){
	    		throw new Exception("Verification Code non corretto!");
	    	}
	    	accountEO.setFlgVerificato(true);// imposto il codice verificato
	    	session.update(accountEO);

	    	tx.commit();
	    	
	    	result.setSuccess(true);
	    	result.setWrittenKey(accountEO.getId());
	    	result.setAffectedRecords(1);
	    }catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during checkVerificationCode() ",e);
	    }finally {
	    	session.close(); 
	    }			
		logger.info("...checkVerificationCode");
		return result;
	}

	@Override
	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws Exception {
		logger.info("update password...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
//			String hqlUpdate = "UPDATE AccountEO set password = :newPassword  WHERE id =:userId AND email =:email AND password = :oldPassword AND flgValidita = 1";	    	  
//			Query query = session.createQuery(hqlUpdate);
	    	Query query = session.getNamedQuery(Constants.QY_UPDATE_ACCOUNT_PSW);
			query.setParameter("newPassword", encodedNewPassword);
			query.setParameter("userId", userId);
			query.setParameter("email", email);
			query.setParameter("oldPassword", encodedOldPassword);

			int recordUpdated = query.executeUpdate();
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			result.setWrittenKey(userId);
			result.setSuccess(true);
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during updatePassword() ",e);
	    }finally {
	    	session.close(); 
	    }			
	    logger.info("...update password");
		return result;
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

	@Override
	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws Exception {
		logger.info("reset Password...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	String hqlSelectAccount = "SELECT A.id  FROM AccountEO A WHERE A.flgValidita = 1 AND A.email = :email AND A.verificationCode = :verificationCode AND A.flgVerificato = 1";	    	  
	    	
	    	
	    	Query querySelectVerifiedAccount = session.createQuery(hqlSelectAccount);
	    	querySelectVerifiedAccount.setParameter("email", email);
	    	querySelectVerifiedAccount.setParameter("verificationCode", verificationCode);
	    	
	    	tx = session.beginTransaction();

	    	Integer idAccount = (Integer)querySelectVerifiedAccount.uniqueResult();	    	
	    	
			String hqlUpdate = "UPDATE AccountEO set password = :newPassword  WHERE id =:idAccount AND flgVerificato = 1";	    	  
			Query queryResetPassword = session.createQuery(hqlUpdate);
			queryResetPassword.setParameter("newPassword", newPassword);
			queryResetPassword.setParameter("idAccount", idAccount);
			int recordUpdated = queryResetPassword.executeUpdate();

			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			result.setWrittenKey(idAccount);
			result.setSuccess(true);
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during resetPassword() ",e);
	    }finally {
	    	session.close(); 
	    }			
	    logger.info("...reset Password");
		return result;
	}

	@Override
	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws Exception {
		logger.info("update password...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
			String hqlUpdate = "UPDATE AccountEO set verificationCode = :verificationCode, flgVerificato = 0  WHERE email =:email AND flgValidita = 1";	    	  

			Query query = session.createQuery(hqlUpdate);
			query.setParameter("verificationCode", verificationCode);
			query.setParameter("email", email);

			tx = session.beginTransaction();
			int recordUpdated = query.executeUpdate();
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			result.setWrittenKey(-1);
			result.setSuccess(recordUpdated>0);
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during updatePassword() ",e);
	    }finally {
	    	session.close(); 
	    }			
	    logger.info("...update password");
		return result;
	}

}
