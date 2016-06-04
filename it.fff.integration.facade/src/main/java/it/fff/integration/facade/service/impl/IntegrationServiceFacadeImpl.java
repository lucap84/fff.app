package it.fff.integration.facade.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.*;
import it.fff.business.common.mapper.CustomMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.ErrorCodes;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.util.Constants;
import it.fff.clientserver.common.util.FlokkerUtils;
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
	public WriteResultBO updateProfileImage(ProfileImageBO imgBO) throws IntegrationException {
		logger.debug("aggiornando img utente ...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");

		WriteResultBO result = null;
		try{
			result = userPersistenceService.updateProfileImage(imgBO);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		if(result!=null){
			logger.debug("user img created");
		}
		return result;
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
	public WriteResultBO setCurrentPosition(int userId, double gpsLat, double gpsLong) throws IntegrationException {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		WriteResultBO resultBO = null;
		try{
			resultBO = placesPersistenceService.setCurrentPosition(userId, gpsLat, gpsLong);
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
	public Set<PlaceBO> getPlacesByDescription(String token, double userGpsLat, double userGpsLong) throws IntegrationException {
		Set<PlaceBO> bos = new HashSet<PlaceBO>();
		if(token==null || "".equals(token)){
			return bos;
		}
		
		PlacesExternalService placesExternalService = (PlacesExternalService)ExternalServiceProvider.getExternalService("placesExternalService");
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		PlaceBO userPlace = null;
		String userRegion = null;
		
		boolean readFromCache = Boolean.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_PLACE_READ_FROM_CACHE));
		boolean writeToCache = Boolean.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_PLACE_UPDATE_CACHE));
		
		if(readFromCache){ //Prendo i place dalla cache solo se la cache è abilitata
			bos = this.getPlacesByDescriptionInCache(token, userGpsLat, userGpsLong);
		}
		
		List<PlaceBO> invalidPlaces = this.getInvalidPlaces(bos);
		
		//cerco su servizio esterno solo se la cache è disabilitata
		//OPPURE
		//i risultati della cache sono vuoti (provo quindi su servizio ext)
		//OPPURE
		//c'e' almeno un risultato non piu valido (devo aggiornare i risultati cached)
		if(!readFromCache 			|| 
			bos.size()==0		   	||
			invalidPlaces.size()>0
			){

			//rimuovo tutti i risultati non piu validi e quindi da sostituire
			bos.removeAll(invalidPlaces);
			
			try{
				//recupero prima la Region del chiamante (partendo dalle sue coordinate) per raffinare la ricerca successiva
				userPlace = this.getPlaceByGPS(userGpsLat, userGpsLong);
				userRegion =  this.getRegionByPlace(userPlace);
			
				//Search on External service and add all results
				List<PlaceBO> placesByDescription = placesExternalService.getPlacesByDescription(token, userGpsLat, userGpsLong, userRegion);
				bos.addAll(placesByDescription);	
			}
			catch(Exception e){
				logger.error(e.getMessage());
				IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
				persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
				throw persistenceException;			
			}

			if(writeToCache){ 
				//Aggiorno su cache (DB interno) i risultati nuovi/aggiornati da servizio esterno

				try {
					if(userPlace!=null){//Salvo o aggiorno i dati del luogo da cui e' partita la ricerca
						placesPersistenceService.saveOrUpdatePlace(userPlace, null);  //non c'e' token perche' il place e' stato trovato tramite coordinate
					}
					
					if(bos!=null && bos.size()>0){//Salvo o aggiorno tutti i luoghi ricavati dalla ricerca tramite token (a partire dalla coordinate gps utente)
						for (PlaceBO placeBO : bos) {
							placesPersistenceService.saveOrUpdatePlace(placeBO, token); 
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		


		return bos;		
	}
	

	private String getRegionByPlace(PlaceBO place) throws Exception{
		String userRegion = null;
		//ricavo quindi la stringa della region dalla quale parte la chiamata
		if(place!=null && place.getCity()!=null && place.getCity().getNazione()!=null){
			userRegion = place.getCity().getNazione().getInternationalCodeAplha2();
		}			
		return userRegion;
	}

	@Override
	public PlaceBO getPlaceByGPS(double userGpsLat, double userGpsLong) throws IntegrationException {
		PlaceBO userPlaceByGPS = null;

		if(userGpsLat==0 && userGpsLong==0){
			return userPlaceByGPS;
		}
		
		boolean readFromCache = Boolean.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_PLACE_READ_FROM_CACHE));
		
		if(readFromCache){ //Prendo il place dalla cache solo se la cache è abilitata
			userPlaceByGPS = this.getPlaceByGPSInCache(userGpsLat, userGpsLong);
		}
		if(!readFromCache || userPlaceByGPS==null){//Altrimenti cerco su servizio esterno
			PlacesExternalService placesExternalService = (PlacesExternalService)ExternalServiceProvider.getExternalService("placesExternalService");
			try {
				userPlaceByGPS = placesExternalService.getPlaceByGPS(userGpsLat, userGpsLong);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return userPlaceByGPS;
	}	

	private PlaceBO getPlaceByGPSInCache(double userGpsLat, double userGpsLong) throws IntegrationException{
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		PlaceBO boCached = null;
		
		try{
			boCached = placesPersistenceService.getPlaceByGPS(userGpsLat, userGpsLong);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException exc = new IntegrationException(e.getMessage(),e);
			exc.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw exc;			
		}
		
		return boCached;
	}

	private Set<PlaceBO> getPlacesByDescriptionInCache(String token, double userGpsLat, double userGpsLong) throws IntegrationException  {
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		Set<PlaceBO> bosCached = null;
		
		try{
			bosCached = placesPersistenceService.getPlacesByDescription(token, userGpsLat, userGpsLong);	
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException exc = new IntegrationException(e.getMessage(),e);
			exc.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw exc;			
		}
		
		return bosCached;
	}

	private List<PlaceBO> getInvalidPlaces(Set<PlaceBO> bosCached) {
		List<PlaceBO> invalidPlaces = new ArrayList<PlaceBO>();
		
		if(bosCached!=null && bosCached.size()>0){
			int ttlDays = Integer.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_PLACE_EXT_CACHING_TTL));
			for (PlaceBO placeBO : bosCached) {
				String dataAggiornamento = placeBO.getDataAggiornamento();
				
				if(!FlokkerUtils.isDateStillValid(dataAggiornamento, ttlDays)){
					invalidPlaces.add(placeBO);
				}
			}
		}
		return invalidPlaces;
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
		try {
			bos = typologicalPersistenceService.getAllAttendanceStates();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		
		return bos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws IntegrationException {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<EventStateEnum> bos = null;
		try {
			bos = typologicalPersistenceService.getAllEventStates();
		} catch (Exception e) {
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;	
		}
		
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
	public EmailInfoBO getEmailInfo(String email) throws IntegrationException {
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		EmailInfoBO resultBO = null;
		try{
			resultBO = userPersistenceService.getEmailInfo(email);
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
	public CityBO getCityByName(String cityName, String nationCode) throws IntegrationException {
		logger.debug("recupero citta...");
		PlacesPersistenceService placesPersistenceService = (PlacesPersistenceService)PersistenceServiceProvider.getPersistenceService("placesPersistenceService");
		
		CityBO resultBO = null;
		try{
			resultBO = placesPersistenceService.getCityByName(cityName, nationCode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		logger.debug("...recuperate citta");
		return resultBO;
	}	
	
	@Override
	public List<FeedbackEnum> getUserFeedbacks(int userId) throws IntegrationException {
		logger.debug("recupero feedbacks utente...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		List<FeedbackEnum> resultBO = null;
		try{
			resultBO = userPersistenceService.getUserFeedbacks(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		logger.debug("...recuperati feedbacks utente");
		return resultBO;
	}	


	@Override
	public ProfileImageBO readProfileImage(int userId) throws IntegrationException {
		logger.debug("recupero immagine utente...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		ProfileImageBO resultBO = null;
		try{
			resultBO = userPersistenceService.readProfileImage(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		logger.debug("...recuperata immagine utente");
		return resultBO;
	}
	
	@Override
	public String[] getFacebookToken(String code) throws IntegrationException {
		
		String accessTokenResp = null;
		String[] socialTokenAndExpires = new String[2];
		
        try {
        	ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
        	String myAppId = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_APP_ID);
        	String myAppSecret = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_APP_SECRET);
        	String urlAccessToken = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_ACCESSTOKEN_URL);
        	String urlRedirect = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_REDIRECT_URL);
        	
        	StringBuffer urlRedirectEnriched = new StringBuffer(urlAccessToken);
        	urlRedirectEnriched.append("?client_id=").append(myAppId);
        	urlRedirectEnriched.append("&redirect_uri=").append(URLEncoder.encode(urlRedirect, "UTF-8"));
        	urlRedirectEnriched.append("&client_secret=").append(myAppSecret);
        	urlRedirectEnriched.append("&code=").append(code);
        	
            URL u = new URL(urlRedirectEnriched.toString());
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");            
            in.close();
            accessTokenResp = b.toString();
            if (accessTokenResp.startsWith("{"))
                throw new Exception("error on requesting token: " + accessTokenResp + " with code: " + code);
        } catch (Exception e) {
            logger.error("invalid 'token'");
            return null;
        } 
        
        String socialTokenExpiresStr = null;
        String socialToken = null;
        
		if(accessTokenResp.contains("access_token=")){
			accessTokenResp = accessTokenResp.replace("access_token=", "");
			int indexOf = accessTokenResp.indexOf("&expires=");
			
			socialTokenExpiresStr = accessTokenResp.substring(indexOf+9);
			if(socialTokenExpiresStr!=null && !"".equals(socialTokenExpiresStr)){
				socialTokenExpiresStr = socialTokenExpiresStr.substring(0, socialTokenExpiresStr.indexOf('\n'));
			}
			socialToken = accessTokenResp.substring(0, indexOf);
		}
		
		socialTokenAndExpires[0] = socialToken;
		socialTokenAndExpires[1] = socialTokenExpiresStr;
        
        return socialTokenAndExpires;
	}

	@Override
	public UserBO getFacebookUserData(String socialToken, int socialTokenExpires, String deviceId) throws IntegrationException {
		
        String graph = null;
        ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
        try {
        	String urlFbMe = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_ME_URL);
        	String fbMeFields = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_ME_FIELDS);
        	
        	StringBuffer urlFbMeEnriched = new StringBuffer(urlFbMe);
        	urlFbMeEnriched.append("?access_token=").append(socialToken);
        	urlFbMeEnriched.append("&fields=").append(fbMeFields);
            
            URL u = new URL(urlFbMeEnriched.toString());
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer buff = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            	buff.append(inputLine + "\n");            
            in.close();
            graph = buff.toString();
        } catch (Exception e) {
            logger.error("error during facebook graph call");
            throw new IntegrationException("error during facebook graph call", e);
        }
        
        UserBO userBO = CustomMapper.getInstance().mapFacebookData2BO(graph);
        
        SessionBO session = new SessionBO();
        session.setSharedKey(socialToken);
        session.setExpiresKey(socialTokenExpires);
        session.setDeviceId(deviceId);
        session.setLogged(true); //Imposto questa sessione come quella corrente
        
        userBO.getAccount().getSessions().add(session);
        
		return userBO;
	}
	
	@Override
	public List<AttendanceBO> getAttendancesByUser(int userId) throws IntegrationException {
		logger.debug("recupero partecipazioni per utente...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		List<AttendanceBO> bos = null;
		try{
			bos = userPersistenceService.getAttendancesByUser(userId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		
		logger.debug("...partecipazioni per utente recuperate");
		return bos;
	}
	
	@Override
	public AccountBO getUserAccountByFacebookId(long facebookId) throws IntegrationException {
		logger.debug("recupero account flokker tramite facebookId...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		AccountBO bo = null;
		try{
			bo = userPersistenceService.getUserAccountByFacebookId(facebookId);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		
		logger.debug("...recuperato account flokker tramite facebookId");
		return bo;
	}	
	
	@Override
	public AccountBO getUserAccountByEmail(String email) throws IntegrationException {
		logger.debug("recupero account flokker tramite email...");
		UserPersistenceService userPersistenceService = (UserPersistenceService)PersistenceServiceProvider.getPersistenceService("userPersistenceService");
		
		AccountBO bo = null;
		try{
			bo = userPersistenceService.getUserAccountByEmail(email);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			IntegrationException persistenceException = new IntegrationException(e.getMessage(),e);
			persistenceException.addErrorCode(ErrorCodes.ERR_PERSIST_GENERIC);
			throw persistenceException;			
		}
		
		logger.debug("...recuperato account flokker tramite email");
		return bo;
	}	
	

}
