package it.fff.business.facade.service;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.facade.exception.BusinessException;
import it.fff.clientserver.common.dto.*;

public interface BusinessServiceFacade {
	
	public EventDTO getEvent(int eventId) throws BusinessException;

	public UserDTO createUser(UserDTO userDTO) throws BusinessException;

	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException;

	public List<AttendanceDTO> getAttendancesByEvent(String eventId) throws BusinessException;

	public WriteResultDTO createEvent(EventDTO eventToCreate) throws BusinessException;

	public WriteResultDTO cancelEvent(String eventId) throws BusinessException;

	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate) throws BusinessException;

	public WriteResultDTO addFeedback(AttendanceDTO attendance) throws BusinessException;

	public WriteResultDTO cancelAttendance(String eventId, String attendanceId) throws BusinessException;

	public WriteResultDTO postEventMessage(String eventId, String message) throws BusinessException;

	public WriteResultDTO postStandardEventMessage(String attendanceId, String stdMsgId) throws BusinessException;

	public ArrayList<MessageDTO> getEventMessages(String eventId) throws BusinessException;

	public ArrayList<EventDTO> searchEvents(String posizione, String categoria, int partecipanti) throws BusinessException;

	public List<PlaceDTO> getPlacesByDescription(String description) throws BusinessException;

	public WriteResultDTO upgradeToPremium(String userId, SubscriptionDTO subscription) throws BusinessException;

	public WriteResultDTO login(String username, String password) throws BusinessException;

	public WriteResultDTO updatePassword(String email, String encodedPassword);

	public WriteResultDTO checkVerificationCode(String email, String verificationcode);

	public WriteResultDTO sendVerificationCode(String email);

	public WriteResultDTO logout(String userId);

	public WriteResultDTO updateUserData(UserDTO user);

	public WriteResultDTO setCurrentPosition(String userId, String eventId, PlaceDTO place);

	public List<EventDTO> getEventsByUser(String userId);

	public UserDTO getUser(int userId);

}
