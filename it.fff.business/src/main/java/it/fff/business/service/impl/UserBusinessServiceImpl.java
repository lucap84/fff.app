package it.fff.business.service.impl;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.service.UserBusinessService;
import it.fff.business.strategy.ImageValidationStrategy;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
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
	public WriteResultBO createUser(UserBO userBO) throws PersistenceException {
		logger.info("createUser start...");
		//Inizializzo le validita dell'account
		AccountBO creatingAccount = userBO.getAccount();
		creatingAccount.setFlgValidita(true);
		//imposto l'utente come loggato appena creato l'account
		SessionBO firstSession = creatingAccount.getSessions().get(0);
		firstSession.setLogged(true);
		//imposto la data login della prima sessione
		String loginDate = DHSecureConfiguration.DATE_FORMATTER.format(new Date());
		firstSession.setDataLogin(loginDate);
		
		WriteResultBO resultBO = persistenceFacade.registerUser(userBO);
		logger.info("...createUser end");
		return resultBO;
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
	public WriteResultBO updateUserData(UserBO userBO) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.updateUserData(userBO);
		return resultBO;
	}


	@Override
	public UserBO getUser(int userId) throws PersistenceException {
		UserBO bo = persistenceFacade.getUser(userId);
		return bo;
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.cancelAttendance(eventId, userId);
		return resultBO;
	}

}
