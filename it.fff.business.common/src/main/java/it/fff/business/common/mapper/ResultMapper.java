package it.fff.business.common.mapper;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class ResultMapper{

	public static AuthDataResponseDTO map2AuthDataDTO(CreateResultBO bo) {
		AuthDataResponseDTO dto = new AuthDataResponseDTO();
		dto.setOk(bo.isSuccess());
		dto.setUserId((String.valueOf(bo.getCreatedKey())));
		return dto;
	}
	
	public static WriteResultDTO map2WriteResultDTO(CreateResultBO bo) {
		WriteResultDTO dto = new WriteResultDTO();
		dto.setAffectedRecords(bo.getNumRecordsCreated());
		dto.setOk(bo.isSuccess());
		dto.setIdentifier((String.valueOf(bo.getCreatedKey())));
		return dto;
	}	

	public static WriteResultDTO map2DTO(UpdateResultBO bo) {
		WriteResultDTO dto = new WriteResultDTO();
		dto.setAffectedRecords(bo.getNumRecordsUpdated());
		dto.setOk(bo.isSuccess());
		dto.setIdentifier(String.valueOf(bo.getUpdatedKey()));
		return dto;
	}
	
	public static AuthDataResponseDTO map2AuthDataDTO(UpdateResultBO bo) {
		AuthDataResponseDTO dto = new AuthDataResponseDTO();
		dto.setOk(bo.isSuccess());
		dto.setUserId((String.valueOf(bo.getUpdatedKey())));
		return dto;
	}	

}
