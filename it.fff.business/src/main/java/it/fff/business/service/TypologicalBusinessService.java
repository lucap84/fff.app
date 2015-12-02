package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface TypologicalBusinessService extends BusinessService{

	public List<EventCategoryBO> getAllEventCategories() throws  PersistenceException;

	public List<EventStateBO> getAllEventStates() throws  PersistenceException;

	public List<AttendanceStateBO> getAllAttendanceStates() throws  PersistenceException;

	public List<MessageStandardBO> getAllStandardMessages() throws  PersistenceException;

	public List<AchievementTypeBO> getAllAchievementTypes() throws  PersistenceException;

	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws  PersistenceException;

	public List<LanguageBO> getAllLanguages() throws  PersistenceException;

	public List<NationBO> getAllNations() throws  PersistenceException;;

}
