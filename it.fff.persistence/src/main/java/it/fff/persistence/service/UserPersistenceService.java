package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.dao.ProfileImageDAO;
import it.fff.business.common.dao.UserDAO;

public interface UserPersistenceService  extends PersistenceService{

	UserDAO registerUser(UserDAO userDAO) throws SQLException;

	ProfileImageDAO updateProfileImage(ProfileImageDAO daoInput) throws SQLException;;

}
