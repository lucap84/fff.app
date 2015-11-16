package it.fff.persistence.service;

import it.fff.business.common.bo.UpdateResultBO;

public interface SecurityPersistenceService extends PersistenceService{

	public UpdateResultBO logout(int userId) throws Exception;

	public UpdateResultBO generateVerficationCode(String email) throws Exception;

	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws Exception;

	public UpdateResultBO updatePassword(String email, String encodedPassword) throws Exception;

	public UpdateResultBO login(String username, String password) throws Exception;

}
