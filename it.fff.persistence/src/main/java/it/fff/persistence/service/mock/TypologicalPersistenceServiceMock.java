package it.fff.persistence.service.mock;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.service.TypologicalPersistenceService;

public class TypologicalPersistenceServiceMock implements TypologicalPersistenceService{

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws Exception {
		List<EventCategoryBO> bos = new ArrayList<EventCategoryBO>();
		
		EventCategoryBO bo1 = new EventCategoryBO();
		bo1.setId(1);
		bo1.setNome("Qualsiasi");
		bo1.setDescrizione("Qualsiasi");
		
		EventCategoryBO bo2 = new EventCategoryBO();
		bo1.setId(2);
		bo1.setNome("Viaggi");
		bo1.setDescrizione("Viaggi");

		EventCategoryBO bo3 = new EventCategoryBO();
		bo1.setId(3);
		bo1.setNome("Arte e cultura");
		bo1.setDescrizione("Arte e cultura");

		EventCategoryBO bo4 = new EventCategoryBO();
		bo1.setId(4);
		bo1.setNome("Musica");
		bo1.setDescrizione("Musica");

		EventCategoryBO bo5 = new EventCategoryBO();
		bo1.setId(5);
		bo1.setNome("Sport");
		bo1.setDescrizione("Sport");

		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		bos.add(bo4);
		bos.add(bo5);
		return bos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws Exception {
		
		List<EventStateEO> eos = new ArrayList<EventStateEO>();
		EventStateEO eo1 = new EventStateEO();
		eo1.setId(1);
		eo1.setNome("ACTIVE");
		eo1.setDescrizione("EventState Attivo");
		
		EventStateEO eo2 = new EventStateEO();
		eo2.setId(2);
		eo2.setNome("ONGOING");
		eo2.setDescrizione("EventState In corso");

		EventStateEO eo3 = new EventStateEO();
		eo3.setId(3);
		eo3.setNome("FINISHED");
		eo3.setDescrizione("EventState Terminato");
		
		EventStateEO eo4 = new EventStateEO();
		eo4.setId(4);
		eo4.setNome("CANCELED");
		eo4.setDescrizione("EventState annullato");
		
		eos.add(eo1);
		eos.add(eo2);
		eos.add(eo3);
		eos.add(eo4);
		
		List<EventStateEnum> bos = EventStateMapper.getInstance().mapEOs2BOs(eos);
		
		return bos;
	}

	@Override
	public List<AttendanceStateEnum> getAllAttendanceStates() throws Exception {
		List<AttendanceStateEO> eos = new ArrayList<AttendanceStateEO>();
		
		AttendanceStateEO eo1 = new AttendanceStateEO();
		eo1.setId(1);
		eo1.setNome("UNDETECTED");
		eo1.setDescrizione("Non rilevabile");	
		
		AttendanceStateEO eo2 = new AttendanceStateEO();
		eo2.setId(2);
		eo2.setNome("OUTPLACE");
		eo2.setDescrizione("Posizione non coincidente a quella dell’evento");	
		
		AttendanceStateEO eo3 = new AttendanceStateEO();
		eo3.setId(3);
		eo3.setNome("INPLACE");
		eo3.setDescrizione("L’utente è situato nel luogo dell’evento");	
		
		eos.add(eo1);
		eos.add(eo2);
		eos.add(eo3);
		
		List<AttendanceStateEnum> bos = AttendanceStateMapper.getInstance().mapEOs2BOs(eos);
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws Exception {
		List<MessageStandardBO> bos = new ArrayList<MessageStandardBO>();
		
		MessageStandardBO bo1 = new MessageStandardBO();
		bo1.setId(1);
		bo1.setText("Non vi vedo, dove state?");

		MessageStandardBO bo2 = new MessageStandardBO();
		bo2.setId(2);
		bo2.setText("Scusate, sono in ritardo!");
		
		MessageStandardBO bo3 = new MessageStandardBO();
		bo3.setId(3);
		bo3.setText("Mi date un numero da contattare?");
		
		bos.add(bo1);
		
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws Exception {
		List<AchievementTypeBO> bos = new ArrayList<AchievementTypeBO>();
		
		AchievementTypeBO bo1 = new AchievementTypeBO();
		bo1.setId(1);
		bo1.setNome("AchievementType 1");
		bo1.setDescrizione("AchievementType 1");

		AchievementTypeBO bo2 = new AchievementTypeBO();
		bo2.setId(2);
		bo2.setNome("AchievementType 2");
		bo2.setDescrizione("AchievementType 2");
		
		AchievementTypeBO bo3 = new AchievementTypeBO();
		bo3.setId(3);
		bo3.setNome("AchievementType 3");
		bo3.setDescrizione("AchievementType 3");
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);

		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws Exception {
		List<SubscriptionTypeBO> bos = new ArrayList<SubscriptionTypeBO>();
		
		SubscriptionTypeBO bo1 = new SubscriptionTypeBO();
		bo1.setId(1);
		bo1.setNome("Settimanale");
		bo1.setDescrizione("Settimanale");
		bo1.setDurataGiorni(7);
		bo1.setDurataMesi(0);

		SubscriptionTypeBO bo2 = new SubscriptionTypeBO();
		bo2.setId(1);
		bo2.setNome("Mensile");
		bo2.setDescrizione("Mensile");
		bo2.setDurataGiorni(0);
		bo2.setDurataMesi(1);
		
		SubscriptionTypeBO bo3 = new SubscriptionTypeBO();
		bo3.setId(1);
		bo3.setNome("Trimestrale");
		bo3.setDescrizione("Trimestrale");
		bo3.setDurataGiorni(0);
		bo3.setDurataMesi(3);
		
		SubscriptionTypeBO bo4 = new SubscriptionTypeBO();
		bo4.setId(1);
		bo4.setNome("Annuale");
		bo4.setDescrizione("Annuale");
		bo4.setDurataGiorni(0);
		bo4.setDurataMesi(12);
		
		SubscriptionTypeBO bo5 = new SubscriptionTypeBO();
		bo5.setId(1);
		bo5.setNome("Perpetuo");
		bo5.setDescrizione("Perpetuo");
		bo5.setDurataGiorni(999);
		bo5.setDurataMesi(999);
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		bos.add(bo4);
		bos.add(bo5);
		
		return bos;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws Exception {
		List<LanguageBO> bos = new ArrayList<LanguageBO>();
		LanguageBO bo1 = new LanguageBO();
		bo1.setId(1);
		bo1.setNome("Italian");;
		bo1.setIso639_1("it");
		bo1.setIso639_2("ita");
		bo1.setIso639_3("ita");
		
		LanguageBO bo2 = new LanguageBO();
		bo2.setId(2);
		bo2.setNome("English");;
		bo2.setIso639_1("en");
		bo2.setIso639_2("eng");
		bo2.setIso639_3("eng");
		
		LanguageBO bo3 = new LanguageBO();
		bo3.setId(3);
		bo3.setNome("French");;
		bo3.setIso639_1("fr");
		bo3.setIso639_2("fra");
		bo3.setIso639_3("fra");		
		
		bos.add(bo1);
		bos.add(bo2);
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws Exception {
		List<NationBO> bos = new ArrayList<NationBO>();
		NationBO bo1 = new NationBO();
		bo1.setId(1);
		bo1.setNome("Italia");
		bo1.setInternationalKey("380");
		bo1.setInternationalCodeAplha2("IT");
		bo1.setInternationalCodeAplha3("ITA");
		
		NationBO bo2 = new NationBO();
		bo2.setId(2);
		bo2.setNome("Regno Unito");
		bo2.setInternationalKey("826");
		bo1.setInternationalCodeAplha2("BR");
		bo1.setInternationalCodeAplha3("GBR");

		
		NationBO bo3 = new NationBO();
		bo3.setId(3);
		bo3.setNome("Francia");
		bo3.setInternationalKey("250");
		bo1.setInternationalCodeAplha2("FR");
		bo1.setInternationalCodeAplha3("FRA");

		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		return bos;
	}

	@Override
	public List<PlaceTypeEnum> getAllPlaceTypes() throws Exception {
		
		PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_1.setId(1);
		PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_2.setId(2);
		PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_3.setId(3);
		PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_4.setId(4);
		PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_5.setId(5);
		PlaceTypeEnum.AIRPORT.setId(6);
		PlaceTypeEnum.BUS_STATION.setId(7);
		PlaceTypeEnum.CHURCH.setId(8);
		PlaceTypeEnum.COLLOQUIAL_AREA.setId(9);
		PlaceTypeEnum.COUNTRY.setId(10);
		PlaceTypeEnum.ESTABLISHMENT.setId(11);
		PlaceTypeEnum.FINANCE.setId(12);
		PlaceTypeEnum.INTERSECTION.setId(13);
		PlaceTypeEnum.LOCALITY.setId(14);
		PlaceTypeEnum.NATURAL_FEATURE.setId(15);
		PlaceTypeEnum.NEIGHBORHOOD.setId(16);
		PlaceTypeEnum.PARK.setId(17);
		PlaceTypeEnum.PLACE_OF_WORSHIP.setId(18);
		PlaceTypeEnum.POINT_OF_INTEREST.setId(19);
		PlaceTypeEnum.POLITICAL.setId(20);
		PlaceTypeEnum.POST_OFFICE.setId(21);
		PlaceTypeEnum.POSTAL_CODE.setId(22);
		PlaceTypeEnum.PREMISE.setId(23);
		PlaceTypeEnum.ROUTE.setId(24);
		PlaceTypeEnum.STREET_ADDRESS.setId(25);
		PlaceTypeEnum.SUBLOCALITY.setId(26);
		PlaceTypeEnum.SUBLOCALITY_LEVEL_1.setId(27);
		PlaceTypeEnum.SUBLOCALITY_LEVEL_2.setId(28);
		PlaceTypeEnum.SUBLOCALITY_LEVEL_3.setId(29);
		PlaceTypeEnum.SUBLOCALITY_LEVEL_4.setId(30);
		PlaceTypeEnum.SUBLOCALITY_LEVEL_5.setId(31);
		PlaceTypeEnum.SUBPREMISE.setId(32);
		PlaceTypeEnum.SUBWAY_STATION.setId(33);
		PlaceTypeEnum.TRAIN_STATION.setId(34);
		PlaceTypeEnum.TRANSIT_STATION.setId(35);
		PlaceTypeEnum.UNKNOW.setId(36);
		
		List<PlaceTypeEnum> placeTypes = new ArrayList<PlaceTypeEnum>();
		
		placeTypes.add(PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_1);
		placeTypes.add(PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_2);
		placeTypes.add(PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_3);
		placeTypes.add(PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_4);
		placeTypes.add(PlaceTypeEnum.ADMINISTRATIVE_AREA_LEVEL_5);
		placeTypes.add(PlaceTypeEnum.AIRPORT);
		placeTypes.add(PlaceTypeEnum.BUS_STATION);
		placeTypes.add(PlaceTypeEnum.CHURCH);
		placeTypes.add(PlaceTypeEnum.COLLOQUIAL_AREA);
		placeTypes.add(PlaceTypeEnum.COUNTRY);
		placeTypes.add(PlaceTypeEnum.ESTABLISHMENT);
		placeTypes.add(PlaceTypeEnum.FINANCE);
		placeTypes.add(PlaceTypeEnum.INTERSECTION);
		placeTypes.add(PlaceTypeEnum.LOCALITY);
		placeTypes.add(PlaceTypeEnum.NATURAL_FEATURE);
		placeTypes.add(PlaceTypeEnum.NEIGHBORHOOD);
		placeTypes.add(PlaceTypeEnum.PARK);
		placeTypes.add(PlaceTypeEnum.PLACE_OF_WORSHIP);
		placeTypes.add(PlaceTypeEnum.POINT_OF_INTEREST);
		placeTypes.add(PlaceTypeEnum.POLITICAL);
		placeTypes.add(PlaceTypeEnum.POST_OFFICE);
		placeTypes.add(PlaceTypeEnum.POSTAL_CODE);
		placeTypes.add(PlaceTypeEnum.PREMISE);
		placeTypes.add(PlaceTypeEnum.ROUTE);
		placeTypes.add(PlaceTypeEnum.STREET_ADDRESS);
		placeTypes.add(PlaceTypeEnum.SUBLOCALITY);
		placeTypes.add(PlaceTypeEnum.SUBLOCALITY_LEVEL_1);
		placeTypes.add(PlaceTypeEnum.SUBLOCALITY_LEVEL_2);
		placeTypes.add(PlaceTypeEnum.SUBLOCALITY_LEVEL_3);
		placeTypes.add(PlaceTypeEnum.SUBLOCALITY_LEVEL_4);
		placeTypes.add(PlaceTypeEnum.SUBLOCALITY_LEVEL_5);
		placeTypes.add(PlaceTypeEnum.SUBPREMISE);
		placeTypes.add(PlaceTypeEnum.SUBWAY_STATION);
		placeTypes.add(PlaceTypeEnum.TRAIN_STATION);
		placeTypes.add(PlaceTypeEnum.TRANSIT_STATION);
		placeTypes.add(PlaceTypeEnum.UNKNOW);
		
		return placeTypes;
	}

}
