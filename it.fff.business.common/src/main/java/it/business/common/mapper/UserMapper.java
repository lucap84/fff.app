package it.business.common.mapper;

import it.fff.business.common.bo.UserBO;
import it.fff.business.common.dao.UserDAO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.UserDTO;

public class UserMapper implements BeanMapper<UserDTO, UserBO, UserDAO> {

	@Override
	public UserBO mapDto2Bo(UserDTO dto) {
		UserBO bo = new UserBO();
		bo.setId(dto.getId());
		bo.setCognome(dto.getCognome());
		bo.setDataNascita(dto.getDataNascita());
		bo.setDescrizione(dto.getDescrizione());
		bo.setEmail(dto.getEmail());
		return bo;
	}

	@Override
	public UserDTO mapBo2Dto(UserBO bo) {
		UserDTO dto = new UserDTO();
		dto.setId(bo.getId());
		dto.setCognome(bo.getCognome());
		dto.setDataNascita(bo.getDataNascita());
		dto.setDescrizione(bo.getDescrizione());
		dto.setEmail(bo.getEmail());
		return dto;
	}

	@Override
	public UserDAO mapBo2Dao(UserBO bo) {
		UserDAO dao = new UserDAO();
		dao.setId(bo.getId());
		dao.setCognome(bo.getCognome());
		dao.setDataNascita(bo.getDataNascita());
		dao.setDescrizione(bo.getDescrizione());
		dao.setEmail(bo.getEmail());
		return dao;
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
