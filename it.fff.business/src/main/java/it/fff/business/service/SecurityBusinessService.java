package it.fff.business.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.IntegrationException;

public interface SecurityBusinessService extends BusinessService{

	public WriteResultBO login(SessionBO session) throws IntegrationException;

	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws IntegrationException;

	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws IntegrationException;

	public WriteResultBO generateAndSendVerficationCode(String email) throws IntegrationException;

	public WriteResultBO logout(int userIdInt, String deviceId) throws IntegrationException;

	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws IntegrationException;

	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws IntegrationException;

	public String[] getFacebookToken(String code) throws IntegrationException;

}
