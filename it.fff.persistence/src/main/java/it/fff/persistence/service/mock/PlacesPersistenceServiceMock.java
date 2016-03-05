package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.PlaceTypeBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.dto.CityDTO;
import it.fff.persistence.service.PlacesPersistenceService;

public class PlacesPersistenceServiceMock implements PlacesPersistenceService{

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO bo) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws SQLException {
		PlaceBO bo1 = new PlaceBO();
		bo1.setId(1);
		bo1.setNome("Chiringuito");
		
		PlaceTypeBO placeType = new PlaceTypeBO();
		placeType.setId(1);
		placeType.setNome("point of interest");
		
		NationBO nationBO = new NationBO();
		nationBO.setId(1);
		nationBO.setNome("Italia");
		nationBO.setInternationalKey("380");
		nationBO.setInternationalCode("ITA");
		
		CityBO citta = new CityBO();
		citta.setId(1);
		citta.setNome("Roma");
		citta.setNazione(nationBO);
		
		bo1.setPlaceKey("ChIJrRMgU7ZhLxMRxAOFkC7I8Sg");
		bo1.setGpsLat(1.234);
		bo1.setGpsLong(1.456);
		bo1.setRoute("Via di prova");
		bo1.setCivico("22");
		bo1.setCap("00100");
		bo1.setPlaceType(placeType);
		bo1.setCity(citta);
		
		
		
		PlaceBO bo2 = new PlaceBO();
		bo2.setId(2);
		bo2.setNome("Colosseo");
		
		PlaceTypeBO placeType2 = new PlaceTypeBO();
		placeType2.setId(1);
		placeType2.setNome("route");
		
		bo2.setPlaceKey("l9d0sRMgU7ZhLxMRxAOFkC83j7f");
		bo2.setGpsLat(1.44);
		bo2.setGpsLong(1.45);
		bo2.setRoute("Piazza del Colosseo");
		bo2.setCivico("1");
		bo2.setCap("00100");
		bo2.setPlaceType(placeType2);
		bo2.setCity(citta);		
		
		List<PlaceBO> places = new ArrayList<PlaceBO>();
		places.add(bo1);
		places.add(bo2);
		
		return places;
	}

}
