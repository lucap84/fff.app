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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.ConfigurationProvider;
import it.fff.persistence.util.Constants;
import it.fff.persistence.util.HibernateUtil;

public class UserPersistenceServiceHibernate implements UserPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(UserPersistenceServiceHibernate.class);
	
	@Override
	public UserEO registerUser(UserEO userEO) throws SQLException {
		logger.info("registering user");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userEO);
		session.getTransaction().commit();
		session.close();
		logger.info("user registered");
		UserEO outputEo = userEO;
		
		return outputEo;
	}

	@Override
	public ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws SQLException {
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
	
	@Override
	public UserEO getUser(int userId) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		UserEO eo = (UserEO) session.load(UserEO.class, userId);
		UserEO eo = (UserEO) session.get(UserEO.class, userId);
        session.close();
		return eo;
	}

	@Override
	public UpdateResultBO updateUserData(UserEO eo) throws SQLException {
		UpdateResultBO result = new UpdateResultBO();

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserEO eoTOUpdate = (UserEO) session.get(UserEO.class, eo.getId());
		eoTOUpdate.setNome(eo.getNome());
		eoTOUpdate.setCognome(eo.getCognome());
		eoTOUpdate.setNumUpdate(eoTOUpdate.getNumUpdate()+1);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = df.format(new Date());
		eoTOUpdate.setDataLastUpdate(formattedDate);
//		session.merge(eoTOUpdate);
        session.getTransaction().commit();
        session.close();
        
        result.setSuccess(true);
        result.setUpdatedKey(eo.getId());
        result.setNumRecordsUpdated(1);
        
        return result;
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
