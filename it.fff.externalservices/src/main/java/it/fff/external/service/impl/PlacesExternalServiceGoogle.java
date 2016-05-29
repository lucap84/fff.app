package it.fff.external.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.comparator.PlaceComparator;
import it.fff.business.common.mapper.PlaceMapper;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.clientserver.common.util.Constants;
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
		 * Restringo il campo di ricerca nell'intorno delle coordinate gps dell'utente (per risultati piu' attinenti)
		 */
		
		if(userGpsLat!=0 && userGpsLong!=0){
			double sideOfSquareKm= viewportSizeKm/2; //Lato del quadrato della viewport di ricerca in km
			double sideOfSquareDegrees = Constants.ONE_KM_TO_DEGREES*sideOfSquareKm; //trasformo km in gradi 
			
			//Per il calcolo della finestra geografica devo considerare il range delle coordinate GPS (lat:-90;+90 long:-180,+180) e il segno
			double southWestBound_Lat	= userGpsLat - sideOfSquareDegrees;
			if(Math.abs(southWestBound_Lat)>Constants.LATITUDE_RANGE_ABS){ //se la latitudine calcolata esce dal range fisico di latitudine
				southWestBound_Lat = (southWestBound_Lat % Constants.LATITUDE_RANGE_ABS)*-1; //ricalcolo la latitudine
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
	
			LatLng southWestBound = new LatLng(southWestBound_Lat, southWestBound_Long); //angolo sud-ovest della finestra di ricerca
			LatLng northEastBound = new LatLng(northEastBound_Lat, northEastBound_Long); //angolo nord-est della finestra di ricerca
			
			geocodingRequest.bounds(southWestBound, northEastBound); //imposto la finestra di ricerca nella request
		}
		
		/*
		 * Imposto anche la region dell'utente chiamante per un risultato piu' attinente
		 */
		if(region!=null && !"".equals(region)){
			geocodingRequest.region(region);
		}
		
		//Lancio la ricerca per la parola chiave cercata
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
		GeocodingResult[] geoResults = geocodingRequest.latlng(userPosition).await();
		
		if(geoResults!=null){
			List<PlaceBO> bos = PlaceMapper.getInstance().mapGeocodingResults2BOs(geoResults);
			
			//Ordinamento basato su coordinate uente
			PlaceComparator comparator = new PlaceComparator(userGpsLat, userGpsLong);
			Collections.sort(bos, comparator);
			
			placeBO = bos.get(0); //Restituisco solo il place piu' vicino all'utente (quindi attinente)
		}
		
		logger.debug("... return from External service getPlaceByGPS");
		return placeBO;
	}

}
