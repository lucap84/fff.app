package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.clientserver.common.dto.CityDTO;
import it.fff.persistence.service.PlacesPersistenceService;

public class PlacesPersistenceServiceMock implements PlacesPersistenceService{

	@Override
	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceBO bo) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws SQLException {
		PlaceBO bo1 = new PlaceBO();
		bo1.setId(1);
		bo1.setNome("place1");
		bo1.setGpsLat(1.234);
		bo1.setGpsLat(1.456);
		bo1.setVia("Via di prova");
		bo1.setCivico("22");
		bo1.setCap("00100");
		CityBO citta = new CityBO();
		citta.setId(1);
		citta.setNome("Roma");
		bo1.setCity(citta);
		
		
		PlaceBO bo2 = new PlaceBO();
		bo2.setNome("place2");
		bo2.setGpsLat(1.001);
		bo2.setGpsLat(1.003);		
		
		List<PlaceBO> places = new ArrayList<PlaceBO>();
		places.add(bo1);
		places.add(bo2);
		
		return places;
	}

}
