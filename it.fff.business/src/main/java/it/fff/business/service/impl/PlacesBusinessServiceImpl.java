package it.fff.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.comparator.PlaceComparator;
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
		Set<PlaceBO> bos = integrationFacade.getPlacesByDescription(description, gplLat, gpsLong);
		List<PlaceBO> bosList = new ArrayList<PlaceBO>(bos);
		
		//Ordinamento basato su coordinate uente
		PlaceComparator comparator = new PlaceComparator(gplLat, gpsLong);
		Collections.sort(bosList, comparator);
		
		return bosList;
	}

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO placeBO) throws IntegrationException {
		WriteResultBO bo = integrationFacade.setCurrentPosition(userId, eventId, placeBO);
		return bo;
	}

	@Override
	public CityBO getCityByName(String cityName, String nationKey) throws IntegrationException {
		CityBO bo = integrationFacade.getCityByName(cityName, nationKey);
		return bo;
	}	
	
}
