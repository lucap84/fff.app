package it.fff.business.facade.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.business.common.mapper.EventMapper;
import it.business.common.mapper.UserMapper;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.ProfileImageDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.dto.WriteResultDTO;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.business.service.EventBusinessService;
import it.fff.business.service.UserBusinessService;
import it.fff.business.util.BusinessServiceProvider;
import it.fff.persistence.facade.exception.PersistenceException;

public class BusinessServiceFacadeImpl extends BusinessServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(BusinessServiceFacadeImpl.class);
	
	public BusinessServiceFacadeImpl(){
		
	}

	@Override
	public EventDTO getEvent(int eventId) throws BusinessException {
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di business
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		EventBO eventBO = null;
		try {
			eventBO = eventBusinessService.getEvent(eventId);
		} catch (PersistenceException e) {
			super.manageException(e,ErrorCodes.ERR_BUSIN_GETEVENT);
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
			super.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);			
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

	@Override
	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO resultDTO = new WriteResultDTO();
		ProfileImageBO imgBO = null;
		try {
			UserMapper mapper = new UserMapper();
			imgBO = mapper.mapProfileImageDto2Bo(dto);
			imgBO = userBusinessService.updateProfileImage(imgBO);
		} catch (PersistenceException e) {
			super.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);
		}
		if(imgBO!=null){
			logger.debug("Operation successful by business layer");
		}
		UserMapper mapper = new UserMapper();
		ProfileImageDTO dtoResult = mapper.mapProfileImageBo2Dto(imgBO);
		
		if(dtoResult!=null){
			resultDTO.setAffectedRecords(1);
			resultDTO.setIdentifier(dtoResult.getImgHashCode());
			logger.debug("Mapping bo2dto completed");
		}
		return resultDTO;
	}


}
