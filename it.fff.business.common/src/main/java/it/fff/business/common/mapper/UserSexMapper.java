package it.fff.business.common.mapper;

import java.util.List;

import org.hibernate.Session;

import it.fff.clientserver.common.enums.UserSexEnum;

public class UserSexMapper implements Mapper<UserSexEnum, UserSexEnum, Void>{

	@Override
	public List<UserSexEnum> mapDTOs2BOs(List<UserSexEnum> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSexEnum mapDTO2BO(UserSexEnum dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Void> mergeBOs2EOs(List<UserSexEnum> bos, List<Void> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void mergeBO2EO(UserSexEnum bo, Void eo, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserSexEnum> mapEOs2BOs(List<Void> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSexEnum mapEO2BO(Void eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserSexEnum> mapBOs2DTOs(List<UserSexEnum> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSexEnum mapBO2DTO(UserSexEnum bo) {
		// TODO Auto-generated method stub
		return null;
	}

}
