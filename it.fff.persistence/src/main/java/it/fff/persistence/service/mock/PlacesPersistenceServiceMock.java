package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.persistence.service.PlacesPersistenceService;

public class PlacesPersistenceServiceMock implements PlacesPersistenceService{

	@Override
	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceEO eo) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public List<PlaceEO> getPlacesByDescription(String description) throws SQLException {
		PlaceEO eo1 = new PlaceEO();
		eo1.setNome("place1");
		eo1.setGpsLat(1.001);
		eo1.setGpsLat(1.003);
		
		PlaceEO eo2 = new PlaceEO();
		eo2.setNome("place2");
		eo2.setGpsLat(1.001);
		eo2.setGpsLat(1.003);		
		
		List<PlaceEO> places = new ArrayList<PlaceEO>();
		places.add(eo1);
		places.add(eo2);
		
		return places;
	}

}
