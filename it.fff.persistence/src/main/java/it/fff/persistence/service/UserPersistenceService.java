package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;

public interface UserPersistenceService  extends PersistenceService{

	public UserEO registerUser(UserEO userEO) throws SQLException;

	public ProfileImageEO updateProfileImage(ProfileImageEO eoInput) throws SQLException;

	public UserEO getUser(int userId) throws SQLException;

	public UpdateResultBO updateUserData(UserEO eo) throws SQLException;

}
