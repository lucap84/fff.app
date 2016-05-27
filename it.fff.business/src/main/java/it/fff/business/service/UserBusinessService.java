package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.integration.facade.exception.IntegrationException;

public interface UserBusinessService extends BusinessService{

	public WriteResultBO createUser(UserBO userBO) throws  IntegrationException;

	public WriteResultBO updateProfileImage(ProfileImageBO imgBO) throws  IntegrationException;

	public WriteResultBO updateUserData(UserBO userBO) throws  IntegrationException;

	public UserBO getUser(int userId) throws  IntegrationException;

	public WriteResultBO cancelAttendance(int eventId, int userId)  throws  IntegrationException;

	public EmailInfoBO getEmailInfo(String email)  throws  IntegrationException;

	public List<FeedbackEnum> getUserFeedbacks(int userId) throws IntegrationException;

	public ProfileImageBO readProfileImage(int userId) throws IntegrationException;

	public UserBO getFacebookUserData(String token, int socialTokenExpires, String deviceId) throws IntegrationException;

	public List<AttendanceBO> getAttendancesByUser(int userId) throws IntegrationException;

	public AccountBO getUserAccountByFacebookId(long facebookId) throws IntegrationException;

	public AccountBO getUserAccountByEmail(String email) throws IntegrationException;


}
