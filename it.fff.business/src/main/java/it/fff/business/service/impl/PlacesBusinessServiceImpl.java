package it.fff.business.service.impl;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.service.PlacesBusinessService;
import it.fff.integration.facade.exception.PersistenceException;
import it.fff.integration.facade.service.IntegrationServiceFacade;

public class PlacesBusinessServiceImpl implements PlacesBusinessService{
	
	private IntegrationServiceFacade integrationFacade;

	public IntegrationServiceFacade getIntegrationFacade() {
		return integrationFacade;
	}

	public void setIntegrationFacade(IntegrationServiceFacade integrationFacade) {
		this.integrationFacade = integrationFacade;
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws PersistenceException {
		List<PlaceBO> bos = integrationFacade.getPlacesByDescription(description);
		return bos;
	}

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws PersistenceException {
		WriteResultBO bo = integrationFacade.setCurrentPosition(userId, eventId, placeBO);
		return bo;
	}	
	
}
