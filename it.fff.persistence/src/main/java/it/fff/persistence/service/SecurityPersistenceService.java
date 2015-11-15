package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;

public interface SecurityPersistenceService extends PersistenceService{

	public UpdateResultBO logout(int userId) throws SQLException;

	public UpdateResultBO generateVerficationCode(String email) throws SQLException;

	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws SQLException;

	public UpdateResultBO updatePassword(String email, String encodedPassword) throws SQLException;

	public UpdateResultBO login(String username, String password) throws SQLException;

}
