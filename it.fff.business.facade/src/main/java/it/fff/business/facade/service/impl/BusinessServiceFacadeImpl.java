package it.fff.business.facade.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.business.common.mapper.EventMapper;
import it.business.common.mapper.UserMapper;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.business.service.EventBusinessService;
import it.fff.business.service.UserBusinessService;
import it.fff.business.util.BusinessServiceProvider;
import it.fff.persistence.facade.exception.PersistenceException;

public class BusinessServiceFacadeImpl implements BusinessServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(BusinessServiceFacadeImpl.class);
	
	public BusinessServiceFacadeImpl(){
		
	}

	@Override
	public EventDTO getEvent(int eventId) throws BusinessException {
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di business
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		EventBO eventBO;
		try {
			eventBO = eventBusinessService.getEvent(eventId);
		} catch (PersistenceException e) {
			String errMess = "Errore da strato di persistenza. Message:"+e.getMessage();
			logger.error(errMess);
			BusinessException businessException = new BusinessException(errMess,e);
			businessException.addErrorCodes(e.getErrorCodes());
			businessException.addErrorCode(ErrorCodes.ERR_BUSIN_GETEVENT);
			throw businessException;
		}
		if(eventBO!=null){
			logger.debug("Event successfully retrieved by business layer");
		}
		EventMapper mapper = new EventMapper();
		EventDTO dtoResult = mapper.mapBo2Dto(eventBO);
		if(dtoResult!=null){
			logger.debug("Mapping bo2dto completed");
		}
		return dtoResult;
	}

	@Override
	public UserDTO createUser(CreateUserDTO createUserDTO) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		UserBO userBO = null;
		try {
			UserMapper mapper = new UserMapper();
			userBO = mapper.mapCreateUserDto2Bo(createUserDTO);
			userBO = userBusinessService.createUser(userBO);
		} catch (PersistenceException e) {
			String errMess = "Errore da strato di persistenza. Message:"+e.getMessage();
			logger.error(errMess);
			BusinessException businessException = new BusinessException(errMess,e);
			businessException.addErrorCodes(e.getErrorCodes());
			businessException.addErrorCode(ErrorCodes.ERR_BUSIN_CREATEUSER);
			throw businessException;
		}
		if(userBO!=null){
			logger.debug("User successfully retrieved by business layer");
		}
		UserMapper mapper = new UserMapper();
		UserDTO dtoResult = mapper.mapBo2Dto(userBO);
		if(dtoResult!=null){
			logger.debug("Mapping bo2dto completed");
		}
		return dtoResult;
	}


}
