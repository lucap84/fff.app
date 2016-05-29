package it.fff.persistence.service.mock;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.clientserver.common.util.Constants;
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
		
		PlaceTypeEnum placeType = PlaceTypeEnum.POINT_OF_INTEREST;
		
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
		bo1.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		bo1.setDataAggiornamento(Constants.DATE_FORMATTER.format(new Date()));
		
		
		
		PlaceBO bo2 = new PlaceBO();
		bo2.setId(2);
		bo2.setNome("Colosseo");
		
		PlaceTypeEnum placeType2 = PlaceTypeEnum.POINT_OF_INTEREST;
		
		bo2.setPlaceKey("l9d0sRMgU7ZhLxMRxAOFkC83j7f");
		bo2.setGpsLat(41.89021);
		bo2.setGpsLong(12.492231);
		bo2.setAddressRoute("Piazza del Colosseo");
		bo2.setCivico("1");
		bo2.setCap("00184");
		bo2.setPlaceType(placeType2);
		bo2.setCity(citta);	
		bo2.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		bo2.setDataAggiornamento(Constants.DATE_FORMATTER.format(new Date()));
		
		PlaceBO bo3 = new PlaceBO();
		bo3.setId(3);
		bo3.setNome("Via dei Mockatori");
		
		PlaceTypeEnum placeType3 = PlaceTypeEnum.ROUTE;
		
		bo3.setPlaceKey("37d0sRMgU7ZhLxMRxAOFkC83kjF");
		bo3.setGpsLat(41.22021);
		bo3.setGpsLong(12.672231);
		bo3.setAddressRoute("Via dei Mockatori");
		bo3.setCivico("23");
		bo3.setCap("00148");
		bo3.setPlaceType(placeType3);
		bo3.setCity(citta);	
		bo3.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		bo3.setDataAggiornamento(Constants.DATE_FORMATTER.format(new Date()));	
		
		Set<PlaceBO> places = new HashSet<PlaceBO>();
		places.add(bo1);
		places.add(bo2);
		places.add(bo3);
		
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
		
		double propability = 1 / placesByDescription.size();
		for (PlaceBO placeBO : placesByDescription) {
			if(Math.random()<=propability){
				return placeBO;
			} else{
				propability += propability; //Aumento la probabilita' di scegliere un elemento
			}
		}
		
		PlaceBO bo1 = placesByDescription.iterator().next();
		return bo1;
	}

}
