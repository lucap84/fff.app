package it.fff.persistence.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.service.SecurityPersistenceService;

public class SecurityPersistenceServiceHibernate implements SecurityPersistenceService {
	
	private static final Logger logger = LogManager.getLogger(SecurityPersistenceServiceHibernate.class);

	@Override
	public UpdateResultBO logout(int userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO generateVerficationCode(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
