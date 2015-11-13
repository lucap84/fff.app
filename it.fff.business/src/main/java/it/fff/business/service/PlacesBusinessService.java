package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface PlacesBusinessService extends BusinessService{

	List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException;

	UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException;

}
