package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.service.SecurityPersistenceService;

public class SecurityPersistenceServiceMock implements SecurityPersistenceService {

	@Override
	public WriteResultBO logout(int userId, String deviceId) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(userId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO generateVerficationCode(String email) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO updatePassword(String email, String encodedPassword) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO login(SessionBO session) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws Exception {
		Map<Integer, Map<String, String>> secrets = new HashMap<Integer, Map<String,String>>();
		
		Map<String, String> device2SharedKey = new HashMap<String, String>();
		device2SharedKey.put("device-01-mock", "secret-mock-0001");
		
		secrets.put(1, device2SharedKey);
		return secrets;
	}

}
