package it.fff.business.service;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface SecurityBusinessService extends BusinessService{

	UpdateResultBO login(String username, String password) throws PersistenceException;

	UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException;

	UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException;

	UpdateResultBO generateAndVerificationCode(String email) throws PersistenceException;

	UpdateResultBO logout(int userIdInt) throws PersistenceException;

}
