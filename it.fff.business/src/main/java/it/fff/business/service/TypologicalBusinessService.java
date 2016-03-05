package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.integration.facade.exception.IntegrationException;

public interface TypologicalBusinessService extends BusinessService{

	public List<EventCategoryBO> getAllEventCategories() throws  IntegrationException;

	public List<EventStateEnum> getAllEventStates() throws  IntegrationException;

	public List<AttendanceStateEnum> getAllAttendanceStates() throws  IntegrationException;

	public List<MessageStandardBO> getAllStandardMessages() throws  IntegrationException;

	public List<AchievementTypeBO> getAllAchievementTypes() throws  IntegrationException;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws  IntegrationException;

	public List<LanguageBO> getAllLanguages() throws  IntegrationException;

	public List<NationBO> getAllNations() throws  IntegrationException;;

}
