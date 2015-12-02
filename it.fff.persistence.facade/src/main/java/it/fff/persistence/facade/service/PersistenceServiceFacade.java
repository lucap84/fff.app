package it.fff.persistence.facade.service;

import java.util.List;
import java.util.Map;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface PersistenceServiceFacade {

	public EventBO retrieveEvent(int eventId) throws PersistenceException;

	public CreateResultBO registerUser(UserBO userBO) throws PersistenceException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException;

	public CreateResultBO createEvent(EventBO bo) throws PersistenceException;

	public UpdateResultBO cancelEvent(int eventId) throws PersistenceException;

	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws PersistenceException;

	public CreateResultBO createEventMessage(int attendanceId, String message) throws PersistenceException;

	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException;

	public CreateResultBO joinEvent(AttendanceBO bo) throws PersistenceException;

	public CreateResultBO addFeedback(AttendanceBO bo) throws PersistenceException;

	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException;

	public List<EventBO> searchEvents(double gpsLatFrom,double gpsLatTo, double gpsLongFrom, double gpsLongTo, int idCategoria, int minPartecipanti) throws PersistenceException;

	public List<EventBO> getEventsByUser(int userId) throws PersistenceException;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException;

	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException;

	public List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException;

	public UpdateResultBO login(SessionBO session) throws PersistenceException;

	public UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException;

	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	public UpdateResultBO generateVerficationCode(String email) throws PersistenceException;

	public UpdateResultBO logout(int userId, String deviceId) throws PersistenceException;

	public CreateResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException;

	public UpdateResultBO updateUserData(UserBO userBO) throws PersistenceException;

	public UserBO getUser(int userId) throws PersistenceException;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException;

	public List<LanguageBO> getAllLanguages() throws PersistenceException;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException;

	public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException;

	public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException;

	public List<AttendanceStateBO> getAllAttendanceStates() throws PersistenceException;

	public List<EventStateBO> getAllEventStates() throws PersistenceException;

	public List<EventCategoryBO> getAllEventCategories() throws PersistenceException;

	public List<NationBO> getAllNations()  throws PersistenceException;;
	

}
