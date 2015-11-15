package it.fff.business.service.impl;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.service.SecurityBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class SecurityBusinessServiceImpl implements SecurityBusinessService{

	private PersistenceServiceFacade persistenceFacade;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	@Override
	public UpdateResultBO login(String username, String password) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.login(username, password);
		return bo;
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.updatePassword(email, encodedPassword);
		return bo;
	}

	@Override
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.checkVerificationCode(email, verificationcode);
		return bo;
	}

	@Override
	public UpdateResultBO generateAndVerificationCode(String email) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.generateVerficationCode(email);
		//TODO send email dopo esito generazione
		return bo;
	}

	@Override
	public UpdateResultBO logout(int userId) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.logout(userId);
		return bo;
	}	
	
}
