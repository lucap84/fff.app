package it.fff.business.facade.service;

import java.util.List;
import java.util.Map;

import it.fff.business.facade.exception.BusinessException;
import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;

public interface BusinessServiceFacade {
	
	public EventDTO getEvent(int eventId) throws BusinessException;

	public AuthDataResponseDTO createUser(RegistrationInputDTO registrationInput) throws BusinessException;

	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException;

	public List<AttendanceDTO> getAttendancesByEvent(String eventId) throws BusinessException;

	public WriteResultDTO createEvent(EventDTO eventToCreate) throws BusinessException;

	public WriteResultDTO cancelEvent(String eventId, String organizerId) throws BusinessException;

	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate) throws BusinessException;

	public WriteResultDTO addFeedback(String attendanceId, FeedbackEnum feedback) throws BusinessException;

	public WriteResultDTO cancelAttendance(String eventId, String userId) throws BusinessException;

	public WriteResultDTO postEventMessage(String eventId, String message) throws BusinessException;

	public WriteResultDTO postStandardEventMessage(String attendanceId, String stdMsgId) throws BusinessException;

	public List<MessageDTO> getEventMessages(String eventId) throws BusinessException;

	public List<EventDTO> searchEvents(String userGpsLat, String userGpsLong,String radiusKm, String desideredGpsLat, String desideredGpsLong, String idCategoria, String partecipanti) throws BusinessException;

	public List<PlaceDTO> getPlacesByDescription(String description, String gpsLat, String gpsLong) throws BusinessException;

	public WriteResultDTO upgradeToPremium(String userId, SubscriptionDTO subscription) throws BusinessException;

	public AuthDataResponseDTO login(LoginInputDTO loginInfo, String sharedSecretHEX) throws BusinessException;

	public WriteResultDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) throws BusinessException;

	public WriteResultDTO checkVerificationCode(String email, String verificationcode) throws BusinessException;

	public WriteResultDTO sendVerificationCode(String email) throws BusinessException;

	public WriteResultDTO logout(String userId, String deviceId) throws BusinessException;

	public WriteResultDTO updateUserData(UserDTO user) throws BusinessException;

	public WriteResultDTO setCurrentPosition(String userId, PlaceDTO currentPosition) throws BusinessException;

	public List<EventDTO> getEventsByUser(String userId) throws BusinessException;

	public UserDTO getUser(int userId) throws BusinessException;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws BusinessException;

	public List<LanguageDTO> getAllLanguages() throws BusinessException;

	public List<SubscriptionTypeDTO> getAllSubscriptionTypes() throws BusinessException;

	public List<AchievementTypeDTO> getAllAchievementTypes() throws BusinessException;

	public List<MessageStandardDTO> getAllStandardMessages() throws BusinessException;

	public List<AttendanceStateEnum> getAllAttendanceStates() throws BusinessException;

	public List<EventStateEnum> getAllEventStates() throws BusinessException;

	public List<EventCategoryDTO> getAllEventCategories() throws BusinessException;

	public List<NationDTO> getAllNations() throws BusinessException;

	public WriteResultDTO resetPassword(ResetPasswordDTO resetPasswordDTO) throws BusinessException;

	public EmailInfoDTO isExistingEmail(String email) throws BusinessException;

	public CityDTO getCityByName(String cityName, String nationCode) throws BusinessException;

	public List<FeedbackEnum> getUserFeedbacks(String userId) throws BusinessException;

	public ProfileImageDTO readProfileImage(String userId) throws BusinessException;

	public AuthDataResponseDTO loginFacebook(String code, String deviceId) throws BusinessException;

	public UserDTO getFacebookUserData(String token, int socialTokenExpires, String deviceId) throws BusinessException;

	public List<AttendanceDTO> getAttendancesByUser(String userId) throws BusinessException;

	public AccountDTO getUserAccountByEmail(String email) throws BusinessException;

	public AccountDTO getUserAccountByFacebookId(String facebookId) throws BusinessException;

}
