package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UpdateResultBO;

public interface PlacesPersistenceService extends PersistenceService{

	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO bo) throws Exception;

	public List<PlaceBO> getPlacesByDescription(String description) throws Exception;

}
