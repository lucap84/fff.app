package it.fff.persistence.facade.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.*;
import it.fff.business.common.eo.*;
import it.fff.business.common.mapper.*;
import it.fff.business.common.util.ErrorCodes;
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

		EventEO eventEO = null;
		try{
			eventEO = eventPersistenceService.retrieveEvent(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		if(eventEO!=null){
			logger.debug("event ({}) retrieved",eventId);
		}
		EventBO eventBO = EventMapper.map2BO(eventEO);
		if(eventBO!=null){
			logger.debug("Event ({}) mapped in BO",eventId);
		}
		return eventBO;
	}

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException {
		logger.debug("aggiornando img utente ...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		ProfileImageEO eoOutput = null;
		try{
			UserMapper mapper = new UserMapper();
			ProfileImageEO eoInput = mapper.map2EO(imgBO);
			eoOutput = userPersistenceService.updateProfileImage(eoInput);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		if(eoOutput!=null){
			logger.debug("user img created");
		}
		UserMapper mapper = new UserMapper();
		ProfileImageBO boCreated = mapper.map2BO(eoOutput);
		if(boCreated!=null){
			logger.debug("img mapped in BO");
		}
		return boCreated;
	}

	@Override
	public CreateResultBO createEvent(EventBO bo) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		CreateResultBO resultBO = null;
		EventEO eo = EventMapper.map2EO(bo);
		try{
			resultBO = eventPersistenceService.createEvent(eo);
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
	public UpdateResultBO cancelEvent(int eventId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		UpdateResultBO resultBO = null;
		try{
			resultBO = eventPersistenceService.cancelEvent(eventId);
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
	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		UpdateResultBO resultBO = null;
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
	public CreateResultBO createEventMessage(int attendanceId, String message) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		CreateResultBO resultBO = null;
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
	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		CreateResultBO resultBO = null;
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
	public CreateResultBO joinEvent(AttendanceBO bo) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		CreateResultBO resultBO = null;
		AttendanceEO eo = EventMapper.map2EO(bo);
		try{
			resultBO = eventPersistenceService.createStandardEventMessage(eo);
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
	public CreateResultBO addFeedback(AttendanceBO bo) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		CreateResultBO resultBO = null;
		AttendanceEO eo = EventMapper.map2EO(bo);
		boolean isPositiveFeedback = bo.getFeedback().isPositiveFeedback();
		try{
			resultBO = eventPersistenceService.addFeedback(eo, isPositiveFeedback);
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
		
		List<MessageEO> eos = null;
		try{
			eos = eventPersistenceService.getEventMessages(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		List<MessageBO> bos = MessageMapper.map2BO(eos);

		return bos;			
	}

	@Override
	public List<EventBO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");

		List<EventEO> eos = null;
		try{
			eos = eventPersistenceService.searchEvents(gpsLat, gpsLong, idCategoria, partecipanti);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		List<EventBO> bos = EventMapper.map2BO(eos);

		return bos;	
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<EventEO> eos = null;
		try{
			eos = eventPersistenceService.getEventsByUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		List<EventBO> bos = EventMapper.map2BO(eos);

		return bos;		
		
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException {
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getPersistenceService("eventPersistenceService");
		
		List<AttendanceEO> eos = null;
		try{
			eos = eventPersistenceService.getAttendancesByEvent(eventId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		List<AttendanceBO> bos = AttendanceMapper.map2BO(eos);

		return bos;			
	}
	
	@Override
	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		UpdateResultBO resultBO = null;
		PlaceEO eo = PlacesMapper.map2EO(placeBO);
		try{
			resultBO = placesPersistenceService.setCurrentPosition(userId,eventId,eo);
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
		
		List<PlaceEO> eos = null;
		try{
			eos = placesPersistenceService.getPlacesByDescription(description);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		List<PlaceBO> bos = PlacesMapper.map2BO(eos);

		return bos;		
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		UpdateResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.updatePassword(email,encodedPassword);
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
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		UpdateResultBO resultBO = null;
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
	public UpdateResultBO generateVerficationCode(String email) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		UpdateResultBO resultBO = null;
		try{
			resultBO = securityPersistenceService.generateVerficationCode(email);
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
	public UpdateResultBO logout(int userId, String deviceId) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		UpdateResultBO resultBO = null;
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
	public CreateResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException {
		PremiumPersistenceService premiumPersistenceService = (PremiumPersistenceService)PersistenceServiceProvider.getPersistenceService("premiumPersistenceService");
		
		SubscriptionEO eo = SubscriptionMapper.map2EO(subscriptionBO);
		CreateResultBO resultBO = null;
		try{
			resultBO = premiumPersistenceService.upgradeToPremium(userId, eo);
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
	public CreateResultBO registerUser(UserBO userBO) throws PersistenceException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		CreateResultBO resultBO = null;
		try{
			UserEO userEOinput = UserMapper.map2EO(userBO); 
			resultBO = userPersistenceService.registerUser(userEOinput);
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
	public UpdateResultBO login(SessionBO session) throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");

		UpdateResultBO resultBO = null;
		try{
			SessionEO sessionEOInput = UserMapper.map2EO(session);
			resultBO = securityPersistenceService.login(sessionEOInput);
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
	public UpdateResultBO updateUserData(UserBO userBO) throws PersistenceException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		UserEO eo = UserMapper.map2EO(userBO);
		UpdateResultBO resultBO = null;
		try{
			resultBO = userPersistenceService.updateUserData(eo);
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
		
		UserEO eoOutput = null;
		try{
			eoOutput = userPersistenceService.getUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			PersistenceException persistenceException = new PersistenceException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		UserBO boOutput = UserMapper.map2BO(eoOutput);

		return boOutput;
	}

	@Override
	public Map<String, Map<String, String>> retrieveClientSecrets() throws PersistenceException {
		SecurityPersistenceService securityPersistenceService = (SecurityPersistenceService)PersistenceServiceProvider.getPersistenceService("securityPersistenceService");
		
		Map<String, Map<String, String>> secrets = null;
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

}
