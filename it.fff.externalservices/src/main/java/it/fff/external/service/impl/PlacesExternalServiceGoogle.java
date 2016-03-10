package it.fff.external.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.external.service.PlacesExternalService;

public class PlacesExternalServiceGoogle implements PlacesExternalService {
	
	private static final Logger logger = LogManager.getLogger(PlacesExternalServiceGoogle.class);

	@Override
	public List<PlaceBO> getPlacesByDescription(String description, double userGpsLat, double userGpsLong) throws Exception {
		List<PlaceBO> placesBO = null;
		
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String apiKEY = confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_APIKEY);
		int maxResultsToLoad = Integer.valueOf(confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_MAX_RESULTS_LOADED));
		double viewportSizeKm = Double.valueOf(confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_VIEWPORT_DIAMETER_KM));
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKEY);
		
		GeocodingApiRequest geocodingRequest = GeocodingApi.newRequest(context);
		/*
		 * Restringo il campo di ricerca nell'intorno delle coordinate gps dell'utente
		 */
		double sideOfSquareKm= viewportSizeKm/2; //Lato del quadrato della viewport di ricerca in km
		double sideOfSquareDegrees = Constants.ONE_KM_TO_DEGREES*sideOfSquareKm; //trasformo km in gradi 
		
		double southWestBound_Lat = userGpsLat-sideOfSquareDegrees;
		double southWestBound_Long = userGpsLong-sideOfSquareDegrees;
		double northEastBound_Lat = userGpsLat+sideOfSquareDegrees;
		double northEastBound_Long = userGpsLong+sideOfSquareDegrees;	

		LatLng southWestBound = new LatLng(southWestBound_Lat, southWestBound_Long);
		LatLng northEastBound = new LatLng(northEastBound_Lat, northEastBound_Long);
		
		geocodingRequest.bounds(southWestBound, northEastBound); //imposto la finestra di ricerca nella request
		
		//Lancio la ricerca
		GeocodingResult[] results = geocodingRequest.address(description).await();
		
		if(results!=null){
			placesBO = new ArrayList<PlaceBO>();
			
			int resultsLength = results.length;
			if(resultsLength>maxResultsToLoad){
				resultsLength = maxResultsToLoad;
			}
			
			for (int i = 0; i < resultsLength; i++) {
				System.out.println("*********************");
				GeocodingResult geocodingResult = results[i];
				
				String gPlaceId = geocodingResult.placeId;
				boolean gPartialMatch = geocodingResult.partialMatch;
				String gNome = geocodingResult.formattedAddress;
				AddressType gAddressType = geocodingResult.types[0];
				String gRoute = null;
				String gStreetAddress = null;
				String gCivico = null;
				String gLocality = null;
				String gRegione = null;
				String gComuneLongName = null;
				String gComuneShortName = null;
				String gAdminArea3 = null;
				String gAdminArea4 = null;
				String gAdminArea5 = null;
				String gNazioneLongName = null;
				String gNazioneShortName = null;
				String gCAP = null;
				double gLat = geocodingResult.geometry.location.lat;
				double gLong = geocodingResult.geometry.location.lng;
				
				for (int j = 0; j < geocodingResult.addressComponents.length; j++) {
					AddressComponent addressComponent = geocodingResult.addressComponents[j];
					
					switch(addressComponent.types[0]){
						case ROUTE: gRoute = addressComponent.longName; break;	
						case STREET_ADDRESS: gStreetAddress = addressComponent.longName; break;
						case STREET_NUMBER: gCivico = addressComponent.longName; break;
						case LOCALITY: gLocality = addressComponent.longName; break;
						case ADMINISTRATIVE_AREA_LEVEL_1: gRegione = addressComponent.longName; break;
						case ADMINISTRATIVE_AREA_LEVEL_2: {
							gComuneLongName = addressComponent.longName;
							gComuneShortName = addressComponent.shortName;
							break;
						}
						case ADMINISTRATIVE_AREA_LEVEL_3: gAdminArea3 = addressComponent.longName; break;
						case ADMINISTRATIVE_AREA_LEVEL_4: gAdminArea4 = addressComponent.longName; break;
						case ADMINISTRATIVE_AREA_LEVEL_5: gAdminArea5 = addressComponent.longName; break;
						case COUNTRY: {
							gNazioneLongName  =	addressComponent.longName;
							gNazioneShortName = addressComponent.shortName;
							break;
						}
						case POSTAL_CODE: gCAP = addressComponent.longName; break;
					default:
						break;
					}
				}
				

				System.out.println("*********************");
				System.out.println("placeId: "+gPlaceId);
				System.out.println("partialMatch: "+gPartialMatch);
				System.out.println("formattedAddress: "+gNome);
				System.out.println("gRoute: "+gRoute);
				System.out.println("gStreetAddress: "+gStreetAddress);
				System.out.println("gCivico: "+gCivico);
				System.out.println("latitudine: "+gLat);
				System.out.println("longitudine: "+gLong);
				System.out.println("postal code: "+gCAP);
				System.out.println("gAddressType: "+gAddressType);
				System.out.println("gLocality: "+gLocality);
				System.out.println("gRegione: "+gRegione);
				System.out.println("gComuneLongName: "+gComuneLongName);
				System.out.println("gComuneShortName: "+gComuneShortName);
				System.out.println("gNazioneLongName: "+gNazioneLongName);
				System.out.println("gNazioneShortName: "+gNazioneShortName);
				System.out.println("*********************");
				
				PlaceBO bo = new PlaceBO();
				bo.setPlaceKey(gPlaceId);
				bo.setNome(gNome);
				
				if(gStreetAddress!=null && gStreetAddress.length()>0){
					bo.setAddressRoute(gStreetAddress);
				} else{
					if(gRoute!=null && gRoute.length()>0){
						bo.setAddressRoute(gRoute);
					} else{
						if(gLocality!=null && gLocality.length()>0){
							bo.setAddressRoute(gLocality);
						}
					}
				}
				bo.setCivico(gCivico);
				bo.setGpsLat(gLat);
				bo.setGpsLong(gLong);
				bo.setCap(gCAP);
				
				Date currentDate = new Date();
				String currentDateStr = Constants.DATE_FORMATTER.format(currentDate);
				bo.setDataCreazione(currentDateStr);
				bo.setDataAggiornamento(currentDateStr);
				
				CityBO cittaBO = new CityBO();
				//Se la citta (LOCALITY) non e' presente nella risposta uso le administrative area, dalla piu' specifica alla meno specifica (il comune)
				if(gLocality!=null && !"".equals(gLocality)){
					cittaBO.setNome(gLocality);
				} else{
					if(gAdminArea5!=null && !"".equals(gAdminArea5)){
						cittaBO.setNome(gAdminArea5);	
					} else{
						if(gAdminArea4!=null && !"".equals(gAdminArea4)){
							cittaBO.setNome(gAdminArea4);	
						} else{
							if(gAdminArea3!=null && !"".equals(gAdminArea3)){
								cittaBO.setNome(gAdminArea3);	
							} else{
								if(gComuneLongName!=null && !"".equals(gComuneLongName)){
									cittaBO.setNome(gComuneLongName);	
								}
							}
						}
					}
				}
				
				NationBO nazioneBO = new NationBO();
				nazioneBO.setNome(gNazioneLongName);
				if(gNazioneShortName!=null && gNazioneShortName.length()==2){
					nazioneBO.setInternationalCodeAplha2(gNazioneShortName);
				}
				if(gNazioneShortName!=null && gNazioneShortName.length()==3){
					nazioneBO.setInternationalCodeAplha3(gNazioneShortName);
				}
				

				cittaBO.setNazione(nazioneBO);
				bo.setCity(cittaBO);
				
				PlaceTypeEnum placeTypeBO = null;
				try{
					
					placeTypeBO = PlaceTypeEnum.valueOf(gAddressType.name());
				}
				catch(IllegalArgumentException e){
					logger.error("State not Recognized! :"+gAddressType.name());
				}
				bo.setPlaceType(placeTypeBO);
			
				
				//Add to list
				placesBO.add(bo);
			}
		
		}
		
		return placesBO;
	}

}
