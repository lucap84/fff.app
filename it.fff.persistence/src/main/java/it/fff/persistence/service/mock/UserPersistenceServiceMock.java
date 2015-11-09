package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public UserEO registerUser(UserEO userEO) throws SQLException {
		UserEO eo = new UserEO();
		eo.setId(1);
		eo.setNome(userEO.getNome()+" persistente");
		eo.setCognome(userEO.getCognome()+" persistente");
		eo.setEmail(userEO.getEmail());
		return eo;
	}

	@Override
	public ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws SQLException {
		eoInput.setImageIdentifier("0101010101010");
		return eoInput;
	}

}
