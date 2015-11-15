package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.service.SecurityPersistenceService;

public class SecurityPersistenceServiceMock implements SecurityPersistenceService {

	@Override
	public UpdateResultBO logout(int userId) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public UpdateResultBO generateVerficationCode(String email) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public UpdateResultBO login(String username, String password) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

}
