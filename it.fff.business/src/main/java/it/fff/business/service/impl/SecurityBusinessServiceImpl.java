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
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException {
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}

	@Override
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}

	@Override
	public UpdateResultBO generateAndVerificationCode(String email) throws PersistenceException {
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}

	@Override
	public UpdateResultBO logout(int userIdInt) throws PersistenceException {
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}	
	
}
