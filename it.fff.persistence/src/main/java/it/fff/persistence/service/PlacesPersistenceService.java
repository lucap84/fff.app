package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.PlaceEO;

public interface PlacesPersistenceService extends PersistenceService{

	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceEO eo) throws Exception;

	public List<PlaceEO> getPlacesByDescription(String description) throws Exception;

}
