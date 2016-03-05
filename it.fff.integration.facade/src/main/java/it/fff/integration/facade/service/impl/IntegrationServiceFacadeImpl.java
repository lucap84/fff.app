package it.fff.integration.facade.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.*;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.business.common.util.ErrorCodes;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.external.service.PlacesExternalService;
import it.fff.external.util.ExternalServiceProvider;
import it.fff.integration.facade.exception.IntegrationException;
import it.fff.integration.facade.service.IntegrationServiceFacade;
import it.fff.persistence.service.*;
import it.fff.persistence.util.PersistenceServiceProvider;

public class IntegrationServiceFacadeImpl implements IntegrationServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(IntegrationServiceFacadeImpl.class);

	@Override
	public EventBO retrieveEvent(int eventId) throws IntegrationException{
		logger.debug("retrieving event ({}) ...",eventId);
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");

		EventBO eventBO = null;
		try{
			eventBO = eventPersistenceService.retrieveEvent(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		return eventBO;
	}

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws IntegrationException {
		logger.debug("aggiornando img utente ...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		ProfileImageBO boOutput = null;
		try{
			boOutput = userPersistenceService.updateProfileImage(imgBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		if(boOutput!=null){
			logger.debug("user img created");
		}
		return boOutput;
	}

	@Override
	public WriteResultBO createEvent(EventBO bo) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createEvent(bo);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.cancelEvent(eventId, organizerId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws IntegrationException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = userPersistenceService.cancelAttendance(eventId,userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO createEventMessage(int attendanceId, String message) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createEventMessage(attendanceId, message);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createStandardEventMessage(attendanceId, stdMsgId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO joinEvent(AttendanceBO bo) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createAttandance(bo);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.addFeedback(attendanceId, feedback);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<MessageBO> bos = null;
		try{
			bos = eventPersistenceService.getEventMessages(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;			
	}

	@Override
	public List<EventBO> searchEvents(double gpsLatFrom,double gpsLatTo, double gpsLongFrom, double gpsLongTo, int idCategoria, int minPartecipanti) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");

		List<EventBO> bos = null;
		try{
			bos = eventPersistenceService.searchEvents(gpsLatFrom, gpsLatTo, gpsLongFrom, gpsLongTo, idCategoria, minPartecipanti);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;	
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<EventBO> bos = null;
		try{
			bos = eventPersistenceService.getEventsByUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;		
		
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws IntegrationException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<AttendanceBO> bos = null;
		try{
			bos = eventPersistenceService.getAttendancesByEvent(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		return bos;			
	}
	
	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws IntegrationException {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = placesPersistenceService.setCurrentPosition(userId,eventId,placeBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws IntegrationException {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		PlacesExternalService placesExternalService = (PlacesExternalService)ExternalServiceProvider.getExternalService("placesExternalService");
		
		List<PlaceBO> bos = null;
		
		try{
			//Search on FLOKKER Database
			bos = placesPersistenceService.getPlacesByDescription(token, gpsLat, gpsLong);	
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		
		int ttlDays = Integer.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_GOOGLE_TTL));
		
		boolean flokkerPlacesAreValid = false;
		
		if(bos!=null && bos.size()>0){
			flokkerPlacesAreValid = true;
			for (PlaceBO placeBO : bos) {
				String dataAggiornamento = placeBO.getDataAggiornamento();
				
				if(!this.isPlaceStillValid(dataAggiornamento, ttlDays)){
					flokkerPlacesAreValid = false;
					break;
				}
			}
		}
		
		
		if(flokkerPlacesAreValid){
			return bos;
		}
		else {//Se i risultati del DB interno FLOKKER non sono soddisfacenti cerco su servizio esterno
			try{
				//Search on GOOGLE service
				bos = placesExternalService.getPlacesByDescription(token);
			}
			catch(Exception e){
				logger.error(e.getMessage());
				IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
				persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
				throw persistenceException;			
			}
		}
		
		//Se ho ottenuto risultati dalla ricerca su servizio esterno
		if(bos!=null && bos.size()>0){
			
			for (PlaceBO placeBO : bos) {
				try {
					placesPersistenceService.saveOrUpdatePlace(placeBO, token);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return bos;		
	}

	@Override
	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.updatePassword(userId, email, encodedOldPassword, encodedNewPassword);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;			
	}

	@Override
	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.checkVerificationCode(email,verificationcode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		

	}

	@Override
	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.saveVerficationCode(email, verificationCode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO logout(int userId, String deviceId) throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.logout(userId,deviceId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws IntegrationException {
		PremiumPersistenceService premiumPersistenceService = (PremiumPersistenceService)PersistenceServiceProvider.getPersistenceService("premiumPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = premiumPersistenceService.upgradeToPremium(userId, subscriptionBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}
	
	@Override
	public WriteResultBO registerUser(UserBO userBO) throws IntegrationException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		WriteResultBO resultBO = null;
		try{
			resultBO = userPersistenceService.registerUser(userBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		return resultBO;
	}
	
	@Override
	public WriteResultBO login(SessionBO session) throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");

		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.login(session);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;	
	}	

	@Override
	public WriteResultBO updateUserData(UserBO userBO) throws IntegrationException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = userPersistenceService.updateUserData(userBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public UserBO getUser(int userId) throws IntegrationException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		UserBO boOutput = null;
		try{
			boOutput = userPersistenceService.getUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return boOutput;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		Map<Integer, Map<String, String>> secrets = null;
		try {
			secrets = securityPersistenceService.retrieveClientSecrets();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return secrets;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<LanguageBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllLanguages();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<SubscriptionTypeBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllSubscriptionTypes();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<AchievementTypeBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllAchievementTypes();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<MessageStandardBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllStandardMessages();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<AttendanceStateEnum> getAllAttendanceStates() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<AttendanceStateEnum> bos = null;
		List<AttendanceStateEO> eos = null;
		try {
			eos = typologicalPersistenceService.getAllAttendanceStates();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		
		AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
		bos = attendanceStateMapper.mapEOs2BOs(eos);
		
		return bos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<EventStateEnum> bos = null;
		List<EventStateEO> eos = null;
		try {
			eos = typologicalPersistenceService.getAllEventStates();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		
		EventStateMapper eventStateMapper = EventStateMapper.getInstance();
		bos = eventStateMapper.mapEOs2BOs(eos);
		
		return bos;
	}

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<EventCategoryBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllEventCategories();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<NationBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllNations();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws IntegrationException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.resetPassword(email, newPassword, verificationCode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;
	}

	@Override
	public EmailInfoBO isExistingEmail(String email) throws IntegrationException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		EmailInfoBO resultBO = null;
		try{
			resultBO = userPersistenceService.isExistingEmail(email);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		
		return resultBO;
	}
	
	private boolean isPlaceStillValid(String date, int expirationDays) {
		Date cachedDate = null;
		try {
			cachedDate = Constants.DATE_FORMATTER.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date currentDate = new Date();
		long diffDays = getDateDiff(cachedDate, currentDate, TimeUnit.DAYS);
		logger.debug("Place data "+diffDays+" days old");
		
		return diffDays<=expirationDays;
	}
	
	private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}	

}
