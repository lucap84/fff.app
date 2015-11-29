package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public CreateResultBO registerUser(UserBO userBO) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO eoInput) throws SQLException {
		eoInput.setImgHashCode("0101010101010");
		return eoInput;
	}

	@Override
	public UserBO getUser(int userId) throws SQLException {
		UserBO bo = new UserBO();
		bo.setId(userId);
		bo.setNome("nome1");
		bo.setCognome("cognome1");
		return bo;
	}

	@Override
	public UpdateResultBO updateUserData(UserBO bo) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

}
