package it.fff.persistence.service;

import java.util.Set;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;

public interface PlacesPersistenceService extends PersistenceService{

	public WriteResultBO setCurrentPosition(int userId, double gpsLat, double gpsLong) throws Exception;

	public Set<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws Exception;
	
	public PlaceBO getPlaceByGPS(double gpsLat, double gpsLong) throws Exception;
	
	public WriteResultBO saveOrUpdatePlace(PlaceBO place, String token) throws Exception;

	public CityBO getCityByName(String cityName, String nationKey) throws Exception;
	
	public NationBO getNationByInternationalCode(String nationCode) throws Exception;


}
