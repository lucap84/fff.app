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
	public List<PlaceBO> getPlacesByDescription(String description) throws Exception {
		List<PlaceBO> placesBO = null;
		
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String apiKEY = confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_APIKEY);
		int maxResultsToLoad = Integer.valueOf(confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_MAX_RESULTS_LOADED));
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKEY);
		
		GeocodingApiRequest geocodingRequest = GeocodingApi.newRequest(context);
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
				String gCitta = null;
				String gRegione = null;
				String gComuneLongName = null;
				String gComuneShortName = null;
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
						case LOCALITY: gCitta = addressComponent.longName; break;
						case ADMINISTRATIVE_AREA_LEVEL_1: gRegione = addressComponent.longName; break;
						case ADMINISTRATIVE_AREA_LEVEL_2: {
							gComuneLongName = addressComponent.longName;
							gComuneShortName = addressComponent.shortName;
							break;
						}
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
				System.out.println("gCitta: "+gCitta);
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
				}
				else{
					bo.setAddressRoute(gRoute);
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
				cittaBO.setNome(gCitta);
				
				NationBO nazioneBO = new NationBO();
				nazioneBO.setNome(gNazioneLongName);
				nazioneBO.setInternationalCode(gNazioneShortName);
				
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
