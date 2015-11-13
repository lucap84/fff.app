package it.fff.business.facade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.exception.ApplicationException;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.MessageMapper;
import it.fff.business.common.mapper.PlacesMapper;
import it.fff.business.common.mapper.ResultMapper;
import it.fff.business.common.mapper.SubscriptionMapper;
import it.fff.business.common.mapper.UserMapper;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.business.service.EventBusinessService;
import it.fff.business.service.PlacesBusinessService;
import it.fff.business.service.SecurityBusinessService;
import it.fff.business.service.UserBusinessService;
import it.fff.business.util.BusinessServiceProvider;
import it.fff.clientserver.common.dto.*;
import it.fff.persistence.facade.exception.PersistenceException;

public class BusinessServiceFacadeImpl implements BusinessServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(BusinessServiceFacadeImpl.class);
	
	public BusinessServiceFacadeImpl(){
		
	}

	@Override
	public EventDTO getEvent(int eventId) throws BusinessException {
		EventBO eventBO = null;
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		try {
			eventBO = eventBusinessService.getEvent(eventId);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETEVENT);
		}
		if(eventBO!=null){
			logger.debug("Event successfully retrieved by business layer");
		}
		EventDTO dtoResult = EventMapper.map2DTO(eventBO);
		if(dtoResult!=null){
			logger.debug("Mapping bo2dto completed");
		}
		return dtoResult;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		UserBO userBO = null;
		try {
			userBO = UserMapper.map2BO(userDTO);
			userBO = userBusinessService.createUser(userBO);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);			
		}
		if(userBO!=null){
			logger.debug("User successfully retrieved by business layer");
		}
		UserMapper mapper = new UserMapper();
		UserDTO dtoResult = mapper.map2DTO(userBO);
		if(dtoResult!=null){
			logger.debug("Mapping bo2dto completed");
		}
		return dtoResult;
	}

	@Override
	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO resultDTO = new WriteResultDTO();
		ProfileImageBO imgBO = null;
		try {
			imgBO = UserMapper.map2BO(dto);
			imgBO = userBusinessService.updateProfileImage(imgBO);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);
		}
		if(imgBO!=null){
			logger.debug("Operation successful by business layer");
		}
		ProfileImageDTO dtoResult = UserMapper.map2DTO(imgBO);
		
		if(dtoResult!=null){
			resultDTO.setOk(true);
			resultDTO.setAffectedRecords(1);
			resultDTO.setIdentifier(dtoResult.getImgHashCode());
			logger.debug("Mapping bo2dto completed");
		}
		return resultDTO;
	}

	@Override
	public List<AttendanceDTO> getAttendancesByEvent(String eventId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		List<AttendanceBO> attendancesBO = null;
		int eventIdInt =-1;
		try{
			eventIdInt = Integer.valueOf(eventId);
			attendancesBO = eventBusinessService.getAttendancesByEvent(eventIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES);
		}
		
		List<AttendanceDTO> attendancesDTO = null;
		attendancesDTO = AttendanceMapper.map2DTO(attendancesBO);

		return attendancesDTO;
	}

	@Override
	public WriteResultDTO createEvent(EventDTO eventToCreate) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		EventBO bo = EventMapper.map2BO(eventToCreate);
		CreateResultBO createResultBO = null;
		try {
			createResultBO = eventBusinessService.createEvent(bo);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(createResultBO);
		return result;
	}

	@Override
	public WriteResultDTO cancelEvent(String eventId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		int eventIdInt =-1;
		UpdateResultBO updateResultBO = null;
		try {
			eventIdInt = Integer.valueOf(eventId);
			updateResultBO = eventBusinessService.cancelEvent(eventIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CANCELEVENT);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		CreateResultBO createResultBO = null;
		AttendanceBO bo = AttendanceMapper.map2BO(attendanceToCreate);
		try {
			createResultBO = eventBusinessService.joinEvent(bo);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_JOINEVENT);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(createResultBO);
		return result;
	}

	@Override
	public WriteResultDTO addFeedback(AttendanceDTO attendance) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		CreateResultBO createResultBO = null;
		AttendanceBO bo = AttendanceMapper.map2BO(attendance);
		try {
			createResultBO = eventBusinessService.addFeedback(bo);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_ADDFEEDBACK);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(createResultBO);
		return result;
	}

	@Override
	public WriteResultDTO cancelAttendance(String eventId, String attendanceId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int eventIdInt =-1;
		int attendanceIdInt = -1;
		UpdateResultBO updateResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceIdInt);
			eventIdInt = Integer.valueOf(eventId);
			updateResultBO = eventBusinessService.cancelAttendance(eventIdInt, attendanceIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CANCELATTENDANCES);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO postEventMessage(String attendanceId, String message) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int attendanceIdInt = -1;
		CreateResultBO createResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceId);
			createResultBO = eventBusinessService.postEventMessage(attendanceIdInt, message);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_POSTMSG);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(createResultBO);
		return result;
	}

	@Override
	public WriteResultDTO postStandardEventMessage(String attendanceId, String stdMsgId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int attendanceIdInt = -1;
		int stdMsgIdInt = -1;
		CreateResultBO createResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceId);
			stdMsgIdInt = Integer.valueOf(stdMsgId);
			createResultBO = eventBusinessService.postStandardEventMessage(attendanceIdInt, stdMsgIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_POSTSTDMSG);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(createResultBO);
		return result;
	}

	@Override
	public List<MessageDTO> getEventMessages(String eventId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int eventIdInt = -1;
		List<MessageBO> messagesBO = null;
		try {
			eventIdInt = Integer.valueOf(eventId);
			messagesBO = eventBusinessService.getEventMessages(eventIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETEVENTMSG);
		}
		
		List<MessageDTO> messagesDTO= MessageMapper.map2DTO(messagesBO);
		return messagesDTO;
	}

	@Override
	public List<EventDTO> searchEvents(String gpsLat, String gpsLong, String idCategoria, String partecipanti) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		double gpsLatDouble = -1;
		double gpsLongDouble = -1;
		int idCategoriaInt = -1;
		int partecipantiInt = -1;
		List<EventBO> eventsBO = null;
		try {
			idCategoriaInt = Integer.valueOf(idCategoria);
			gpsLatDouble = Double.valueOf(gpsLat);
			gpsLongDouble = Double.valueOf(gpsLong);
			partecipantiInt = Integer.valueOf(partecipanti);
			eventsBO = eventBusinessService.searchEvents(gpsLatDouble, gpsLongDouble, idCategoriaInt, partecipantiInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEACRHEVENTS);
		}
		
		List<EventDTO> eventsDTO = EventMapper.map2DTO(eventsBO);
		return eventsDTO;
	}

	@Override
	public List<PlaceDTO> getPlacesByDescription(String description) throws BusinessException {
		PlacesBusinessService placesBusinessService = (PlacesBusinessService)BusinessServiceProvider.getBusinessService("placesBusinessService");

		List<PlaceBO> placesBO = null;
		try {
			placesBO = placesBusinessService.getPlacesByDescription(description);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETPLACES);
		}
		
		List<PlaceDTO> placesDTO = PlacesMapper.map2DTO(placesBO);
		
		
		return placesDTO;
	}

	@Override
	public WriteResultDTO upgradeToPremium(String userId, SubscriptionDTO subscription) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		int userIdInt = -1;
		CreateResultBO createResultBO = null;
		
		SubscriptionBO subscriptionBO = SubscriptionMapper.map2BO(subscription);
		try {
			userIdInt = Integer.valueOf(userId);
			createResultBO = userBusinessService.upgradeToPremium(userIdInt, subscriptionBO);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPGRADE_TO_PREMIUM);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(createResultBO);
		return result;
	}

	@Override
	public WriteResultDTO login(String username, String password) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		UpdateResultBO updateResultBO = null;
		
		try {
			updateResultBO = securityBusinessService.login(username, password);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_LOGIN);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO updatePassword(String email, String encodedPassword) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		UpdateResultBO updateResultBO = null;
		
		try {
			updateResultBO = securityBusinessService.updatePassword(email, encodedPassword);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPDATEPSW);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;	
	}

	@Override
	public WriteResultDTO checkVerificationCode(String email, String verificationcode) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		UpdateResultBO updateResultBO = null;
		
		try {
			updateResultBO = securityBusinessService.checkVerificationCode(email, verificationcode);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CHECK_VERIFICATIONCODE);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO sendVerificationCode(String email) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		UpdateResultBO updateResultBO = null;
		
		try {
			updateResultBO = securityBusinessService.generateAndVerificationCode(email);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEND_VERIFICATIONCODE);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO logout(String userId) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		int userIdInt = -1;
		UpdateResultBO updateResultBO = null;
		try {
			userIdInt = Integer.valueOf(userId);
			updateResultBO = securityBusinessService.logout(userIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEND_VERIFICATIONCODE);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO updateUserData(UserDTO user) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		UpdateResultBO updateResultBO = null;
		
		UserBO userBO = UserMapper.map2BO(user);
		try {
			updateResultBO = userBusinessService.updateUserData(userBO);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPDATE_USERDATA);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO setCurrentPosition(String userId, String eventId, PlaceDTO placeDTO) throws BusinessException {
		PlacesBusinessService placesBusinessService = (PlacesBusinessService)BusinessServiceProvider.getBusinessService("placesBusinessService");
		
		UpdateResultBO updateResultBO = null;
		int userIdInt = -1;
		int eventIdInt = -1;
		PlaceBO placeBO = PlacesMapper.map2BO(placeDTO);
		try {
			userIdInt = Integer.valueOf(userId);
			eventIdInt = Integer.valueOf(eventId);
			updateResultBO = placesBusinessService.setCurrentPosition(userIdInt, eventIdInt, placeBO);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SET_CURRENTPOSITION);
		}
		
		WriteResultDTO result = ResultMapper.map2DTO(updateResultBO);
		return result;
	}

	@Override
	public List<EventDTO> getEventsByUser(String userId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		List<EventBO> eventsBO = null;
		int userIdInt = -1;
		try {
			userIdInt = Integer.valueOf(userId);
			eventsBO = eventBusinessService.getEventsByUser(userIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GET_EVENTSBYUSER);
		}
		
		List<EventDTO> eventsDTO = EventMapper.map2DTO(eventsBO);
		
		return eventsDTO;
	}

	@Override
	public UserDTO getUser(int userId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		UserBO userBO = null;
		int userIdInt = -1;
		try {
			userIdInt = Integer.valueOf(userId);
			userBO = userBusinessService.getUser(userIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GET_USER);
		}
		
		UserDTO userDTO = UserMapper.map2DTO(userBO);
		
		return userDTO;
	}


}
