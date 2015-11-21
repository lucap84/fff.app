package it.fff.business.service;

import java.util.Map;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface UserBusinessService extends BusinessService{

	public CreateResultBO createUser(UserBO userBO) throws  PersistenceException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws  PersistenceException;

	public UpdateResultBO updateUserData(UserBO userBO) throws  PersistenceException;

	public UserBO getUser(int userId) throws  PersistenceException;


}
