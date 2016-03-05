package it.fff.external.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.external.service.PlacesExternalService;

public class PlacesExternalServiceGoogle implements PlacesExternalService {

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws Exception {
		
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String apiKEY = confProvider.getPlacesConfigProperty(Constants.PROP_GOOGLE_APIKEY);
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKEY);
		
//		GeocodingResult[] results =  GeocodingApi.geocode(context,description).await();
		GeocodingApiRequest geocodingRequest = GeocodingApi.newRequest(context);
		GeocodingResult[] results = geocodingRequest.address(description).await();
		
		for (int i = 0; i < results.length; i++) {
			System.out.println("*********************");
			GeocodingResult geocodingResult = results[i];
			System.out.println("placeId: "+geocodingResult.placeId);
			System.out.println("partialMatch: "+geocodingResult.partialMatch);
			System.out.println("addressComponents: "+geocodingResult.addressComponents);
			System.out.println("formattedAddress: "+geocodingResult.formattedAddress);
			System.out.println("types: "+geocodingResult.types);
			System.out.println("postcodeLocalities: "+geocodingResult.postcodeLocalities);
			System.out.println("geometry: "+geocodingResult.geometry);
			System.out.println("*********************");
		}
		
		List<PlaceBO> places = new ArrayList<PlaceBO>();
		
		return places;
	}

}
