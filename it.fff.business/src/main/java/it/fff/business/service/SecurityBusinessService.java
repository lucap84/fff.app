package it.fff.business.service;

import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface SecurityBusinessService extends BusinessService{

	UpdateResultBO login(SessionBO session) throws PersistenceException;

	UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException;

	UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	UpdateResultBO generateAndVerificationCode(String email) throws PersistenceException;

	UpdateResultBO logout(int userIdInt, String deviceId) throws PersistenceException;

	Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException;

}
