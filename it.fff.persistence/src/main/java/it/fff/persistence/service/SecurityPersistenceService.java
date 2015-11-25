package it.fff.persistence.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UpdateResultBO;

public interface SecurityPersistenceService extends PersistenceService{

	public UpdateResultBO logout(int userId, String deviceId) throws Exception;

	public UpdateResultBO generateVerficationCode(String email) throws Exception;

	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws Exception;

	public UpdateResultBO updatePassword(String email, String encodedPassword) throws Exception;

	public UpdateResultBO login(SessionBO sessionBO) throws Exception;

	public Map<String, Map<String, String>> retrieveClientSecrets() throws Exception;

}
