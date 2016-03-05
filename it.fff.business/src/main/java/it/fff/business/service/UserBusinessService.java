package it.fff.business.service;

import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.IntegrationException;

public interface UserBusinessService extends BusinessService{

	public WriteResultBO createUser(UserBO userBO) throws  IntegrationException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws  IntegrationException;

	public WriteResultBO updateUserData(UserBO userBO) throws  IntegrationException;

	public UserBO getUser(int userId) throws  IntegrationException;

	public WriteResultBO cancelAttendance(int eventId, int userId)  throws  IntegrationException;

	public EmailInfoBO isExistingEmail(String email)  throws  IntegrationException;


}
