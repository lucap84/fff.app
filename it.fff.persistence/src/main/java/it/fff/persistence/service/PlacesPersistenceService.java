package it.fff.persistence.service;

import java.util.Set;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;

public interface PlacesPersistenceService extends PersistenceService{

	public WriteResultBO setCurrentPosition(int userId, double gpsLat, double gpsLong) throws PersistenceException;

	public Set<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws PersistenceException;
	
	public PlaceBO getPlaceByGPS(double gpsLat, double gpsLong) throws PersistenceException;
	
	public WriteResultBO saveOrUpdatePlace(PlaceBO place, String token) throws PersistenceException;

	public CityBO getCityByName(String cityName, String nationKey) throws PersistenceException;
	
	public NationBO getNationByInternationalCode(String nationCode) throws PersistenceException;


}
