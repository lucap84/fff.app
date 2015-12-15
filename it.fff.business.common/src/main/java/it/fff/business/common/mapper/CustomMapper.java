package it.fff.business.common.mapper;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.LoginInputDTO;

public class CustomMapper {
	
	private static CustomMapper mapper;
	
	private CustomMapper(){
		
	}
	
	public static CustomMapper getInstance(){
		if(mapper==null){
			mapper= new  CustomMapper();
		}
		return mapper;
	}

	public AuthDataResponseDTO mapWriteResult2AuthData(WriteResultBO bo) {
		AuthDataResponseDTO dto = new AuthDataResponseDTO();
		if(!bo.isSuccess()){
			for (String errCode : bo.getErrorCodes()) {
				dto.putErrorInMap(errCode, errCode);
			}
		}
		dto.setUserId((String.valueOf(bo.getWrittenKey())));
		return dto;
	}

	public SessionBO mapLoginInput2Session(LoginInputDTO loginInfo) {
		AccountBO accBO = new AccountBO();
		accBO.setEmail(loginInfo.getEmail());
		accBO.setPassword(loginInfo.getPassword());
		
		SessionBO sessBO = new SessionBO();
		sessBO.setDeviceId(loginInfo.getDeviceId());
		sessBO.setAccount(accBO);
		return sessBO;
	}
	
	
	
}
