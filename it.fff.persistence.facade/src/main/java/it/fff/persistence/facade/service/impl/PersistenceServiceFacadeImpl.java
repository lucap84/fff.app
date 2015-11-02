package it.fff.persistence.facade.service.impl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.business.common.mapper.EventMapper;
import it.business.common.mapper.UserMapper;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dao.EventDAO;
import it.fff.business.common.dao.UserDAO;
import it.fff.business.common.dto.CreateUserDTO;
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

		EventDAO eventDAO = null;
		try{
			eventDAO = eventPersistenceService.retrieveEvent(eventId);
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
		if(eventDAO!=null){
			logger.debug("event ({}) retrieved",eventId);
		}
		EventMapper mapper = new EventMapper();
		EventBO eventBO = mapper.mapDao2Bo(eventDAO);
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

		UserDAO userDAOoutput = null;
		try{
			UserMapper mapper = new UserMapper();
			UserDAO userDAOinput = mapper.mapBo2Dao(userBO); 
			userDAOoutput = userPersistenceService.registerUser(userDAOinput);
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
		if(userDAOoutput!=null){
			logger.debug("user created");
		}
		UserMapper mapper = new UserMapper();
		UserBO userBOCreated = mapper.mapDao2Bo(userDAOoutput);
		if(userBO!=null){
			logger.debug("Usermapped in BO");
		}
		return userBOCreated;
	}

}
