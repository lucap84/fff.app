package it.fff.persistence.service;


import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;

public interface UserPersistenceService  extends PersistenceService{

	public CreateResultBO registerUser(UserBO userBO) throws Exception;

	public ProfileImageBO updateProfileImage(ProfileImageBO eoInput) throws Exception;

	public UserBO getUser(int userId) throws Exception;

	public UpdateResultBO updateUserData(UserBO eo) throws Exception;

}
