package it.business.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.service.UserBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class UserBusinessServiceImpl implements UserBusinessService {
	
	private static final Logger logger = LogManager.getLogger(UserBusinessServiceImpl.class);
	
	private PersistenceServiceFacade persistenceFacade;

	@Override
	public UserBO createUser(UserBO userBO) throws PersistenceException {
		logger.info("EventBusinessServiceImpl registering user...");
		UserBO userBOCreated = persistenceFacade.registerUser(userBO);
		if(userBO!=null){
			logger.info("User registered");
		}
		return userBOCreated;
	}

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	
}
