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

import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.ConfigurationProvider;
import it.fff.persistence.util.Constants;

public class UserPersistenceServiceImpl implements UserPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(UserPersistenceServiceImpl.class);
	
	@Override
	public UserEO registerUser(UserEO userEO) throws SQLException {
		logger.info("registering user");
		UserEO outputEo = null;
		//		TODO create in DB
		if(outputEo!=null){
			logger.info("User created");
		}
		else{
			throw new SQLException("Errore creando lo user su DB");
		}
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
