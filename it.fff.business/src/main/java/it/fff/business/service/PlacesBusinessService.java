package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.IntegrationException;

public interface PlacesBusinessService extends BusinessService{

	List<PlaceBO> getPlacesByDescription(String description, double gpsLat, double gpsLong) throws IntegrationException;

	WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws IntegrationException;

	CityBO getCityByName(String cityName, String nationKey) throws IntegrationException;;

}
