package it.fff.persistence.facade.service.impl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.UserMapper;
import it.fff.business.common.util.ErrorCodes;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.service.UserPersistenceService;
import it.fff.persistence.util.PersistenceServiceProvider;

public class PersistenceServiceFacadeImpl implements PersistenceServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(PersistenceServiceFacadeImpl.class);

	@Override
	public EventBO retrieveEvent(int eventId) throws PersistenceException{
		logger.debug("retrieving event ({}) ...",eventId);
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di persistenza
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getBusinessService("eventPersistenceService");

		EventEO eventEO = null;
		try{
			eventEO = eventPersistenceService.retrieveEvent(eventId);
		}
		catch(SQLException e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERS_INVALID_INPUT);
			throw persistenceException;
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERS_GENERIC);
			throw persistenceException;			
		}
		if(eventEO!=null){
			logger.debug("event ({}) retrieved",eventId);
		}
		EventMapper mapper = new EventMapper();
		EventBO eventBO = mapper.mapEo2Bo(eventEO);
		if(eventBO!=null){
			logger.debug("Event ({}) mapped in BO",eventId);
		}
		return eventBO;
	}

	@Override
	public UserBO registerUser(UserBO userBO) throws PersistenceException {
		logger.debug("registrando utente ...");
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di persistenza
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getBusinessService("userPersistenceService");

		UserEO userEOoutput = null;
		try{
			UserMapper mapper = new UserMapper();
			UserEO userEOinput = mapper.mapBo2Eo(userBO); 
			userEOoutput = userPersistenceService.registerUser(userEOinput);
		}
		catch(SQLException e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERS_INVALID_INPUT);
			throw persistenceException;
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERS_GENERIC);
			throw persistenceException;			
		}
		if(userEOoutput!=null){
			logger.debug("user created");
		}
		UserMapper mapper = new UserMapper();
		UserBO userBOCreated = mapper.mapEo2Bo(userEOoutput);
		if(userBO!=null){
			logger.debug("Usermapped in BO");
		}
		return userBOCreated;
	}

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException {
		logger.debug("aggiornando img utente ...");
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di persistenza
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getBusinessService("userPersistenceService");

		ProfileImageEO eoOutput = null;
		try{
			UserMapper mapper = new UserMapper();
			ProfileImageEO eoInput = mapper.mapProfileImageBo2Eo(imgBO);
			eoOutput = userPersistenceService.updateProfileImage(eoInput);
		}
		catch(SQLException e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERS_INVALID_INPUT);
			throw persistenceException;
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERS_GENERIC);
			throw persistenceException;			
		}
		if(eoOutput!=null){
			logger.debug("user img created");
		}
		UserMapper mapper = new UserMapper();
		ProfileImageBO boCreated = mapper.mapProfileImageEo2Bo(eoOutput);
		if(boCreated!=null){
			logger.debug("img mapped in BO");
		}
		return boCreated;
	}

}
