package it.fff.persistence.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.AchievementObtainedEO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.AccountMapper;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.ProfileImageMapper;
import it.fff.business.common.mapper.UserMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.HibernateUtil;

//import org.apache.catalina.util.Base64;
import org.apache.commons.net.util.Base64;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.net.util.Base64;

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
	public WriteResultBO updateProfileImage(ProfileImageBO boInput) throws Exception {
		logger.info("creando img user");
		
		
		ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
		String uploadFolder = configurationProvider.getImageConfigProperty(Constants.PROP_IMAGE_UPLOAD_LOCATION);

		String dirPath = uploadFolder+"\\"+boInput.getUserId()+"\\";
		String filePath = dirPath+boInput.getFileName();
		
		this.createDirIfNotExists(dirPath);
		boInput.setPath(dirPath);
		
		boolean isSavedFile = saveFile(boInput.getImageInputStream(), filePath);
		if(isSavedFile){
			boInput.setHash(String.valueOf(boInput.getImageInputStream().hashCode()));
			logger.info("Img user created on FileSystem");
		}
		else{
			throw new Exception("Errore creando su File system");
		}
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ProfileImageEO imageEO = null;
		Integer imageID = -1;
		
		
	    try{
	    	tx = session.beginTransaction();
	    	
	    	imageEO = ProfileImageMapper.getInstance().mergeBO2EO(boInput, imageEO, session);
			imageID = (Integer) session.save(imageEO);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during updateProfileImage() ",e);
	    }finally {
	    	session.close(); 
	    }		
		
        WriteResultBO result = new WriteResultBO();
        result.setSuccess(true);
        result.setWrittenKey(imageID);
        result.setAffectedRecords(1);
        
        return result;
	}
	
	@Override
	public ProfileImageBO readProfileImage(int userId) throws Exception {
		logger.info("get metadati immagine da DB...");
		
		ProfileImageBO result = new ProfileImageBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ProfileImageEO imageEO = null;
		UserEO userEO = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	userEO = (UserEO) session.get(UserEO.class, userId);
	    	List<ProfileImageEO> profileImages = userEO.getProfileImages();
	    	for (ProfileImageEO eo : profileImages) {
				if(eo.isProfileImage()){
					imageEO = eo;
					break;
				}
			}
	    	
	    	tx.commit();
	    	
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during readProfileImageMetadata() ",e);
	    }finally {
	    	session.close(); 
	    }
	    
	    result = ProfileImageMapper.getInstance().mapEO2BO(imageEO);
	    
	    logger.info("....get metadati immagine da DB completato");

		String filePath = result.getPath()+result.getFileName();
		
//		FileInputStream fis = this.readFile(filePath);
		String imageAsB64 = this.readFileAsBase64(filePath);
		
		if(imageAsB64!=null){
			result.setImageAsB64(imageAsB64);
		}
		else{
			throw new Exception("Errore leggendo immagine da Filesystem");
		}
		
		logger.info("...recuperata img user");
		
		return result;
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

	@Override
	public EmailInfoBO getEmailInfo(String email) throws Exception {
		logger.info("check mail esistente...");
		
		EmailInfoBO result = new EmailInfoBO();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	Query query = session.getNamedQuery(Constants.QY_GET_INFO_BY_MAIL);	    	  
	    	query.setParameter("email", email);

	    	List<Object[]> results = query.list();
	    	  
	    	Integer accountId = null;
	    	Boolean flgValidita = null;
	    	Boolean flgVerificato = null;
	    	  
	    	for (Object[] row: results) {
	    		accountId = (Integer)row[0];
	    		flgValidita = (Boolean)row[1];
	    		flgVerificato = (Boolean)row[2];
    		}
	    	
			tx.commit();
			
			if(accountId!=null && accountId>0){
				result.setEmail(email);
				result.setExisting(true);
				result.setValidAccount(flgValidita);
				result.setVerifiedAccount(flgVerificato);
			}else{
				result.setExisting(false);
			}
			
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during isExistingEmail() ",e);
	    }finally {
	    	session.close(); 
	    }		
		logger.info("...check mail completato");
		return result;
	}

	@Override
	public List<FeedbackEnum> getUserFeedbacks(int userId) throws Exception {
		List<FeedbackEnum> feedbacks = new ArrayList<FeedbackEnum>();
		
		List<AttendanceBO> attendances = this.getAttendancesByUser(userId);
		
		for (AttendanceBO a : attendances) {
			if(a.isOrganizer()){
				feedbacks.add(a.getFeedback());
			}
		}
		
		return feedbacks;
	}
	
	@Override
	public List<AttendanceBO> getAttendancesByUser(int userId) throws Exception {
		List<AttendanceBO> bos = null;
		List<AttendanceEO> eos = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	 tx = session.beginTransaction();
			String hqlSelect = "FROM AttendanceEO a WHERE a.utente.id = :userId AND a.isValid = 1 ";	    	  
	    	Query query = session.createQuery(hqlSelect);
	    	query.setParameter("userId", userId);
	    	
	    	eos = query.list();
	    	
	    	tx.commit();
	    	
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAttendancesByUser() ",e);
	     }finally {
	        session.close(); 
	     }

		bos = AttendanceMapper.getInstance().mapEOs2BOs(eos);
		return bos;
	}
	
	@Override
	public AccountBO getUserAccountByFacebookId(long facebookId) throws Exception {
		logger.info("get account by facebook...");
		
		AccountBO result = null;
		AccountEO eo = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	Query query = session.getNamedQuery(Constants.QY_GET_ACCOUNT_BY_FB);	    	  
	    	query.setParameter("facebookId", facebookId);

	    	eo = (AccountEO)query.uniqueResult();
	    	  
			tx.commit();
			
	    }catch (HibernateException e) {
	    	 if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	        throw new Exception("HibernateException during isExistingEmail() ",e);
	    }finally {
	    	session.close(); 
	    }
	    
	    result = AccountMapper.getInstance().mapEO2BO(eo);
	    
		logger.info("...get account by facebook completato");
		return result;

	}	
	
	

	/*
	 * 
	 * Utility methods
	 * 
	 * 
	 */
	

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
            int read = 0;
            byte[] bytes = new byte[1024];
 
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

        } catch (IOException e) {
        	saved = false;
            e.printStackTrace();
        }
        finally {
        	try {
                outpuStream.flush();
                outpuStream.close();
        		outpuStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return saved;
    }
    
    private FileInputStream readFile(String serverLocation){
    	File file = new File(serverLocation);
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			logger.debug("Total file size to read (in bytes) : "+ fis.available());
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    	
		return fis;
    }

    private String readFileAsBase64(String serverLocation){
    	String b64 = null;
    	File file = new File(serverLocation);
    	byte[] readFileToByteArray;
		try {
			readFileToByteArray = FileUtils.readFileToByteArray(file);
			b64 = Base64.encodeBase64String(readFileToByteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return b64;
    }

}

