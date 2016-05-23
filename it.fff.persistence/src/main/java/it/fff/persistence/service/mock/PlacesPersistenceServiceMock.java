package it.fff.persistence.service.mock;

import java.util.HashSet;
import java.util.Set;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.service.PlacesPersistenceService;

public class PlacesPersistenceServiceMock implements PlacesPersistenceService{

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO bo) throws Exception {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public Set<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws Exception {
		TypologicalPersistenceServiceMock typologicalMock = new TypologicalPersistenceServiceMock();
		
		PlaceBO bo1 = new PlaceBO();
		bo1.setId(1);
		bo1.setNome("El Chiringuito Libre");
		
		PlaceTypeEnum placeType = PlaceTypeEnum.UNKNOW;
		
		NationBO nationBO = typologicalMock.getAllNations().get(0);
		
		CityBO citta = new CityBO();
		citta.setId(1);
		citta.setNome("Roma");
		citta.setNazione(nationBO);
		
		bo1.setPlaceKey("ChIJrRMgU7ZhLxMRxAOFkC7I8Sg");
		bo1.setGpsLat(41.856616);
		bo1.setGpsLong(12.476971);
		bo1.setAddressRoute("Largo Riccardi Beato Placido");
		bo1.setCivico("1");
		bo1.setCap("00154");
		bo1.setPlaceType(placeType);
		bo1.setCity(citta);
		bo1.setDataCreazione("2016-01-01_00-00-00");
		bo1.setDataAggiornamento("2016-01-01_00-00-00");
		
		
		
		PlaceBO bo2 = new PlaceBO();
		bo2.setId(2);
		bo2.setNome("Colosseo");
		
		PlaceTypeEnum placeType2 = PlaceTypeEnum.UNKNOW;
		
		bo2.setPlaceKey("l9d0sRMgU7ZhLxMRxAOFkC83j7f");
		bo2.setGpsLat(41.89021);
		bo2.setGpsLong(12.492231);
		bo2.setAddressRoute("Piazza del Colosseo");
		bo2.setCivico("1");
		bo2.setCap("00184");
		bo2.setPlaceType(placeType2);
		bo2.setCity(citta);	
		bo2.setDataCreazione("2016-01-02");
		bo2.setDataAggiornamento("2016-01-02");		
		
		Set<PlaceBO> places = new HashSet<PlaceBO>();
		places.add(bo1);
		places.add(bo2);
		
		return places;
	}

	@Override
	public WriteResultBO saveOrUpdatePlace(PlaceBO place, String token) throws Exception {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public CityBO getCityByName(String cityName, String nationCode) throws Exception {
		TypologicalPersistenceServiceMock typologicalMock = new TypologicalPersistenceServiceMock();
		CityBO c = new CityBO();
		c.setId(1);
		c.setNome(cityName);
		
		NationBO n = typologicalMock.getAllNations().get(0);
		c.setNazione(n);
		return c;
	}

	@Override
	public NationBO getNationByInternationalCode(String nationCode) throws Exception {
		TypologicalPersistenceServiceMock typologicalMock = new TypologicalPersistenceServiceMock();
		NationBO n = typologicalMock.getAllNations().get(0);
		return n;
	}

	@Override
	public PlaceBO getPlaceByGPS(double gpsLat, double gpsLong) throws Exception {
		Set<PlaceBO> placesByDescription = this.getPlacesByDescription("descrizione",gpsLat, gpsLong);
		PlaceBO bo1 = placesByDescription.iterator().next();
		return bo1;
	}

}
