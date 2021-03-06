package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.SecurityPersistenceService;

public class SecurityPersistenceServiceMock implements SecurityPersistenceService {
	
	public static Map<Integer, Map<String, String>> secrets = new HashMap<Integer, Map<String,String>>();

	@Override
	public WriteResultBO logout(int userId, String deviceId) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(userId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO saveVerficationCode(String email, String verificationCode) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(userId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO login(SessionBO session) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException {
		
//		Map<String, String> device2SharedKeyA = new HashMap<String, String>();
//		device2SharedKeyA.put("device-01-mock", "24:2B:ED:10:17:A0:13:1E:3A:6C:19:63:81:DB:16:00:BD:02:10:86:71:A3:D4:E8:AE:EB:0E:C7:D7:B9:05:15:52:2F:62:C1:E3:37:A0:E4:AE:25:11:DE:1A:56:47:1A:16:FD:23:84:22:9E:71:D2:A8:FF:02:E2:85:25:3B:A6");
//		device2SharedKeyA.put("device-02-mock", "25:2B:ED:10:17:A0:13:1E:3A:6C:19:63:81:DB:16:00:BD:02:10:86:71:A3:D4:E8:AE:EB:0E:C7:D7:B9:05:15:52:2F:62:C1:E3:37:A0:E4:AE:25:11:DE:1A:56:47:1A:16:FD:23:84:22:9E:71:D2:A8:FF:02:E2:85:25:3B:A6");
//		device2SharedKeyA.put("device-02-mock", "26:2B:ED:10:17:A0:13:1E:3A:6C:19:63:81:DB:16:00:BD:02:10:86:71:A3:D4:E8:AE:EB:0E:C7:D7:B9:05:15:52:2F:62:C1:E3:37:A0:E4:AE:25:11:DE:1A:56:47:1A:16:FD:23:84:22:9E:71:D2:A8:FF:02:E2:85:25:3B:A6");
//
//		Map<String, String> device2SharedKeyB = new HashMap<String, String>();
//		device2SharedKeyB.put("device-04-mock", "27:2B:ED:10:17:A0:13:1E:3A:6C:19:63:81:DB:16:00:BD:02:10:86:71:A3:D4:E8:AE:EB:0E:C7:D7:B9:05:15:52:2F:62:C1:E3:37:A0:E4:AE:25:11:DE:1A:56:47:1A:16:FD:23:84:22:9E:71:D2:A8:FF:02:E2:85:25:3B:A6");
//		
//		SecurityPersistenceServiceMock.secrets.put(1, device2SharedKeyA);
//		SecurityPersistenceServiceMock.secrets.put(2, device2SharedKeyB);
		return SecurityPersistenceServiceMock.secrets;
	}

	@Override
	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

}
