package it.fff.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.service.PlacesBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class PlacesBusinessServiceImpl implements PlacesBusinessService{
	
	private PersistenceServiceFacade persistenceFacade;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException {
		List<PlaceBO> bos = persistenceFacade.getPlacesByDescription(description);
		return bos;
	}

	@Override
	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.setCurrentPosition(userId, eventId, placeBO);
		return bo;
	}	
	
}
