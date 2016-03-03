package it.fff.external.service;

import java.util.List;

import it.fff.business.common.bo.PlaceBO;

public interface PlacesExternalService extends ExternalService {
	
	public List<PlaceBO> getPlacesByDescription(String description) throws Exception;

}
