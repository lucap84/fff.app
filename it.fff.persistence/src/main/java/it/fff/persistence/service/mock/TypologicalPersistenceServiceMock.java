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
		EventCategoryBO bo = new EventCategoryBO();
		bo.setId(1);
		bo.setNome("EventCategory nome");
		bo.setDescrizione("EventCategory descr");
		bos.add(bo);
		return bos;
	}

	@Override
	public List<EventStateBO> getAllEventStates() throws Exception {
		List<EventStateBO> bos = new ArrayList<EventStateBO>();
		EventStateBO bo1 = new EventStateBO();
		bo1.setId(1);
		bo1.setNome("ACTIVE");
		bo1.setDescrizione("EventState attivo");
		
		EventStateBO bo2 = new EventStateBO();
		bo2.setId(2);
		bo2.setNome("CANCELED");
		bo2.setDescrizione("EventState annullato");
		
		bos.add(bo1);
		bos.add(bo2);
		return bos;
	}

	@Override
	public List<AttendanceStateBO> getAllAttendanceStates() throws Exception {
		List<AttendanceStateBO> bos = new ArrayList<AttendanceStateBO>();
		AttendanceStateBO bo = new AttendanceStateBO();
		bo.setId(1);
		bo.setNome("AttendanceState nome");
		bo.setDescrizione("AttendanceState descr");		
		bos.add(bo);
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws Exception {
		List<MessageStandardBO> bos = new ArrayList<MessageStandardBO>();
		MessageStandardBO bo = new MessageStandardBO();
		bo.setId(1);
		bo.setText("testo messaggio standard");
		bos.add(bo);
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws Exception {
		List<AchievementTypeBO> bos = new ArrayList<AchievementTypeBO>();
		AchievementTypeBO bo = new AchievementTypeBO();
		bo.setId(1);
		bo.setNome("AchievementType nome");
		bo.setDescrizione("AchievementType descr");
		bos.add(bo);
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws Exception {
		List<SubscriptionTypeBO> bos = new ArrayList<SubscriptionTypeBO>();
		SubscriptionTypeBO bo = new SubscriptionTypeBO();
		bo.setId(1);
		bo.setNome("SubscriptionType nome");
		bo.setDescrizione("SubscriptionType descr");
		bos.add(bo);		
		return bos;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws Exception {
		List<LanguageBO> bos = new ArrayList<LanguageBO>();
		LanguageBO bo1 = new LanguageBO();
		bo1.setId(1);
		bo1.setNome("Italiano");;
		bo1.setIso639_1("Iso639_1");
		bo1.setIso639_2("Iso639_2");
		bo1.setIso639_3("Iso639_3");
		
		LanguageBO bo2 = new LanguageBO();
		bo2.setId(2);
		bo2.setNome("English");;
		bo2.setIso639_1("Iso639_1");
		bo2.setIso639_2("Iso639_2");
		bo2.setIso639_3("Iso639_3");
		
		
		bos.add(bo1);
		bos.add(bo2);
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws Exception {
		List<NationBO> bos = new ArrayList<NationBO>();
		NationBO bo1 = new NationBO();
		bo1.setId(1);
		bo1.setNome("ItaliaMock");
		bo1.setInternationalKey("IT");
		
		NationBO bo2 = new NationBO();
		bo2.setId(2);
		bo2.setNome("SvizzeraMock");
		bo2.setInternationalKey("SW");
		
		NationBO bo3 = new NationBO();
		bo3.setId(3);
		bo3.setNome("AustriaMock");
		bo3.setInternationalKey("AU");
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		return bos;
	}

}
