package it.fff.persistence.service.impl;

import java.sql.SQLException;

import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dao.UserDAO;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceImpl implements UserPersistenceService {

	@Override
	public UserDAO registerUser(UserBO userBO) throws SQLException {
		UserDAO dao = new UserDAO();
		dao.setId(1);
		dao.setNome(userBO.getNome()+" persist");
		dao.setCognome(userBO.getCognome()+" persist");
		dao.setEmail(userBO.getEmail());
		return dao;
	}

}
