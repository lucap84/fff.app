package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.PersistenceException;

public interface PlacesBusinessService extends BusinessService{

	List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException;

	WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException;

}
