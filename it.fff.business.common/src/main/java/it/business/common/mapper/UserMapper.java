package it.business.common.mapper;

import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dao.UserDAO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.UserDTO;

public class UserMapper implements BeanMapper<UserDTO, UserBO, UserDAO> {

	@Override
	public UserBO mapDto2Bo(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO mapBo2Dto(UserBO bo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDAO mapBo2Dao(UserBO bo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBO mapDao2Bo(UserDAO dao) {
		UserBO bo = new UserBO();
		bo.setId(dao.getId());
		bo.setCognome(dao.getCognome());
		bo.setDataNascita(dao.getDataNascita());
		bo.setDescrizione(dao.getDescrizione());
		bo.setEmail(dao.getEmail());
		return bo;
	}

	public UserBO mapCreateUserDto2Bo(CreateUserDTO dto) {
		UserBO bo = new UserBO();
		bo.setCognome(dto.getCognome());
		bo.setDataNascita(dto.getDataNascita());
		bo.setDescrizione(dto.getDescrizione());
		bo.setEmail(dto.getEmail());
		return bo;
	}	
}
