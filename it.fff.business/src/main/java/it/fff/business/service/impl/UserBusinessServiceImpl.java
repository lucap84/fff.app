package it.fff.business.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.service.UserBusinessService;
import it.fff.business.strategy.ImageValidationStrategy;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class UserBusinessServiceImpl implements UserBusinessService {
	
	private static final Logger logger = LogManager.getLogger(UserBusinessServiceImpl.class);

	private PersistenceServiceFacade persistenceFacade;
	private ImageValidationStrategy imageValidationStrategy;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}
	
	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}	
	
	public ImageValidationStrategy getImageValidationStrategy() {
		return imageValidationStrategy;
	}
	
	public void setImageValidationStrategy(ImageValidationStrategy imageValidationStrategy) {
		this.imageValidationStrategy = imageValidationStrategy;
	}	
	
	@Override
	public UserBO createUser(UserBO userBO) throws PersistenceException {
		logger.info("createUser start...");
		UserBO userBOCreated = persistenceFacade.registerUser(userBO);
		logger.info("...createUser end");
		return userBOCreated;
	}


	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException {
		logger.info("updateProfileImage start...");
		if(!imageValidationStrategy.isValid(imgBO)){
			throw new RuntimeException("Immagine in input non validata!");
		}
		ProfileImageBO bo = persistenceFacade.updateProfileImage(imgBO);
		logger.info("...updateProfileImage end");
		return bo;
	}
	

	@Override
	public UpdateResultBO updateUserData(UserBO userBO) throws PersistenceException {
		UpdateResultBO resultBO = persistenceFacade.updateUserData(userBO);
		return resultBO;
	}


	@Override
	public UserBO getUser(int userId) throws PersistenceException {
		UserBO bo = persistenceFacade.getUser(userId);
		return bo;
	}


}
