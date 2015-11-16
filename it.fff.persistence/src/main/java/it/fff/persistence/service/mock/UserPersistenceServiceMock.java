package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public CreateResultBO registerUser(UserEO userEO) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws SQLException {
		eoInput.setImageIdentifier("0101010101010");
		return eoInput;
	}

	@Override
	public UserEO getUser(int userId) throws SQLException {
		UserEO eo = new UserEO();
		eo.setId(userId);
		eo.setNome("nome1");
		eo.setCognome("cognome1");
		return eo;
	}

	@Override
	public UpdateResultBO updateUserData(UserEO eo) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

}
