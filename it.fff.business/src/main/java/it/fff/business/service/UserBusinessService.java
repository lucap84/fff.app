package it.fff.business.service;

import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface UserBusinessService extends BusinessService{

	UserBO createUser(UserBO userBO) throws  PersistenceException;

}
