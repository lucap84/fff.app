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
		PlaceBO bo1 = new PlaceBO();
		bo1.setNome("place1");
		bo1.setGpsLat(1.001);
		bo1.setGpsLat(1.003);
		
		PlaceBO bo2 = new PlaceBO();
		bo1.setNome("place2");
		bo1.setGpsLat(2.003);
		bo1.setGpsLat(3.004);		
		
		List<PlaceBO> places = new ArrayList<PlaceBO>();
		places.add(bo1);
		places.add(bo2);
		return places;
	}

	@Override
	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException {
		UpdateResultBO updateResultBO = new UpdateResultBO();
		updateResultBO.setUpdatedKey(1);
		updateResultBO.setSuccess(true);
		updateResultBO.setNumRecordsUpdated(1);
		return updateResultBO;
	}	
	
}
