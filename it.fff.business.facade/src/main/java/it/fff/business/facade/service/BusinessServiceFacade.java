package it.fff.business.facade.service;

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.ProfileImageDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.dto.WriteResultDTO;
import it.fff.business.common.exception.ApplicationException;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.service.EventBusinessService;
import it.fff.business.util.BusinessServiceProvider;

public abstract class BusinessServiceFacade {
	
	public abstract EventDTO getEvent(int eventId) throws BusinessException;

	public abstract UserDTO createUser(CreateUserDTO createUserDTO) throws BusinessException;

	public abstract WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException;
	
	public void manageException(ApplicationException e, String errorCode) throws BusinessException{

		BusinessException businessException = new BusinessException(e.getMessage(),e);
		businessException.addErrorCodes(e.getErrorCodes());
		businessException.addErrorCode(ErrorCodes.ERR_BUSIN_GETEVENT);
		throw businessException;		
	}

}
