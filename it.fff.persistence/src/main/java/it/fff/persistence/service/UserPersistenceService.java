package it.fff.persistence.service;


import java.util.List;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.persistence.exception.PersistenceException;

public interface UserPersistenceService  extends PersistenceService{

	public WriteResultBO registerUser(UserBO userBO) throws PersistenceException;
	
	public UserBO getUser(int userId) throws PersistenceException;

	public WriteResultBO updateUserData(UserBO eo) throws PersistenceException;

	public WriteResultBO cancelAttendance(int eventId, int userId) throws PersistenceException;

	public EmailInfoBO getEmailInfo(String email) throws PersistenceException;

	public List<FeedbackEnum> getUserFeedbacks(int userId) throws PersistenceException;

	public WriteResultBO updateProfileImage(ProfileImageBO imageBO) throws PersistenceException;

	public ProfileImageBO readProfileImage(int userId) throws PersistenceException;

	public List<AttendanceBO> getAttendancesByUser(int userId) throws PersistenceException;

	public AccountBO getUserAccountByFacebookId(long facebookId) throws PersistenceException;

	public AccountBO getUserAccountByEmail(String email) throws PersistenceException;

}
