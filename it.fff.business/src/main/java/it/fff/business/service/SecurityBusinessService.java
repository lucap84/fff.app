package it.fff.business.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.IntegrationException;

public interface SecurityBusinessService extends BusinessService{

	WriteResultBO login(SessionBO session) throws IntegrationException;

	WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws IntegrationException;

	WriteResultBO checkVerificationCode(String email, String verificationcode) throws IntegrationException;

	WriteResultBO generateAndSendVerficationCode(String email) throws IntegrationException;

	WriteResultBO logout(int userIdInt, String deviceId) throws IntegrationException;

	Map<Integer, Map<String, String>> retrieveClientSecrets() throws IntegrationException;

	WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws IntegrationException;

}
