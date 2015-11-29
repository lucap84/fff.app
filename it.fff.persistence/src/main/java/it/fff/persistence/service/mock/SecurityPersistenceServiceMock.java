package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.service.SecurityPersistenceService;

public class SecurityPersistenceServiceMock implements SecurityPersistenceService {

	@Override
	public UpdateResultBO logout(int userId, String deviceId) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(userId);
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
	public UpdateResultBO login(SessionBO session) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public Map<String, Map<String, String>> retrieveClientSecrets() throws Exception {
		Map<String, Map<String, String>> secrets = new HashMap<String, Map<String,String>>();
		
		Map<String, String> device2SharedKey = new HashMap<String, String>();
		device2SharedKey.put("device-01-mock", "secret-mock-0001");
		
		secrets.put("1", device2SharedKey);
		return secrets;
	}

}
