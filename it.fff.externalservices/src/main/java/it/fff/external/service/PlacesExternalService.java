package it.fff.external.service;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;

public interface PlacesExternalService extends ExternalService {
	
	public List<PlaceBO> getPlacesByDescription(String description, double userGpsLat, double userGpsLong, String userRegion) throws Exception;
	
	public PlaceBO getPlaceByGPS(double userGpsLat, double userGpsLong) throws Exception;

}
