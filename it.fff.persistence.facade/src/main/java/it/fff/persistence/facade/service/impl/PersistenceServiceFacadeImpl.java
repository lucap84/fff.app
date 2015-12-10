package it.fff.persistence.facade.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.*;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.business.common.util.ErrorCodes;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;
import it.fff.persistence.service.*;
import it.fff.persistence.util.PersistenceServiceProvider;

public class PersistenceServiceFacadeImpl implements PersistenceServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(PersistenceServiceFacadeImpl.class);

	@Override
	public EventBO retrieveEvent(int eventId) throws PersistenceException{
		logger.debug("retrieving event ({}) ...",eventId);
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");

		EventBO eventBO = null;
		try{
			eventBO = eventPersistenceService.retrieveEvent(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		return eventBO;
	}

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException {
		logger.debug("aggiornando img utente ...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		ProfileImageBO boOutput = null;
		try{
			boOutput = userPersistenceService.updateProfileImage(imgBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		if(boOutput!=null){
			logger.debug("user img created");
		}
		return boOutput;
	}

	@Override
	public WriteResultBO createEvent(EventBO bo) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createEvent(bo);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.cancelEvent(eventId, organizerId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int attendanceId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.cancelAttendance(eventId,attendanceId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO createEventMessage(int attendanceId, String message) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createEventMessage(attendanceId, message);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createStandardEventMessage(attendanceId, stdMsgId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO joinEvent(AttendanceBO bo) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.createStandardEventMessage(bo);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO addFeedback(AttendanceBO bo) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		WriteResultBO resultBO = null;
		boolean isPositiveFeedback = bo.isPositiveFeedback();
		try{
			resultBO = eventPersistenceService.addFeedback(bo, isPositiveFeedback);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<MessageBO> bos = null;
		try{
			bos = eventPersistenceService.getEventMessages(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;			
	}

	@Override
	public List<EventBO> searchEvents(double gpsLatFrom,double gpsLatTo, double gpsLongFrom, double gpsLongTo, int idCategoria, int minPartecipanti) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");

		List<EventBO> bos = null;
		try{
			bos = eventPersistenceService.searchEvents(gpsLatFrom, gpsLatTo, gpsLongFrom, gpsLongTo, idCategoria, minPartecipanti);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;	
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<EventBO> bos = null;
		try{
			bos = eventPersistenceService.getEventsByUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;		
		
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<AttendanceBO> bos = null;
		try{
			bos = eventPersistenceService.getAttendancesByEvent(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		return bos;			
	}
	
	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = placesPersistenceService.setCurrentPosition(userId,eventId,placeBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		List<PlaceBO> bos = null;
		try{
			bos = placesPersistenceService.getPlacesByDescription(description);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return bos;		
	}

	@Override
	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.updatePassword(userId, email, encodedOldPassword, encodedNewPassword);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;			
	}

	@Override
	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.checkVerificationCode(email,verificationcode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		

	}

	@Override
	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.saveVerficationCode(email, verificationCode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO logout(int userId, String deviceId) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.logout(userId,deviceId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException {
		PremiumPersistenceService premiumPersistenceService = (PremiumPersistenceService)PersistenceServiceProvider.getPersistenceService("premiumPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = premiumPersistenceService.upgradeToPremium(userId, subscriptionBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}
	
	@Override
	public WriteResultBO registerUser(UserBO userBO) throws PersistenceException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		WriteResultBO resultBO = null;
		try{
			resultBO = userPersistenceService.registerUser(userBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		return resultBO;
	}
	
	@Override
	public WriteResultBO login(SessionBO session) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");

		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.login(session);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;	
	}	

	@Override
	public WriteResultBO updateUserData(UserBO userBO) throws PersistenceException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = userPersistenceService.updateUserData(userBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;		
	}

	@Override
	public UserBO getUser(int userId) throws PersistenceException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		UserBO boOutput = null;
		try{
			boOutput = userPersistenceService.getUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return boOutput;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		Map<Integer, Map<String, String>> secrets = null;
		try {
			secrets = securityPersistenceService.retrieveClientSecrets();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return secrets;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<LanguageBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllLanguages();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<SubscriptionTypeBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllSubscriptionTypes();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<AchievementTypeBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllAchievementTypes();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<MessageStandardBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllStandardMessages();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<AttendanceStateEnum> getAllAttendanceStates() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<AttendanceStateEnum> bos = null;
		List<AttendanceStateEO> eos = null;
		try {
			eos = typologicalPersistenceService.getAllAttendanceStates();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		
		AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
		bos = attendanceStateMapper.mapEOs2BOs(eos);
		
		return bos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<EventStateEnum> bos = null;
		List<EventStateEO> eos = null;
		try {
			eos = typologicalPersistenceService.getAllEventStates();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		
		EventStateMapper eventStateMapper = EventStateMapper.getInstance();
		bos = eventStateMapper.mapEOs2BOs(eos);
		
		return bos;
	}

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<EventCategoryBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllEventCategories();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws PersistenceException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<NationBO> bos = null;
		try {
			bos = typologicalPersistenceService.getAllNations();
		} catch (Exception e) {
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		return bos;
	}

	@Override
	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.resetPassword(email, newPassword, verificationCode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}

		return resultBO;
	}

}
