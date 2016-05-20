package it.fff.integration.facade.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.integration.facade.exception.IntegrationException;
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
import it.fff.business.common.bo.UserBO;

public interface IntegrationServiceFacade {

	public EventBO retrieveEvent(int eventId) throws IntegrationException;

	public WriteResultBO registerUser(UserBO userBO) throws IntegrationException;

	public WriteResultBO updateProfileImage(ProfileImageBO imgBO) throws IntegrationException;

	public WriteResultBO createEvent(EventBO bo) throws IntegrationException;

	public WriteResultBO cancelEvent(int eventId, int organizerId) throws IntegrationException;

	public WriteResultBO cancelAttendance(int eventId, int userId) throws IntegrationException;

	public WriteResultBO createEventMessage(int attendanceId, String message) throws IntegrationException;

	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws IntegrationException;

	public WriteResultBO joinEvent(AttendanceBO bo) throws IntegrationException;

	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws IntegrationException;

	public List<MessageBO> getEventMessages(int eventId) throws IntegrationException;

	public List<EventBO> searchEvents(double gpsLatFrom,double gpsLatTo, double gpsLongFrom, double gpsLongTo, int idCategoria, int minPartecipanti) throws IntegrationException;

	public List<EventBO> getEventsByUser(int userId) throws IntegrationException;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws IntegrationException;

	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws IntegrationException;

	public Set<PlaceBO> getPlacesByDescription(String description, double gplLat, double gpsLong) throws IntegrationException;
	
	public PlaceBO getPlaceByGPS(double userGpsLat, double userGpsLong) throws IntegrationException;

	public WriteResultBO login(SessionBO session) throws IntegrationException;

	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws IntegrationException;

	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws IntegrationException;

	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws IntegrationException;

	public WriteResultBO logout(int userId, String deviceId) throws IntegrationException;

	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws IntegrationException;

	public WriteResultBO updateUserData(UserBO userBO) throws IntegrationException;

	public UserBO getUser(int userId) throws IntegrationException;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws IntegrationException;

	public List<LanguageBO> getAllLanguages() throws IntegrationException;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws IntegrationException;

	public List<AchievementTypeBO> getAllAchievementTypes() throws IntegrationException;

	public List<MessageStandardBO> getAllStandardMessages() throws IntegrationException;

	public List<AttendanceStateEnum> getAllAttendanceStates() throws IntegrationException;

	public List<EventStateEnum> getAllEventStates() throws IntegrationException;

	public List<EventCategoryBO> getAllEventCategories() throws IntegrationException;

	public List<NationBO> getAllNations()  throws IntegrationException;

	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws IntegrationException;

	public EmailInfoBO getEmailInfo(String email) throws IntegrationException;

	public CityBO getCityByName(String cityName, String nationKey) throws IntegrationException;

	public List<FeedbackEnum> getUserFeedbacks(int userId) throws IntegrationException;

	public ProfileImageBO readProfileImage(int userId) throws IntegrationException;

	public String getFacebookToken(String code) throws IntegrationException;

	public UserBO getFacebookUserData(String token, String deviceId) throws IntegrationException;

	public List<AttendanceBO> getAttendancesByUser(int userId) throws IntegrationException;

	public AccountBO getUserAccountByFacebookId(long facebookId) throws IntegrationException;
	

}
