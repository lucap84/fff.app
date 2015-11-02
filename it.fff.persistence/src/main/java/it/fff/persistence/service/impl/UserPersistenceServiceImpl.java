package it.fff.persistence.service.impl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.dao.UserDAO;
import it.fff.persistence.service.UserPersistenceService;

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

}
