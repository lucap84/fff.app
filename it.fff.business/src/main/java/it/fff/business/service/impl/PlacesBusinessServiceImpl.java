package it.fff.business.service.impl;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.service.PlacesBusinessService;
import it.fff.integration.facade.exception.IntegrationException;
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
	public List<PlaceBO> getPlacesByDescription(String description, double gplLat, double gpsLong) throws IntegrationException {
		List<PlaceBO> bos = integrationFacade.getPlacesByDescription(description, gplLat, gpsLong);
		return bos;
	}

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws IntegrationException {
		WriteResultBO bo = integrationFacade.setCurrentPosition(userId, eventId, placeBO);
		return bo;
	}	
	
}
