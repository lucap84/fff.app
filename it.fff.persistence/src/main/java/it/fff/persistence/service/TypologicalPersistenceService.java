package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;

public interface TypologicalPersistenceService extends PersistenceService {

	public List<EventCategoryBO> getAllEventCategories() throws Exception;

	public List<EventStateBO> getAllEventStates() throws Exception;

	public List<AttendanceStateBO> getAllAttendanceStates() throws Exception;

	public List<MessageStandardBO> getAllStandardMessages() throws Exception;

	public List<AchievementTypeBO> getAllAchievementTypes() throws Exception;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws Exception;

	public List<LanguageBO> getAllLanguages() throws Exception;

	public List<NationBO> getAllNations() throws Exception;

}
