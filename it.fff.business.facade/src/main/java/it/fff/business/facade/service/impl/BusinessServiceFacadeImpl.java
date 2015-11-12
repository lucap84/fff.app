package it.fff.business.facade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.exception.ApplicationException;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.ResultMapper;
import it.fff.business.common.mapper.SubscriptionMapper;
import it.fff.business.common.mapper.UserMapper;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.business.service.EventBusinessService;
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
		EventMapper mapper = new EventMapper();
		EventDTO dtoResult = mapper.mapBo2Dto(eventBO);
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
			UserMapper mapper = new UserMapper();
			userBO = mapper.mapDto2Bo(userDTO);
			userBO = userBusinessService.createUser(userBO);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);			
		}
		if(userBO!=null){
			logger.debug("User successfully retrieved by business layer");
		}
		UserMapper mapper = new UserMapper();
		UserDTO dtoResult = mapper.mapBo2Dto(userBO);
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
			UserMapper mapper = new UserMapper();
			imgBO = mapper.mapProfileImageDto2Bo(dto);
			imgBO = userBusinessService.updateProfileImage(imgBO);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);
		}
		if(imgBO!=null){
			logger.debug("Operation successful by business layer");
		}
		UserMapper mapper = new UserMapper();
		ProfileImageDTO dtoResult = mapper.mapProfileImageBo2Dto(imgBO);
		
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
			ApplicationException ae = new ApplicationException(e);
			BusinessException.manageException(ae,ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES);
		}
		
		List<AttendanceDTO> attendancesDTO = null;
		AttendanceMapper mapper = new AttendanceMapper();
		attendancesDTO = mapper.bo2dto(attendancesBO);

		return attendancesDTO;
	}

	@Override
	public WriteResultDTO createEvent(EventDTO eventToCreate) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		EventMapper mapper = new EventMapper();
		EventBO bo = mapper.mapDto2Bo(eventToCreate);
		CreateResultBO createResultBO = null;
		try {
			createResultBO = eventBusinessService.createEvent(bo);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES);
		}
		
		ResultMapper resMapper = new ResultMapper();
		WriteResultDTO result = resMapper.map2Dto(createResultBO);
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
			ApplicationException ae = new ApplicationException(e);
			BusinessException.manageException(ae,ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CANCELEVENT);
		}
		
		ResultMapper resMapper = new ResultMapper();
		WriteResultDTO result = resMapper.map2Dto(updateResultBO);
		return result;
	}

	@Override
	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		AttendanceDTO createdAttendance = new AttendanceDTO();
		createdAttendance.setId("1");
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier(createdAttendance.getId());
		return result;
	}

	@Override
	public WriteResultDTO addFeedback(AttendanceDTO attendance) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		//feedback saved, return confirm
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier(String.valueOf(attendance.getId()));
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
			ApplicationException ae = new ApplicationException(e);
			BusinessException.manageException(ae,ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CANCELATTENDANCES);
		}
		
		ResultMapper resMapper = new ResultMapper();
		WriteResultDTO result = resMapper.map2Dto(updateResultBO);
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
			ApplicationException ae = new ApplicationException(e);
			BusinessException.manageException(ae,ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_POSTMSG);
		}
		
		ResultMapper resMapper = new ResultMapper();
		WriteResultDTO result = resMapper.map2Dto(createResultBO);
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
			ApplicationException ae = new ApplicationException(e);
			BusinessException.manageException(ae,ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_POSTSTDMSG);
		}
		
		ResultMapper resMapper = new ResultMapper();
		WriteResultDTO result = resMapper.map2Dto(createResultBO);
		return result;
	}

	@Override
	public ArrayList<MessageDTO> getEventMessages(String eventId) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		ArrayList<MessageDTO> messages = new ArrayList<MessageDTO>();
		MessageDTO m1 = new MessageDTO();
		m1.setId("1");
		m1.setText("testo1 testo1");
		MessageDTO m2 = new MessageDTO();
		m2.setId("2");
		m2.setText("testo2 testo2");
		messages.add(m1);
		messages.add(m2);
		return messages;
	}

	@Override
	public ArrayList<EventDTO> searchEvents(String posizione, String categoria, int partecipanti) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		ArrayList<EventDTO> events = new ArrayList<EventDTO>();
		EventDTO e1 = new EventDTO();
		e1.setEventId("999");
		events.add(e1);
		return events;
	}

	@Override
	public List<PlaceDTO> getPlacesByDescription(String description) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		PlaceDTO dto1 = new PlaceDTO();
		dto1.setNome("place1");
		dto1.setGpsLat("001");
		dto1.setGpsLat("002");
		
		PlaceDTO dto2 = new PlaceDTO();
		dto1.setNome("place2");
		dto1.setGpsLat("003");
		dto1.setGpsLat("004");		
		
		List<PlaceDTO> places = new ArrayList<PlaceDTO>();
		places.add(dto1);
		places.add(dto2);
		return places;
	}

	@Override
	public WriteResultDTO upgradeToPremium(String userId, SubscriptionDTO subscription) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		int userIdInt = -1;
		CreateResultBO createResultBO = null;
		
		SubscriptionMapper mapperInput = new SubscriptionMapper();
		SubscriptionBO subscriptionBO = mapperInput.map(subscription);
		try {
			userIdInt = Integer.valueOf(userId);
			createResultBO = userBusinessService.upgradeToPremium(userIdInt, subscriptionBO);
		}
		catch(NumberFormatException e){
			ApplicationException ae = new ApplicationException(e);
			BusinessException.manageException(ae,ErrorCodes.ERR_BUSIN_ID_FORMAT_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPGRADE_TO_PREMIUM);
		}
		
		ResultMapper resMapper = new ResultMapper();
		WriteResultDTO result = resMapper.map2Dto(createResultBO);
		return result;
	}

	@Override
	public WriteResultDTO login(String username, String password) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier("1");
		return result;
	}

	@Override
	public WriteResultDTO updatePassword(String email, String encodedPassword) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier("1");
		return result;
	}

	@Override
	public WriteResultDTO checkVerificationCode(String email, String verificationcode) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier("1");
		return result;
	}

	@Override
	public WriteResultDTO sendVerificationCode(String email) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier("1");
		return result;
	}

	@Override
	public WriteResultDTO logout(String userId) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setAffectedRecords(1);
		result.setOk(true);
		result.setIdentifier("1");
		return result;
	}

	@Override
	public WriteResultDTO updateUserData(UserDTO user) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(user.getId());
		return result;
	}

	@Override
	public WriteResultDTO setCurrentPosition(String userId, String eventId, PlaceDTO place) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(userId);
		return result;
	}

	@Override
	public List<EventDTO> getEventsByUser(String userId) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		ArrayList<EventDTO> events = new ArrayList<EventDTO>();
		EventDTO e1 = new EventDTO();
		e1.setEventId("1");
		events.add(e1);
		return events;
	}

	@Override
	public UserDTO getUser(int userId) {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		UserDTO user = new UserDTO();
		user.setId("1");
		return user;
	}


}
