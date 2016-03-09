package it.fff.external.service.mock;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.external.service.PlacesExternalService;

public class PlacesExternalServiceMock implements PlacesExternalService {

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws Exception {
		PlaceBO bo1 = new PlaceBO();
		bo1.setId(1);
		bo1.setNome("El Chiringuito Libre");
		
		PlaceTypeEnum placeType = PlaceTypeEnum.UNKNOW;
		
		NationBO nationBO = new NationBO();
		nationBO.setId(1);
		nationBO.setNome("Italia");
		nationBO.setInternationalKey("380");
		nationBO.setInternationalCodeAplha2("IT");
		nationBO.setInternationalCodeAplha3("ITA");
		
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
		
		List<PlaceBO> places = new ArrayList<PlaceBO>();
		places.add(bo1);
		places.add(bo2);
		
		return places;
	}

}
