package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.exception.PersistenceException;

public interface TypologicalPersistenceService extends PersistenceService {

	public List<EventCategoryBO> getAllEventCategories() throws PersistenceException;

	public List<EventStateEnum> getAllEventStates() throws PersistenceException;

	public List<AttendanceStateEnum> getAllAttendanceStates() throws PersistenceException;

	public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException;

	public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException;

	public List<LanguageBO> getAllLanguages() throws PersistenceException;

	public List<NationBO> getAllNations() throws PersistenceException;
	
	public List<PlaceTypeEnum> getAllPlaceTypes() throws PersistenceException;

}
