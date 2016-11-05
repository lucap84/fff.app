package it.fff.persistence.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;

public interface SecurityPersistenceService extends PersistenceService{

	public WriteResultBO logout(int userId, String deviceId) throws PersistenceException;

	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws PersistenceException;

	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException;

	public WriteResultBO login(SessionBO sessionBO) throws PersistenceException;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException;

	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws PersistenceException;

}
