package it.fff.persistence.service.jdbc;

import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.TypologicalPersistenceService;

/**
 * Created by lpelosi on 26/10/16.
 */
public class TypologicalPersistenceServiceJDBC implements TypologicalPersistenceService {
    @Override
    public List<EventCategoryBO> getAllEventCategories() throws PersistenceException {
        return null;
    }

    @Override
    public List<EventStateEnum> getAllEventStates() throws PersistenceException {
        return null;
    }

    @Override
    public List<AttendanceStateEnum> getAllAttendanceStates() throws PersistenceException {
        return null;
    }

    @Override
    public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException {
        return null;
    }

    @Override
    public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException {
        return null;
    }

    @Override
    public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException {
        return null;
    }

    @Override
    public List<LanguageBO> getAllLanguages() throws PersistenceException {
        return null;
    }

    @Override
    public List<NationBO> getAllNations() throws PersistenceException {
        return null;
    }

    @Override
    public List<PlaceTypeEnum> getAllPlaceTypes() throws PersistenceException {
        return null;
    }
}
