package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dao.ProfileImageDAO;
import it.fff.business.common.dao.UserDAO;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public UserDAO registerUser(UserDAO userDAO) throws SQLException {
		UserDAO dao = new UserDAO();
		dao.setId(1);
		dao.setNome(userDAO.getNome()+" persistente");
		dao.setCognome(userDAO.getCognome()+" persistente");
		dao.setEmail(userDAO.getEmail());
		return dao;
	}

	@Override
	public ProfileImageDAO updateProfileImage(ProfileImageDAO daoInput) throws SQLException {
		daoInput.setImgHashCode("0101010101010");
		return daoInput;
	}

}
