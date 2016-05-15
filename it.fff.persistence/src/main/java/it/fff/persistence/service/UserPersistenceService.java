package it.fff.persistence.service;


import java.util.List;

import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;

public interface UserPersistenceService  extends PersistenceService{

	public WriteResultBO registerUser(UserBO userBO) throws Exception;

	public ProfileImageBO updateProfileImage(ProfileImageBO eoInput) throws Exception;
	
	public ProfileImageBO readProfileImage(int userId) throws Exception;

	public UserBO getUser(int userId) throws Exception;

	public WriteResultBO updateUserData(UserBO eo) throws Exception;

	public WriteResultBO cancelAttendance(int eventId, int userId) throws Exception;

	public EmailInfoBO isExistingEmail(String email) throws Exception;

	public List<FeedbackEnum> getUserFeedbacks(int userId) throws Exception;

}
