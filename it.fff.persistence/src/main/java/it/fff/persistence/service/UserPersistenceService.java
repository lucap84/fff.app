package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dao.UserDAO;

public interface UserPersistenceService  extends PersistenceService{

	UserDAO registerUser(UserBO userBO) throws SQLException;

}
