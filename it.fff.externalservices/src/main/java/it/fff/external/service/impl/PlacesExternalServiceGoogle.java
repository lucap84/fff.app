package it.fff.external.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.mapper.PlaceMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.external.service.PlacesExternalService;

public class PlacesExternalServiceGoogle implements PlacesExternalService {
	
	private static final Logger logger = LogManager.getLogger(PlacesExternalServiceGoogle.class);

	@Override
	public List<PlaceBO> getPlacesByDescription(String description, double userGpsLat, double userGpsLong, String region) throws Exception {
		logger.debug("Call External service getPlacesByDescription...");
		List<PlaceBO> placesBO = null;
		
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String apiKEY = confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_APIKEY);
		double viewportSizeKm = Double.valueOf(confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_VIEWPORT_DIAMETER_KM));
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKEY);
		
		GeocodingApiRequest geocodingRequest = GeocodingApi.newRequest(context);
		/*
		 * Restringo il campo di ricerca nell'intorno delle coordinate gps dell'utente
		 */
		
		if(userGpsLat!=0 && userGpsLong!=0){
			double sideOfSquareKm= viewportSizeKm/2; //Lato del quadrato della viewport di ricerca in km
			double sideOfSquareDegrees = Constants.ONE_KM_TO_DEGREES*sideOfSquareKm; //trasformo km in gradi 
			
			//Per il calcolo della finestra geografica devo considerare il range delle coordinate GPS (lat:-90;+90 long:-180,+180) e il segno
			double southWestBound_Lat	= userGpsLat - sideOfSquareDegrees;
			if(Math.abs(southWestBound_Lat)>Constants.LATITUDE_RANGE_ABS){
				southWestBound_Lat = (southWestBound_Lat % Constants.LATITUDE_RANGE_ABS)*-1;
			}
			double southWestBound_Long	= userGpsLong	- sideOfSquareDegrees;
			if(Math.abs(southWestBound_Long)>Constants.LONGITUDE_RANGE_ABS){
				southWestBound_Long = (southWestBound_Long % Constants.LONGITUDE_RANGE_ABS)*-1;
			}		
			double northEastBound_Lat	= userGpsLat	+ sideOfSquareDegrees;
			if(Math.abs(northEastBound_Lat)>Constants.LATITUDE_RANGE_ABS){
				northEastBound_Lat = (northEastBound_Lat % Constants.LATITUDE_RANGE_ABS)*-1;
			}		
			double northEastBound_Long	= userGpsLong	+ sideOfSquareDegrees;
			if(Math.abs(northEastBound_Long)>Constants.LONGITUDE_RANGE_ABS){
				northEastBound_Long = (northEastBound_Long % Constants.LONGITUDE_RANGE_ABS)*-1;
			}		
	
			LatLng southWestBound = new LatLng(southWestBound_Lat, southWestBound_Long);
			LatLng northEastBound = new LatLng(northEastBound_Lat, northEastBound_Long);
			
			geocodingRequest.bounds(southWestBound, northEastBound); //imposto la finestra di ricerca nella request
		}
		
		/*
		 * Imposto anche la region dell'utente chiamante per un risultato piu' attinente
		 */
		if(region!=null && !"".equals(region)){
			geocodingRequest.region(region);
		}
		
		//Lancio la ricerca
		GeocodingResult[] results = geocodingRequest.address(description).await();
		
		if(results!=null){
			placesBO = PlaceMapper.getInstance().mapGeocodingResults2BOs(results);
		}
		logger.debug("... return from External service getPlacesByDescription");
		return placesBO;
	}


	@Override
	public PlaceBO getPlaceByGPS(double userGpsLat, double userGpsLong) throws Exception {
		logger.debug("Call External service getPlaceByGPS...");
		PlaceBO placeBO = null;
		
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String apiKEY = confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_APIKEY);
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKEY);
		GeocodingApiRequest geocodingRequest = GeocodingApi.newRequest(context);
		LatLng userPosition = new LatLng(userGpsLat, userGpsLong);
		//Lancio la ricerca
		GeocodingResult[] results = geocodingRequest.latlng(userPosition).await();
		
		if(results!=null){
			placeBO = PlaceMapper.getInstance().mapGeocodingResult2BO(results[0]); //TODO Quale prendo tra tutti i risultati? Il piu' vicino alle coordinate
		}
		
		logger.debug("... return from External service getPlaceByGPS");
		return placeBO;
	}

}
