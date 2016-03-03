package it.fff.business.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.PersistenceException;

public interface SecurityBusinessService extends BusinessService{

	WriteResultBO login(SessionBO session) throws PersistenceException;

	WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException;

	WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	WriteResultBO generateAndSendVerficationCode(String email) throws PersistenceException;

	WriteResultBO logout(int userIdInt, String deviceId) throws PersistenceException;

	Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException;

	WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws PersistenceException;

}
