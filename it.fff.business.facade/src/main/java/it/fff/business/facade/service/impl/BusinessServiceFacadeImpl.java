package it.fff.business.facade.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.EmailInfoBO;
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
import it.fff.business.common.mapper.AccountMapper;
import it.fff.business.common.mapper.AchievementTypeMapper;
import it.fff.business.common.mapper.AttendanceMapper;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.CityMapper;
import it.fff.business.common.mapper.CustomMapper;
import it.fff.business.common.mapper.EmailInfoMapper;
import it.fff.business.common.mapper.EventCategoryMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.business.common.mapper.LanguageMapper;
import it.fff.business.common.mapper.MessageMapper;
import it.fff.business.common.mapper.MessageStandardMapper;
import it.fff.business.common.mapper.NationMapper;
import it.fff.business.common.mapper.PlaceMapper;
import it.fff.business.common.mapper.ProfileImageMapper;
import it.fff.business.common.mapper.ResultMapper;
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
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.integration.facade.exception.IntegrationException;

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
		} catch (IntegrationException e) {
			BusinessException.manageException(e, ErrorCodes.ERR_BUSIN_GETEVENT);
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
	public AuthDataResponseDTO createUser(RegistrationInputDTO registrationInputDTO) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		UserBO userBO = null;
		WriteResultBO writeResultBO = null;
		try {
			userBO = UserMapper.getInstance().mapDTO2BO(registrationInputDTO);
			writeResultBO = userBusinessService.createUser(userBO);
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);			
		}
		AuthDataResponseDTO result = CustomMapper.getInstance().mapWriteResult2AuthData(writeResultBO);
		return result;
	}
	
	@Override
	public AuthDataResponseDTO login(LoginInputDTO loginInfo, String sharedSecretHEX) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		WriteResultBO WriteResultBO = null;
		SessionBO sessionBO = null;
		try {
			
			sessionBO = CustomMapper.getInstance().mapLoginInput2Session(loginInfo);
			sessionBO.setSharedKey(sharedSecretHEX);
			WriteResultBO = securityBusinessService.login(sessionBO);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_LOGIN);
		}
		
		AuthDataResponseDTO result = CustomMapper.getInstance().mapWriteResult2AuthData(WriteResultBO);
		return result;
	}	

	@Override
	public WriteResultDTO updateProfileImage(ProfileImageDTO dto) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		WriteResultBO resultBO = null;
		ProfileImageBO imgBO = null;
		try {
			imgBO = ProfileImageMapper.getInstance().mapDTO2BO(dto);
			resultBO = userBusinessService.updateProfileImage(imgBO);
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_CREATEUSER);
		}
		if(imgBO!=null){
			logger.debug("Operation successful by business layer");
		}
		WriteResultDTO resultDTO = ResultMapper.getInstance().mapBO2DTO(resultBO);
		
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
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES_BYEVENT);
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
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES_BYEVENT);
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_JOINEVENT);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO addFeedback(String attendanceId, FeedbackEnum feedback) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		
		int attendanceIdInt = -1;
		
		WriteResultBO WriteResultBO = null;
		try {
			attendanceIdInt = Integer.valueOf(attendanceId);
			WriteResultBO = eventBusinessService.addFeedback(attendanceIdInt, feedback);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_ADDFEEDBACK);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO cancelAttendance(String eventId, String userId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");

		int eventIdInt =-1;
		int userIdInt = -1;
		WriteResultBO WriteResultBO = null;
		try {
			userIdInt = Integer.valueOf(userId);
			eventIdInt = Integer.valueOf(eventId);
			WriteResultBO = userBusinessService.cancelAttendance(eventIdInt, userIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETEVENTMSG);
		}
		
		List<MessageDTO> messagesDTO= MessageMapper.getInstance().mapBOs2DTOs(messagesBO);
		return messagesDTO;
	}

	@Override
	public List<EventDTO> searchEvents(	String userGpsLat, 
										String userGpsLong, 
										String radiusKm, 
										String desideredGpsLat, 
										String desideredGpsLong,
										String idCategoria, 
										String partecipanti) throws BusinessException {
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");

		double userGpsLatDouble = -1;
		double userGpsLongDouble = -1;
		double desideredGpsLatDouble = -1;
		double desideredGpsLongDouble = -1;
		double radiusKmDouble = -1;
		int idCategoriaInt = -1;
		int partecipantiInt = -1;
		List<EventBO> eventsBO = null;
		try {
			idCategoriaInt = Integer.valueOf(idCategoria);
			userGpsLatDouble = Double.valueOf(userGpsLat);
			userGpsLongDouble = Double.valueOf(userGpsLong);			
			desideredGpsLatDouble = Double.valueOf(desideredGpsLat);
			desideredGpsLongDouble = Double.valueOf(desideredGpsLong);
			radiusKmDouble = Double.valueOf(radiusKm);
			partecipantiInt = Integer.valueOf(partecipanti);
			
			eventsBO = eventBusinessService.searchEvents(
					userGpsLatDouble, 
					userGpsLongDouble, 
					radiusKmDouble, 
					desideredGpsLatDouble, 
					desideredGpsLongDouble, 
					idCategoriaInt,
					partecipantiInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_SEACRHEVENTS);
		}
		
		List<EventDTO> eventsDTO = EventMapper.getInstance().mapBOs2DTOs(eventsBO);
		return eventsDTO;
	}

	@Override
	public List<PlaceDTO> getPlacesByDescription(String description, String gpsLat, String gpsLong) throws BusinessException {
		PlacesBusinessService placesBusinessService = (PlacesBusinessService)BusinessServiceProvider.getBusinessService("placesBusinessService");

		double gpsLatDouble = 0;
		double gpsLongDouble = 0;
		List<PlaceBO> placesBO = null;
		try {
			if(gpsLat!=null && !"".equals(gpsLat) && gpsLong!=null && !"".equals(gpsLong)){
				gpsLatDouble = Double.valueOf(gpsLat);
				gpsLongDouble = Double.valueOf(gpsLong);
			}
			placesBO = placesBusinessService.getPlacesByDescription(description, gpsLatDouble, gpsLongDouble);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC);
		}		
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPGRADE_TO_PREMIUM);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}



	@Override
	public WriteResultDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) throws BusinessException {
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");

		int userId = updatePasswordDTO.getUserId();
		String email = updatePasswordDTO.getEmail();
		String encodedOldPassword = updatePasswordDTO.getOldPassword();
		String encodedNewPassword = updatePasswordDTO.getNewPassword();
		
		WriteResultBO WriteResultBO = null;
		
		try {
			WriteResultBO = securityBusinessService.updatePassword(userId, email, encodedOldPassword, encodedNewPassword);
		}
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_UPDATE_USERDATA);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public WriteResultDTO setCurrentPosition(String userId, PlaceDTO currentPosition) throws BusinessException {
		PlacesBusinessService placesBusinessService = (PlacesBusinessService)BusinessServiceProvider.getBusinessService("placesBusinessService");
		
		WriteResultBO WriteResultBO = null;
		int userIdInt = -1;
		double gpsLat = currentPosition.getGpsLat();
		double gpsLong = currentPosition.getGpsLong();
		try {
			userIdInt = Integer.valueOf(userId);
			WriteResultBO = placesBusinessService.setCurrentPosition(userIdInt, gpsLat, gpsLong);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
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
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_RESET_PASSWORD);
		}
		
		WriteResultDTO result = ResultMapper.getInstance().mapBO2DTO(WriteResultBO);
		return result;
	}

	@Override
	public EmailInfoDTO isExistingEmail(String email) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		EmailInfoDTO resultDTO = null;
		EmailInfoBO resultBO = null;
		try {
			resultBO = userBusinessService.getEmailInfo(email);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_EXISTING_MAIL);
		}
		
		resultDTO = EmailInfoMapper.getInstance().mapBO2DTO(resultBO);
		return resultDTO;
	}

	@Override
	public CityDTO getCityByName(String cityName, String nationKey) throws BusinessException {
		PlacesBusinessService placesBusinessService = (PlacesBusinessService)BusinessServiceProvider.getBusinessService("placesBusinessService");
		
		CityDTO resultDTO = null;
		CityBO resultBO = null;
		try {
			resultBO = placesBusinessService.getCityByName(cityName, nationKey);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETCITY_BY_NAME);
		}
		
		resultDTO = CityMapper.getInstance().mapBO2DTO(resultBO);
		return resultDTO;
	}

	@Override
	public List<FeedbackEnum> getUserFeedbacks(String userId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		List<FeedbackEnum> feedbacks = null;
		int userIdInt =-1;
		try{
			userIdInt = Integer.valueOf(userId);
			feedbacks = userBusinessService.getUserFeedbacks(userIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETFEEDBACKS_BY_USERID);
		}
		
		return feedbacks;
	}

	@Override
	public ProfileImageDTO readProfileImage(String userId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		ProfileImageDTO resultDTO = new ProfileImageDTO();
		ProfileImageBO imgBO = null;
		int userIdInt =-1;
		try{
			userIdInt = Integer.valueOf(userId);
			imgBO = userBusinessService.readProfileImage(userIdInt);
		} 
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_READIMAGE_BY_USERID);
		}
		if(imgBO!=null){
			logger.debug("Operation successful by business layer");
			resultDTO = ProfileImageMapper.getInstance().mapBO2DTO(imgBO);
		}
		
		return resultDTO;
	}

	@Override
	public AuthDataResponseDTO loginFacebook(String code, String deviceId) throws BusinessException {
		logger.debug("loginFacebook...");
		SecurityBusinessService securityBusinessService = (SecurityBusinessService)BusinessServiceProvider.getBusinessService("securityBusinessService");
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		AuthDataResponseDTO authDataRespDTO = null;
		String socialToken = null;
		int socialTokenExpires = -1;
		Long facebookId = null;
		String userEmail = null;
		UserBO userBO = null;
		try {
			String[] accessTokenAndExpires = securityBusinessService.getFacebookToken(code);
			logger.debug("Access token recuperato da facebook: "+accessTokenAndExpires);
			
			socialToken = accessTokenAndExpires[0];
			socialTokenExpires = Integer.valueOf(accessTokenAndExpires[1]);
			
			if(socialToken==null || "".equals(socialToken)){
				throw new IntegrationException("Social Access token NON recuperato!");
			}
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_LOGIN);
		}
		
		try {
			userBO = userBusinessService.getFacebookUserData(socialToken, socialTokenExpires,  deviceId);
			logger.debug("Dati utente recuperati da facebook");
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_LOGIN);
		}
		
		if(userBO!=null){ //Ora che ho i dati utente presi da facebook, lo registro/aggiorno su DB
			
			WriteResultBO writeResultBO = null;
			try {
				//Controllo se l'utente gi� risulta registrato
				boolean isRegistered = false;
				
				userEmail = userBO.getAccount().getEmail();
				facebookId = userBO.getAccount().getFacebookId();

				AccountBO userCurrentAccount = null;
				if(userEmail!=null && !"".equals(userEmail)){
					userCurrentAccount = userBusinessService.getUserAccountByEmail(userEmail);
					if(userCurrentAccount!=null && userCurrentAccount.getUserId()>-1){
						logger.debug("Utente gia' registrato con questa email su DB: "+userEmail);
						isRegistered = true;
						userBO.setId(userCurrentAccount.getId());
					}
				}
				else{
					userCurrentAccount = userBusinessService.getUserAccountByFacebookId(facebookId);
					if(userCurrentAccount!=null && userCurrentAccount.getUserId()>-1){
						logger.debug("FacebookId gia' presente su DB");
						isRegistered = true;
						userBO.setId(userCurrentAccount.getId());
					}
				}
				
				

				userBO.setFlagAttivo(true);//la garanzia e' data da facebook
				userBO.getAccount().setFlgVerificato(true);  //la garanzia e' data da facebook
				
				if(!isRegistered){
					writeResultBO = userBusinessService.createUser(userBO);
					isRegistered=true;
					logger.debug("Nuovo utente creato su DB a partire dai dati facebook");
				}
				else{//Se l'utente gi� esiste, faccio un update dei suoi dati (ho recuperato il suo ID grazie al facebookId)
					
					WriteResultBO logoutResult = securityBusinessService.logout(userBO.getId(), deviceId); //Faccio logout da tutte le sessioni precedenti
					if(logoutResult.isSuccess()){
						logger.debug("Utente sloggato da tutte le sessioni prima di nuovo login facebook");
					}
					
					writeResultBO = userBusinessService.updateUserData(userBO); //poi aggiorno i dati utente (compreso il suo account e nuova sessione)
					logger.debug("Utente esistente aggiornato su DB a partire dai dati facebook");
				}
				
			} catch (IntegrationException e) {
				BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_LOGIN_FACEBOOK);			
			}
			
			authDataRespDTO = CustomMapper.getInstance().mapWriteResult2AuthData(writeResultBO);			
			authDataRespDTO.setSocialToken(socialToken);
			authDataRespDTO.setSocialId(String.valueOf(facebookId));
			authDataRespDTO.setSocialTokenExpires(socialTokenExpires);
			logger.debug("...loginFacebook");
		}
		
		return authDataRespDTO;
	}

	@Override
	public UserDTO getFacebookUserData(String socialToken, int socialTokenExpires, String deviceId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		UserDTO dto = null;
		UserBO bo = null;

		try{
			 bo = userBusinessService.getFacebookUserData(socialToken, socialTokenExpires, deviceId);
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GET_FB_USERDATA);
		}
		
		dto  = UserMapper.getInstance().mapBO2DTO(bo);
		
		return dto;
	}

	@Override
	public List<AttendanceDTO> getAttendancesByUser(String userId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		List<AttendanceBO> attendancesBO = null;
		int userIdInt =-1;
		try{
			userIdInt = Integer.valueOf(userId);
			attendancesBO = userBusinessService.getAttendancesByUser(userIdInt);
		}
		catch(NumberFormatException e){
			BusinessException.manageException(new ApplicationException(e),ErrorCodes.ERR_BUSIN_GENERIC_ID_NOT_VALID);
		} catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_GETATTENDANCES_BYUSER);
		}
		
		List<AttendanceDTO> attendancesDTO = null;
		attendancesDTO = AttendanceMapper.getInstance().mapBOs2DTOs(attendancesBO);

		return attendancesDTO;
	}

	@Override
	public AccountDTO getUserAccountByEmail(String email) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		AccountDTO resultDTO = null;
		AccountBO resultBO = null;
		try {
			resultBO = userBusinessService.getUserAccountByEmail(email);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_EXISTING_MAIL);
		}
		
		resultDTO = AccountMapper.getInstance().mapBO2DTO(resultBO);
		return resultDTO;
	}

	@Override
	public AccountDTO getUserAccountByFacebookId(String facebookId) throws BusinessException {
		UserBusinessService userBusinessService = (UserBusinessService)BusinessServiceProvider.getBusinessService("userBusinessService");
		
		AccountDTO resultDTO = null;
		AccountBO resultBO = null;
		long facebookIdLong = -1;
		try {
			facebookIdLong = Long.valueOf(facebookId);
			resultBO = userBusinessService.getUserAccountByFacebookId(facebookIdLong);
		}
		catch (IntegrationException e) {
			BusinessException.manageException(e,ErrorCodes.ERR_BUSIN_EXISTING_MAIL);
		}
		
		resultDTO = AccountMapper.getInstance().mapBO2DTO(resultBO);
		return resultDTO;
	}


}
