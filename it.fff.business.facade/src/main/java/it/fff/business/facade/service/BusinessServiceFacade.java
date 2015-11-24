package it.fff.business.facade.service;

import java.util.List;
import java.util.Map;
import java.util.List;

import it.fff.business.facade.exception.BusinessException;
import it.fff.clientserver.common.dto.*;

public interface BusinessServiceFacade {
	
	public EventDTO getEvent(int eventId) throws BusinessException;

	public AuthDataResponseDTO createUser(RegistrationDataRequestDTO registrationData) throws BusinessException;

	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException;

	public List<AttendanceDTO> getAttendancesByEvent(String eventId) throws BusinessException;

	public WriteResultDTO createEvent(EventDTO eventToCreate) throws BusinessException;

	public WriteResultDTO cancelEvent(String eventId) throws BusinessException;

	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate) throws BusinessException;

	public WriteResultDTO addFeedback(AttendanceDTO attendance) throws BusinessException;

	public WriteResultDTO cancelAttendance(String eventId, String attendanceId) throws BusinessException;

	public WriteResultDTO postEventMessage(String eventId, String message) throws BusinessException;

	public WriteResultDTO postStandardEventMessage(String attendanceId, String stdMsgId) throws BusinessException;

	public List<MessageDTO> getEventMessages(String eventId) throws BusinessException;

	public List<EventDTO> searchEvents(String gpsLat, String gpsLong, String idCategoria, String partecipanti) throws BusinessException;

	public List<PlaceDTO> getPlacesByDescription(String description) throws BusinessException;

	public WriteResultDTO upgradeToPremium(String userId, SubscriptionDTO subscription) throws BusinessException;

	public AuthDataResponseDTO login(LoginDataRequestDTO loginDataRequest, String sharedSecretHEX) throws BusinessException;

	public WriteResultDTO updatePassword(String email, String encodedPassword) throws BusinessException;

	public WriteResultDTO checkVerificationCode(String email, String verificationcode) throws BusinessException;

	public WriteResultDTO sendVerificationCode(String email) throws BusinessException;

	public WriteResultDTO logout(String userId, String deviceId) throws BusinessException;

	public WriteResultDTO updateUserData(UserDTO user) throws BusinessException;

	public WriteResultDTO setCurrentPosition(String userId, String eventId, PlaceDTO place) throws BusinessException;

	public List<EventDTO> getEventsByUser(String userId) throws BusinessException;

	public UserDTO getUser(int userId) throws BusinessException;

	public Map<String, Map<String, String>> retrieveClientSecrets() throws BusinessException;

}
