package it.fff.persistence.facade.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SubscriptionBO;
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

	public List<EventBO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti) throws PersistenceException;

	public List<EventBO> getEventsByUser(int userId) throws PersistenceException;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException;

	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException;

	public List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException;

	public UpdateResultBO login(String username, String password) throws PersistenceException;

	public UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException;

	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	public UpdateResultBO generateVerficationCode(String email) throws PersistenceException;

	public UpdateResultBO logout(int userId) throws PersistenceException;

	public CreateResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException;

	public UpdateResultBO updateUserData(UserBO userBO) throws PersistenceException;

	public UserBO getUser(int userId) throws PersistenceException;
	

}
