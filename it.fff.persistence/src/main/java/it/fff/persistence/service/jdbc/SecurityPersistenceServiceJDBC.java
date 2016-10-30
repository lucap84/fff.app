package it.fff.persistence.service.jdbc;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.SecurityPersistenceService;

/**
 * Created by lpelosi on 26/10/16.
 */
public class SecurityPersistenceServiceJDBC implements SecurityPersistenceService {
    @Override
    public WriteResultBO logout(int userId, String deviceId) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO saveVerficationCode(String email, String verificationCode) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO login(SessionBO sessionBO) throws PersistenceException {
        return null;
    }

    @Override
    public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws PersistenceException {
        return null;
    }
}
