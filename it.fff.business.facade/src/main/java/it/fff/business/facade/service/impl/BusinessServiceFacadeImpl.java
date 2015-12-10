package it.fff.business.facade.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
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
import it.fff.business.common.exception.ApplicationException;
import it.fff.business.common.mapper.AchievementTypeMapper;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.CustomMapper;
import it.fff.business.common.mapper.EventCategoryMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.business.common.mapper.LanguageMapper;
import it.fff.business.common.mapper.MessageMapper;
import it.fff.business.common.mapper.MessageStandardMapper;
import it.fff.business.common.mapper.NationMapper;
import it.fff.business.common.mapper.PlaceMapper;
import it.fff.business.common.mapper.ResultMapper;
import it.fff.business.common.mapper.SessionMapper;
import it.fff.business.common.mapper.SubscriptionMapper;
import it.fff.business.common.mapper.SubscriptionTypeMapper;
import it.fff.business.common.mapper.UserMapper;
import it.fff.business.common.util.ErrorCodes;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.business.service.EventBusinessService;
import it.fff.business.service.PlacesBusinessService;
import it.fff.business.service.PremiumBusinessService;
import it.fff.business.service.SecurityBusinessService;
import it.fff.business.service.TypologicalBusinessService;
import it.fff.business.service.UserBusinessService;
import it.fff.business.util.BusinessServiceProvider;
import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
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
		EventDTO dtoResult = EventMapper.getInstance().mapBO2DTO(eventBO);
		if(dtoResult!=null){
			logger.debug("Mapping bo2dto completed");
		}
		return dtoResult;
	}

	@Override
	public AuthDataResponseDTO createUser(RegistrationDataRequestDTO registrationDataDTO) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		UserBO userBO = null;
		WriteResultBO WriteResultBO = null;
		try {
			userBO = UserMapper.getInstance().mapDTO2BO(registrationDataDTO);
			WriteResultBO = userBusinessService.createUser(userBO);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);			
		}
		AuthDataResponseDTO result = CustomMapper.mapWriteResult2AuthData(WriteResultBO);
		return result;
	}
	
	@Override
	public AuthDataResponseDTO login(SessionDTO sessionToCreate, String sharedSecretHEX) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		WriteResultBO WriteResultBO = null;
		SessionBO sessionBO = null;
		try {
			sessionBO = SessionMapper.getInstance().mapDTO2BO(sessionToCreate);
			sessionBO.setSharedKey(sharedSecretHEX);
			WriteResultBO = securityBusinessService.login(sessionBO);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_LOGIN);
		}
		
		AuthDataResponseDTO result = CustomMapper.mapWriteResult2AuthData(WriteResultBO);
		return result;
	}	

	@Override
	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultDTO resultDTO = new WriteResultDTO();
		ProfileImageBO imgBO = null;
		try {
			imgBO = UserMapper.getInstance().mapDTO2BO(dto);
			imgBO = userBusinessService.updateProfileImage(imgBO);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);
		}
		if(imgBO!=null){
			logger.debug("Operation successful by business layer");
		}
		ProfileImageDTO dtoResult = UserMapper.getInstance().mapBO2DTO(imgBO);
		
		if(dtoResult!=null){
//			resultDTO.setOk(true);
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
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES);
		}
		
		List<AttendanceDTO> attendancesDTO = null;
		attendancesDTO = AttendanceMapper.getInstance().mapBOs2DTOs(attendancesBO);

		return attendancesDTO;
	}

	@Override
	public WriteResultDTO createEvent(EventDTO eventToCreate) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		EventBO bo = EventMapper.getInstance().mapDTO2BO(eventToCreate);
		WriteResultBO WriteResultBO = null;
		try {
			WriteResultBO = eventBusinessService.createEvent(bo);
		} catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO cancelEvent(String eventId, String organizerId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		int eventIdInt =-1;
		int organizerIdInt =-1;
		
		WriteResultBO WriteResultBO = null;
		try {
			eventIdInt = Integer.valueOf(eventId);
			organizerIdInt = Integer.valueOf(organizerId);
			WriteResultBO = eventBusinessService.cancelEvent(eventIdInt, organizerIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CANCELEVENT);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		WriteResultBO WriteResultBO = null;
		AttendanceBO bo = AttendanceMapper.getInstance().mapDTO2BO(attendanceToCreate);
		try {
			WriteResultBO = eventBusinessService.joinEvent(bo);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_JOINEVENT);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO addFeedback(AttendanceDTO attendance) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		WriteResultBO WriteResultBO = null;
		AttendanceBO bo = AttendanceMapper.getInstance().mapDTO2BO(attendance);
		try {
			WriteResultBO = eventBusinessService.addFeedback(bo);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_ADDFEEDBACK);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO cancelAttendance(String eventId, String attendanceId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int eventIdInt =-1;
		int attendanceIdInt = -1;
		WriteResultBO WriteResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceIdInt);
			eventIdInt = Integer.valueOf(eventId);
			WriteResultBO = eventBusinessService.cancelAttendance(eventIdInt, attendanceIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CANCELATTENDANCES);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO postEventMessage(String attendanceId, String message) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int attendanceIdInt = -1;
		WriteResultBO WriteResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceId);
			WriteResultBO = eventBusinessService.postEventMessage(attendanceIdInt, message);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_POSTMSG);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO postStandardEventMessage(String attendanceId, String stdMsgId) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		int attendanceIdInt = -1;
		int stdMsgIdInt = -1;
		WriteResultBO WriteResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceId);
			stdMsgIdInt = Integer.valueOf(stdMsgId);
			WriteResultBO = eventBusinessService.postStandardEventMessage(attendanceIdInt, stdMsgIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_POSTSTDMSG);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
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
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETEVENTMSG);
		}
		
		List<MessageDTO> messagesDTO= MessageMapper.getInstance().mapBOs2DTOs(messagesBO);
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
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEACRHEVENTS);
		}
		
		List<EventDTO> eventsDTO = EventMapper.getInstance().mapBOs2DTOs(eventsBO);
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
		
		List<PlaceDTO> placesDTO = PlaceMapper.getInstance().mapBOs2DTOs(placesBO);
		
		
		return placesDTO;
	}

	@Override
	public WriteResultDTO upgradeToPremium(String userId, SubscriptionDTO subscription) throws BusinessException {
		PremiumBusinessService premiumBusinessService = (PremiumBusinessService)BusinessServiceProvider.getBusinessService("premiumBusinessService");
		
		int userIdInt = -1;
		WriteResultBO WriteResultBO = null;
		
		SubscriptionBO subscriptionBO = SubscriptionMapper.getInstance().mapDTO2BO(subscription);
		try {
			userIdInt = Integer.valueOf(userId);
			WriteResultBO = premiumBusinessService.upgradeToPremium(userIdInt, subscriptionBO);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPGRADE_TO_PREMIUM);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}



	@Override
	public WriteResultDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		String userId = updatePasswordDTO.getUserId();
		String email = updatePasswordDTO.getEmail();
		String encodedOldPassword = updatePasswordDTO.getOldPassword();
		String encodedNewPassword = updatePasswordDTO.getNewPassword();
		
		int userIdInt = -1;
		WriteResultBO WriteResultBO = null;
		
		try {
			userIdInt = Integer.valueOf(userId);
			WriteResultBO = securityBusinessService.updatePassword(userIdInt, email, encodedOldPassword, encodedNewPassword);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPDATEPSW);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;	
	}

	@Override
	public WriteResultDTO checkVerificationCode(String email, String verificationcode) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		WriteResultBO WriteResultBO = null;
		
		try {
			WriteResultBO = securityBusinessService.checkVerificationCode(email, verificationcode);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CHECK_VERIFICATIONCODE);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO sendVerificationCode(String email) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		WriteResultBO WriteResultBO = null;
		
		try {
			WriteResultBO = securityBusinessService.generateAndSendVerficationCode(email);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEND_VERIFICATIONCODE);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO logout(String userId, String deviceId) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		int userIdInt = -1;
		WriteResultBO WriteResultBO = null;
		try {
			userIdInt = Integer.valueOf(userId);
			WriteResultBO = securityBusinessService.logout(userIdInt, deviceId);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEND_VERIFICATIONCODE);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO updateUserData(UserDTO user) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		WriteResultBO WriteResultBO = null;
		
		UserBO userBO = UserMapper.getInstance().mapDTO2BO(user);
		try {
			WriteResultBO = userBusinessService.updateUserData(userBO);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPDATE_USERDATA);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO setCurrentPosition(String userId, String eventId, PlaceDTO placeDTO) throws BusinessException {
		PlacesBusinessService placesBusinessService = (PlacesBusinessService)BusinessServiceProvider.getBusinessService("placesBusinessService");
		
		WriteResultBO WriteResultBO = null;
		int userIdInt = -1;
		int eventIdInt = -1;
		PlaceBO placeBO = PlaceMapper.getInstance().mapDTO2BO(placeDTO);
		try {
			userIdInt = Integer.valueOf(userId);
			eventIdInt = Integer.valueOf(eventId);
			WriteResultBO = placesBusinessService.setCurrentPosition(userIdInt, eventIdInt, placeBO);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SET_CURRENTPOSITION);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
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
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GET_EVENTSBYUSER);
		}
		
		List<EventDTO> eventsDTO = EventMapper.getInstance().mapBOs2DTOs(eventsBO);
		
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
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GET_USER);
		}
		
		UserDTO userDTO = UserMapper.getInstance().mapBO2DTO(userBO);
		
		return userDTO;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");
		
		Map<Integer, Map<String, String>> clientSecrets = null;
		try {
			clientSecrets = securityBusinessService.retrieveClientSecrets();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}
		
		return clientSecrets;
	}

	@Override
	public List<LanguageDTO> getAllLanguages() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<LanguageDTO> languagesDTO = null;
		List<LanguageBO> languagesBO = null;
		try {
			languagesBO = typologicalBusinessService.getAllLanguages();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		languagesDTO = LanguageMapper.getInstance().mapBOs2DTOs(languagesBO);
		
		return languagesDTO;
	}

	@Override
	public List<SubscriptionTypeDTO> getAllSubscriptionTypes() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<SubscriptionTypeDTO> dtos = null;
		List<SubscriptionTypeBO> bos = null;
		try {
			bos = typologicalBusinessService.getAllSubscriptionTypes();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = SubscriptionTypeMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public List<AchievementTypeDTO> getAllAchievementTypes() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<AchievementTypeDTO> dtos = null;
		List<AchievementTypeBO> bos = null;
		try {
			bos = typologicalBusinessService.getAllAchievementTypes();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = AchievementTypeMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public List<MessageStandardDTO> getAllStandardMessages() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<MessageStandardDTO> dtos = null;
		List<MessageStandardBO> bos = null;
		try {
			bos = typologicalBusinessService.getAllStandardMessages();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = MessageStandardMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public List<AttendanceStateEnum> getAllAttendanceStates() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<AttendanceStateEnum> dtos = null;
		List<AttendanceStateEnum> bos = null;
		try {
			bos = typologicalBusinessService.getAllAttendanceStates();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = AttendanceStateMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<EventStateEnum> dtos = null;
		List<EventStateEnum> bos = null;
		try {
			bos = typologicalBusinessService.getAllEventStates();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = EventStateMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public List<EventCategoryDTO> getAllEventCategories() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<EventCategoryDTO> dtos = null;
		List<EventCategoryBO> bos = null;
		try {
			bos = typologicalBusinessService.getAllEventCategories();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = EventCategoryMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public List<NationDTO> getAllNations() throws BusinessException {
		TypologicalBusinessService typologicalBusinessService = (TypologicalBusinessService)BusinessServiceProvider.getBusinessService("typologicalBusinessService");
		List<NationDTO> dtos = null;
		List<NationBO> bos = null;
		try {
			bos = typologicalBusinessService.getAllNations();
		}
		catch (PersistenceException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		dtos = NationMapper.getInstance().mapBOs2DTOs(bos);
		
		return dtos;
	}

	@Override
	public WriteResultDTO resetPassword(ResetPasswordDTO resetPasswordDTO) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");
		
		String email = resetPasswordDTO.getEmail();
		String newPassword = resetPasswordDTO.getNewPassword();
		String verificationCode = resetPasswordDTO.getVerificationCode();

		WriteResultBO WriteResultBO = null;
		try {
			WriteResultBO = securityBusinessService.resetPassword(email, newPassword, verificationCode);
		}
		catch (PersistenceException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_RESET_PASSWORD);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}


}
