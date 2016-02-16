package it.fff.business.service;

import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface UserBusinessService extends BusinessService{

	public WriteResultBO createUser(UserBO userBO) throws  PersistenceException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws  PersistenceException;

	public WriteResultBO updateUserData(UserBO userBO) throws  PersistenceException;

	public UserBO getUser(int userId) throws  PersistenceException;

	public WriteResultBO cancelAttendance(int eventId, int userId)  throws  PersistenceException;

	public EmailInfoBO isExistingEmail(String email)  throws  PersistenceException;


}
