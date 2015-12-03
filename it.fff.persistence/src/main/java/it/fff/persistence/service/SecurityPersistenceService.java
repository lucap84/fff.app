package it.fff.persistence.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;

public interface SecurityPersistenceService extends PersistenceService{

	public WriteResultBO logout(int userId, String deviceId) throws Exception;

	public WriteResultBO generateVerficationCode(String email) throws Exception;

	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws Exception;

	public WriteResultBO updatePassword(String email, String encodedPassword) throws Exception;

	public WriteResultBO login(SessionBO sessionBO) throws Exception;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws Exception;

}
