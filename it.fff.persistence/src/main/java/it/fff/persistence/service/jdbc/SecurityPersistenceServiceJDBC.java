package it.fff.persistence.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.SecurityPersistenceService;
import it.fff.persistence.util.JdbcUtil;

/**
 * Created by lpelosi on 26/10/16.
 */
public class SecurityPersistenceServiceJDBC implements SecurityPersistenceService {

    private static final Logger logger = LogManager.getLogger(EventPersistenceServiceJDBC.class);

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
        logger.debug("begin login...");

        WriteResultBO bo = new WriteResultBO();
        Connection connection = null;
        PreparedStatement prepStmt = null;

        String email = sessionBO.getAccount().getEmail();
        String password = sessionBO.getAccount().getPassword();
        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_LOGIN_CHECK_ACCOUNT);
            prepStmt.setString(1, email);
            prepStmt.setString(2, password);

            //TODO Esegui in transazione le due query
            ResultSet rs = prepStmt.executeQuery();
            int accountId = -1;
            if (rs.next()) {
                accountId = rs.getInt(1);

                if(accountId<=0){
                    throw new PersistenceException("Account non trovato!");
                }
            }
            //if accountId exists, I create a new active session for the user

            prepStmt = connection.prepareStatement(JdbcUtil.QY_LOGIN_NEWSESSION);
            prepStmt.setString(1, email);
            prepStmt.setString(2, password);
            prepStmt.setInt(3, accountId);

            int affectedRecords = prepStmt.executeUpdate();

            bo.setSuccess(true);
            bo.setWrittenKey(accountId);
            bo.setAffectedRecords(affectedRecords);

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during login() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...login finished");
        return bo;
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
