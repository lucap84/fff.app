package it.fff.persistence.service.mock;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
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
	public List<EventStateBO> getAllEventStates() throws Exception {
		List<EventStateBO> bos = new ArrayList<EventStateBO>();
		EventStateBO bo1 = new EventStateBO();
		bo1.setId(1);
		bo1.setNome("Attivo");
		bo1.setDescrizione("EventState Attivo");
		
		EventStateBO bo2 = new EventStateBO();
		bo2.setId(2);
		bo2.setNome("In corso");
		bo2.setDescrizione("EventState In corso");

		EventStateBO bo3 = new EventStateBO();
		bo3.setId(3);
		bo3.setNome("Terminato");
		bo3.setDescrizione("EventState Terminato");
		
		EventStateBO bo4 = new EventStateBO();
		bo4.setId(4);
		bo4.setNome("Annullato");
		bo4.setDescrizione("EventState annullato");
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		bos.add(bo4);
		
		return bos;
	}

	@Override
	public List<AttendanceStateBO> getAllAttendanceStates() throws Exception {
		List<AttendanceStateBO> bos = new ArrayList<AttendanceStateBO>();
		
		AttendanceStateBO bo1 = new AttendanceStateBO();
		bo1.setId(1);
		bo1.setNome("Rosso");
		bo1.setDescrizione("Non rilevabile");	
		
		AttendanceStateBO bo2 = new AttendanceStateBO();
		bo2.setId(2);
		bo2.setNome("Giallo");
		bo2.setDescrizione("Posizione non coincidente a quella dell’evento");	
		
		AttendanceStateBO bo3 = new AttendanceStateBO();
		bo3.setId(3);
		bo3.setNome("Verde");
		bo3.setDescrizione("L’utente è situato nel luogo dell’evento");	
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		
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

		SubscriptionTypeBO bo2 = new SubscriptionTypeBO();
		bo2.setId(1);
		bo2.setNome("Mensile");
		bo2.setDescrizione("Mensile");
		
		SubscriptionTypeBO bo3 = new SubscriptionTypeBO();
		bo3.setId(1);
		bo3.setNome("Trimestrale");
		bo3.setDescrizione("Trimestrale");
		
		SubscriptionTypeBO bo4 = new SubscriptionTypeBO();
		bo4.setId(1);
		bo4.setNome("Annuale");
		bo4.setDescrizione("Annuale");
		
		SubscriptionTypeBO bo5 = new SubscriptionTypeBO();
		bo5.setId(1);
		bo5.setNome("Perpetuo");
		bo5.setDescrizione("Perpetuo");
		
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
		bo1.setInternationalCode("ITA");
		
		NationBO bo2 = new NationBO();
		bo2.setId(2);
		bo2.setNome("Regno Unito");
		bo2.setInternationalKey("826");
		bo2.setInternationalCode("GBR");
		
		NationBO bo3 = new NationBO();
		bo3.setId(3);
		bo3.setNome("Francia");
		bo3.setInternationalKey("250");
		bo3.setInternationalCode("FRA");
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		return bos;
	}

}
