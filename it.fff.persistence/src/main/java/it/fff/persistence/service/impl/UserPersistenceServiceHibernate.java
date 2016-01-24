package it.fff.persistence.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.AchievementObtainedEO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.UserMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class UserPersistenceServiceHibernate implements UserPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(UserPersistenceServiceHibernate.class);
	
	@Override
	public WriteResultBO registerUser(UserBO userBO) throws Exception {
		logger.info("registering user");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Integer id = null;
	    try{
	    	tx = session.beginTransaction();

	    	UserEO userEO = UserMapper.getInstance().mergeBO2EO(userBO,null, session);
	    	
	    	String dataCreazione = Constants.DATE_FORMATTER.format(new Date());
	    	userEO.setDataCreazione(dataCreazione);
	    	  
			id = (Integer)session.save(userEO); 
			 
			AccountEO accountEO = userEO.getAccount();
			accountEO.setId(id);
			session.save(accountEO);
			 
			 tx.commit();
	      }
	      catch(ConstraintViolationException e){
		         if (tx!=null) tx.rollback();
		         e.printStackTrace();
		         throw new Exception("ConstraintViolationException during registerUser() ",e);
	      }
	      catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during registerUser() ",e);
	      }finally {
	         session.close(); 
	      }			
		
		logger.info("user registered");
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setSuccess(true);
		resultBO.setWrittenKey(id);
		resultBO.setAffectedRecords(1);
		
		return resultBO;
	}

	@Override
	public UserBO getUser(int userId) throws Exception {
		UserBO bo = null;
		UserEO eo = null;
				
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	 eo = (UserEO) session.get(UserEO.class, userId);
	    	 
	    	 if(eo!=null){
		    	 //inizializzo i campi lazy richiesti in output prima che si chiuda la sessione hibernate
		    	 for (LanguageEO l :  eo.getLingue()) {
			    	 if(l!=null){
			    		 Hibernate.initialize(l);
			    	 }
				 }
		    	 for (AchievementObtainedEO a :  eo.getAchievements()) {
			    	 if(a!=null){
			    		 Hibernate.initialize(a);
			    	 }
				 }
		    	 for (SubscriptionEO s :  eo.getAbbonamenti()) {
			    	 if(s!=null){
			    		 Hibernate.initialize(s);
			    	 }
				 }	    	 
	    	 }
	    	 
	    	 tx.commit();
	      }catch (HibernateException e) {
	    	  if(tx!=null)tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during getUser() ",e);
	      }finally {
	         session.close(); 
	      }	        
	      bo = UserMapper.getInstance().mapEO2BO(eo);
		return bo;
	}

	@Override
	public WriteResultBO updateUserData(UserBO bo) throws Exception {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		UserEO eo = null;
		Transaction tx = null;
	      try{
			tx = session.beginTransaction();
			
			eo = (UserEO) session.get(UserEO.class, bo.getId());
			if(eo!=null){
				eo = UserMapper.getInstance().mergeBO2EO(bo, eo, session);
				
		    	String dataAggiornamento = Constants.DATE_FORMATTER.format(new Date());
		    	eo.setDataAggiornamento(dataAggiornamento);
		    	
				session.update(eo);
			}
			
			tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during update() ",e);
	      }finally {
	         session.close(); 
	      }	        
        
        WriteResultBO result = new WriteResultBO();
        result.setSuccess(true);
        result.setWrittenKey(eo.getId());
        result.setAffectedRecords(1);
        
        return result;
	}	

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO boInput) throws Exception {
		logger.info("creando img user");
		ProfileImageBO outputBO = null;
		ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
		String uploadFolder = configurationProvider.getImageConfigProperty(Constants.PROP_IMAGE_UPLOAD_LOCATION);
		String dirPath = uploadFolder+"\\"+boInput.getUserId()+"\\";
		this.createDirIfNotExists(dirPath);
		String filePath = dirPath+boInput.getFileName();
		boolean isSavedFile = saveFile(boInput.getImageInputStream(), filePath);
		if(isSavedFile){
			outputBO = boInput;
			outputBO.setImgHashCode(boInput.getImageInputStream().hashCode());
		}
		if(outputBO!=null){
			logger.info("Img user created");
		}
		else{
			throw new SQLException("Errore creando su File system");
		}
		return outputBO;
	}

	private boolean createDirIfNotExists(String dirPath) {
		boolean exists = false;
		File dir = new File(dirPath);
		exists = dir.exists();
		if(!exists){
			try{
				exists = dir.mkdir();
			}
			catch(SecurityException se){
				exists = false;
			}
		}
		return exists;
	}	
	// save uploaded file to a defined location on the server
    private boolean saveFile(InputStream uploadedInputStream,  String serverLocation) {
    	boolean saved = true;
    	OutputStream outpuStream = null;
        try {
            outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
 
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
        	saved = false;
            e.printStackTrace();
        }
        return saved;
    }	
    
	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws Exception {
		logger.info("abbandono evento (annullo partecipazione)...");
		
		WriteResultBO result = new WriteResultBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	String dataAggiornamento = Constants.DATE_FORMATTER.format(new Date());
	    	
	    	String hqlUpdateAttendance = "UPDATE AttendanceEO set isValid = 0, dataAggiornamento=:dataAggiornamento  WHERE utente.id=:userId AND event.id=:eventId AND isValid=1";	    	  
			Query queryUpdateEvent = session.createQuery(hqlUpdateAttendance);
			queryUpdateEvent.setParameter("dataAggiornamento", dataAggiornamento);
			queryUpdateEvent.setParameter("userId", userId);
			queryUpdateEvent.setParameter("eventId", eventId);
			
			int recordUpdated = queryUpdateEvent.executeUpdate();
			
			tx.commit();
			
			result.setAffectedRecords(recordUpdated);
			if(recordUpdated>0){
				result.setWrittenKey(eventId);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
			
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during cancelAttendance() ",e);
	    }finally {
	    	session.close(); 
	    }		
		logger.info("...evento abbandonato (partecipazione annullata)");
		return result;
	}    
}
