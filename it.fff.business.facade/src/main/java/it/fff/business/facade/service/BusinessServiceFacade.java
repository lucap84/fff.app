package it.fff.business.facade.service;

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.facade.exception.BusinessException;

public interface BusinessServiceFacade {
	
	public EventDTO getEvent(int eventId) throws BusinessException;

	public UserDTO createUser(CreateUserDTO createUserDTO) throws BusinessException;

}
