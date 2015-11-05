package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;

public interface UserPersistenceService  extends PersistenceService{

	UserEO registerUser(UserEO userEO) throws SQLException;

	ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws SQLException;;

}
