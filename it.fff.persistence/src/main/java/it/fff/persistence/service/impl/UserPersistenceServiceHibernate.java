package it.fff.persistence.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.UserMapper;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.ConfigurationProvider;
import it.fff.persistence.util.Constants;
import it.fff.persistence.util.HibernateUtil;

public class UserPersistenceServiceHibernate implements UserPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(UserPersistenceServiceHibernate.class);
	
	@Override
	public CreateResultBO registerUser(UserBO userBO) throws Exception {
		logger.info("registering user");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Integer id = null;
	      try{
	    	  UserEO userEO = UserMapper.getInstance().mergeBO2EO(userBO,null);
	    	  
			tx = session.beginTransaction();
			id = (Integer)session.save(userEO); 
			 
			AccountEO accountEO = userEO.getAccount();
			accountEO.setId(id);
			session.save(accountEO);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during registerUser() ",e);
	      }finally {
	         session.close(); 
	      }			
		
		logger.info("user registered");
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setSuccess(true);
		resultBO.setCreatedKey(id);
		resultBO.setNumRecordsCreated(1);
		
		return resultBO;
	}

	@Override
	public UserBO getUser(int userId) throws Exception {
		UserBO bo = null;
		UserEO eo = null;
				
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	      try{
	    	 eo = (UserEO) session.get(UserEO.class, userId);
	      }catch (HibernateException e) {
	         e.printStackTrace();
	         throw new Exception("HibernateException during getUser() ",e);
	      }finally {
	         session.close(); 
	      }	        
	      bo = UserMapper.getInstance().mapEO2BO(eo);
		return bo;
	}

	@Override
	public UpdateResultBO updateUserData(UserBO bo) throws Exception {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		UserEO eo = null;
		Transaction tx = null;
	      try{
			tx = session.beginTransaction();
			
			eo = (UserEO) session.load(UserEO.class, bo.getId());
			eo = UserMapper.getInstance().mergeBO2EO(bo, eo);
			session.update(eo);
			
//			UserEO eoTOUpdate = (UserEO) session.load(UserEO.class, eo.getId()); //TODO prova con load()
//			eoTOUpdate.setNome(eo.getNome());
//			eoTOUpdate.setCognome(eo.getCognome());
//			eoTOUpdate.setNumUpdate(99);
			
			tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during update() ",e);
	      }finally {
	         session.close(); 
	      }	        
        
        UpdateResultBO result = new UpdateResultBO();
        result.setSuccess(true);
        result.setUpdatedKey(eo.getId());
        result.setNumRecordsUpdated(1);
        
        return result;
	}	

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO boInput) throws Exception {
		logger.info("creando img user");
		ProfileImageBO outputBO = null;
		ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
		String uploadFolder = configurationProvider.getProperty(Constants.PROP_UPLOAD_LOCATION);
		String dirPath = uploadFolder+"\\"+boInput.getUserId()+"\\";
		this.createDirIfNotExists(dirPath);
		String filePath = dirPath+boInput.getFileName();
		boolean isSavedFile = saveFile(boInput.getImageInputStream(), filePath);
		if(isSavedFile){
			outputBO = boInput;
			outputBO.setImgHashCode(String.valueOf(boInput.getImageInputStream().hashCode()));
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
}
