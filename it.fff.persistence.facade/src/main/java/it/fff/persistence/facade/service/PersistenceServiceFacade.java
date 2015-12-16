package it.fff.persistence.facade.service;

import java.util.List;
import java.util.Map;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface PersistenceServiceFacade {

	public EventBO retrieveEvent(int eventId) throws PersistenceException;

	public WriteResultBO registerUser(UserBO userBO) throws PersistenceException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException;

	public WriteResultBO createEvent(EventBO bo) throws PersistenceException;

	public WriteResultBO cancelEvent(int eventId, int organizerId) throws PersistenceException;

	public WriteResultBO cancelAttendance(int eventId, int userId) throws PersistenceException;

	public WriteResultBO createEventMessage(int attendanceId, String message) throws PersistenceException;

	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException;

	public WriteResultBO joinEvent(AttendanceBO bo) throws PersistenceException;

	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws PersistenceException;

	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException;

	public List<EventBO> searchEvents(double gpsLatFrom,double gpsLatTo, double gpsLongFrom, double gpsLongTo, int idCategoria, int minPartecipanti) throws PersistenceException;

	public List<EventBO> getEventsByUser(int userId) throws PersistenceException;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException;

	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException;

	public List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException;

	public WriteResultBO login(SessionBO session) throws PersistenceException;

	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException;

	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws PersistenceException;

	public WriteResultBO logout(int userId, String deviceId) throws PersistenceException;

	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException;

	public WriteResultBO updateUserData(UserBO userBO) throws PersistenceException;

	public UserBO getUser(int userId) throws PersistenceException;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException;

	public List<LanguageBO> getAllLanguages() throws PersistenceException;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException;

	public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException;

	public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException;

	public List<AttendanceStateEnum> getAllAttendanceStates() throws PersistenceException;

	public List<EventStateEnum> getAllEventStates() throws PersistenceException;

	public List<EventCategoryBO> getAllEventCategories() throws PersistenceException;

	public List<NationBO> getAllNations()  throws PersistenceException;

	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode)  throws PersistenceException;
	

}
