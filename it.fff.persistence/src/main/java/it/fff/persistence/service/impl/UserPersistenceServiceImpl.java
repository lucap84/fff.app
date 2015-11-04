package it.fff.persistence.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.bcel.classfile.Constant;

import it.fff.business.common.dao.ProfileImageDAO;
import it.fff.business.common.dao.UserDAO;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.ConfigurationProvider;
import it.fff.persistence.util.Constants;

public class UserPersistenceServiceImpl implements UserPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(UserPersistenceServiceImpl.class);
	
	@Override
	public UserDAO registerUser(UserDAO userDAO) throws SQLException {
		logger.info("registering user");
		UserDAO outputDao = null;
		//		TODO create in DB
		if(outputDao!=null){
			logger.info("User created");
		}
		else{
			throw new SQLException("Errore creando lo user su DB");
		}
		return outputDao;
	}

	@Override
	public ProfileImageDAO updateProfileImage(ProfileImageDAO daoInput) throws SQLException {
		logger.info("creando img user");
		ProfileImageDAO outputDao = null;
		ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
		String uploadFolder = configurationProvider.getProperty(Constants.PROP_UPLOAD_LOCATION);
		String filePath = uploadFolder+"/"+daoInput.getUserId()+"/"+daoInput.getFileName();
		boolean isSavedFile = saveFile(daoInput.getImageInputStream(), filePath);
		if(isSavedFile){
			outputDao = daoInput;
			outputDao.setImgHashCode(String.valueOf(daoInput.getImageInputStream().hashCode()));
		}
		if(outputDao!=null){
			logger.info("Img user created");
		}
		else{
			throw new SQLException("Errore creando su File system");
		}
		return outputDao;
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
