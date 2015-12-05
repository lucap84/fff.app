package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.mapper.AccountMapper;
import it.fff.business.common.mapper.LanguageMapper;
import it.fff.business.common.mapper.NationMapper;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public WriteResultBO registerUser(UserBO userBO) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
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
		bo.setId(1);
		
		bo.setNome("nome");
		bo.setCognome("cognome");
		bo.setSesso("M");
		bo.setDataNascita("1900-01-01");
		bo.setDescrizione("descrizione");
		
		bo.setLastPositionDate("1900-01-02");
		bo.setLastPositionLat(1.23);
		bo.setLastPositionLong(3.45);
		return bo;
	}

	@Override
	public WriteResultBO updateUserData(UserBO bo) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

}
