package it.fff.business.common.mapper;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;

public class CustomMapper {

	public static AuthDataResponseDTO mapWriteResult2AuthData(WriteResultBO bo) {
		AuthDataResponseDTO dto = new AuthDataResponseDTO();
		if(!bo.isSuccess()){
			for (String errCode : bo.getErrorCodes()) {
				dto.putErrorInMap(errCode, errCode);
			}
		}
		dto.setUserId((String.valueOf(bo.getWrittenKey())));
		return dto;
	}
	
}
