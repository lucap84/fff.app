package it.fff.persistence.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.UserMapper;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.ConfigurationProvider;
import it.fff.persistence.util.Constants;
import it.fff.persistence.util.HibernateUtil;

public class UserPersistenceServiceHibernate implements UserPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(UserPersistenceServiceHibernate.class);
	
	@Override
	public CreateResultBO registerUser(UserEO userEO) throws Exception {
		logger.info("registering user");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Integer id = null;
	      try{
	         tx = session.beginTransaction();
	         id = (Integer)session.save(userEO); 
	         
	         AccountEO accountEO = userEO.getAccount();
	         accountEO.setId(id);
	         session.save(accountEO);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         throw new Exception("HibernateException during save() ",e);
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
	public UserEO getUser(int userId) throws Exception {
		UserEO eo = null;
        
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	      try{
	         eo = (UserEO) session.get(UserEO.class, userId);
	      }catch (HibernateException e) {
	         e.printStackTrace();
	         throw new Exception("HibernateException during get() ",e);
	      }finally {
	         session.close(); 
	      }	        
        
		return eo;
	}

	@Override
	public UpdateResultBO updateUserData(UserEO eo) throws Exception {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	      try{
			tx = session.beginTransaction();
			UserEO eoTOUpdate = (UserEO) session.load(UserEO.class, eo.getId()); //TODO prova con load()
			eoTOUpdate.setNome(eo.getNome());
			eoTOUpdate.setCognome(eo.getCognome());
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
	public ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws Exception {
		logger.info("creando img user");
		ProfileImageEO outputEo = null;
		ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
		String uploadFolder = configurationProvider.getProperty(Constants.PROP_UPLOAD_LOCATION);
		String filePath = uploadFolder+"/"+eoInput.getUserId()+"/"+eoInput.getFileName();
		boolean isSavedFile = saveFile(eoInput.getImageInputStream(), filePath);
		if(isSavedFile){
			outputEo = eoInput;
			outputEo.setImageIdentifier(String.valueOf(eoInput.getImageInputStream().hashCode()));
		}
		if(outputEo!=null){
			logger.info("Img user created");
		}
		else{
			throw new SQLException("Errore creando su File system");
		}
		return outputEo;
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
