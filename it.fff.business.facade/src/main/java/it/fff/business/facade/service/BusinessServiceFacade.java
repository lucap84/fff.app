package it.fff.business.facade.service;

import it.fff.business.common.exception.ApplicationException;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.clientserver.common.dto.*;

public abstract class BusinessServiceFacade {
	
	public abstract EventDTO getEvent(int eventId) throws BusinessException;

	public abstract UserDTO createUser(UserDTO userDTO) throws BusinessException;

	public abstract WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException;
	
	public void manageException(ApplicationException e, String errorCode) throws BusinessException{

		BusinessException businessException = new BusinessException(e.getMessage(),e);
		businessException.addErrorCodes(e.getErrorCodes());
		businessException.addErrorCode(ErrorCodes.ERR_BUSIN_GETEVENT);
		throw businessException;		
	}

}
