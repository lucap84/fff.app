package it.fff.business.service.impl;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.service.TypologicalBusinessService;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.integration.facade.exception.IntegrationException;
import it.fff.integration.facade.service.IntegrationServiceFacade;

public class TypologicalBusinessServiceImpl implements TypologicalBusinessService {

	private IntegrationServiceFacade integrationFacade;


	public IntegrationServiceFacade getIntegrationFacade() {
		return integrationFacade;
	}

	public void setIntegrationFacade(IntegrationServiceFacade integrationFacade) {
		this.integrationFacade = integrationFacade;
	}

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws IntegrationException {
		List<EventCategoryBO> bos = integrationFacade.getAllEventCategories();
		return bos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws IntegrationException {
		List<EventStateEnum> bos = integrationFacade.getAllEventStates();
		return bos;
	}

	@Override
	public List<AttendanceStateEnum> getAllAttendanceStates() throws IntegrationException {
		List<AttendanceStateEnum> bos = integrationFacade.getAllAttendanceStates();
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws IntegrationException {
		List<MessageStandardBO> bos = integrationFacade.getAllStandardMessages();
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws IntegrationException {
		List<AchievementTypeBO> bos = integrationFacade.getAllAchievementTypes();
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws IntegrationException {
		List<SubscriptionTypeBO> bos = integrationFacade.getAllSubscriptionTypes();
		return bos;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws IntegrationException {
		List<LanguageBO> bos = integrationFacade.getAllLanguages();
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws IntegrationException {
		List<NationBO> bos = integrationFacade.getAllNations();
		return bos;
	}
}
