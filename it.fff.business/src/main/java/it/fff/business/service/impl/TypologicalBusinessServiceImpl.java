package it.fff.business.service.impl;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.service.TypologicalBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class TypologicalBusinessServiceImpl implements TypologicalBusinessService {

	private PersistenceServiceFacade persistenceFacade;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws PersistenceException {
		List<EventCategoryBO> bos = persistenceFacade.getAllEventCategories();
		return bos;
	}

	@Override
	public List<EventStateBO> getAllEventStates() throws PersistenceException {
		List<EventStateBO> bos = persistenceFacade.getAllEventStates();
		return bos;
	}

	@Override
	public List<AttendanceStateBO> getAllAttendanceStates() throws PersistenceException {
		List<AttendanceStateBO> bos = persistenceFacade.getAllAttendanceStates();
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException {
		List<MessageStandardBO> bos = persistenceFacade.getAllStandardMessages();
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException {
		List<AchievementTypeBO> bos = persistenceFacade.getAllAchievementTypes();
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException {
		List<SubscriptionTypeBO> bos = persistenceFacade.getAllSubscriptionTypes();
		return bos;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws PersistenceException {
		List<LanguageBO> bos = persistenceFacade.getAllLanguages();
		return bos;
	}
}
