package it.fff.persistence.service;


import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;

public interface UserPersistenceService  extends PersistenceService{

	public CreateResultBO registerUser(UserEO userEO) throws Exception;

	public ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws Exception;

	public UserEO getUser(int userId) throws Exception;

	public UpdateResultBO updateUserData(UserEO eo) throws Exception;

}
