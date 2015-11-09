package it.fff.business.service;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface UserBusinessService extends BusinessService{

	public UserBO createUser(UserBO userBO) throws  PersistenceException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws  PersistenceException;

}
