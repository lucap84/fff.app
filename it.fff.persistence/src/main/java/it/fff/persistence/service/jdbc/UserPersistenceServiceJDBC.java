package it.fff.persistence.service.jdbc;

import java.util.List;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.UserPersistenceService;

/**
 * Created by lpelosi on 26/10/16.
 */
public class UserPersistenceServiceJDBC implements UserPersistenceService {
    @Override
    public WriteResultBO registerUser(UserBO userBO) throws PersistenceException {
        return null;
    }

    @Override
    public UserBO getUser(int userId) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO updateUserData(UserBO eo) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO cancelAttendance(int eventId, int userId) throws PersistenceException {
        return null;
    }

    @Override
    public EmailInfoBO getEmailInfo(String email) throws PersistenceException {
        return null;
    }

    @Override
    public List<FeedbackEnum> getUserFeedbacks(int userId) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO updateProfileImage(ProfileImageBO imageBO) throws PersistenceException {
        return null;
    }

    @Override
    public ProfileImageBO readProfileImage(int userId) throws PersistenceException {
        return null;
    }

    @Override
    public List<AttendanceBO> getAttendancesByUser(int userId) throws PersistenceException {
        return null;
    }

    @Override
    public AccountBO getUserAccountByFacebookId(long facebookId) throws PersistenceException {
        return null;
    }

    @Override
    public AccountBO getUserAccountByEmail(String email) throws PersistenceException {
        return null;
    }
}
