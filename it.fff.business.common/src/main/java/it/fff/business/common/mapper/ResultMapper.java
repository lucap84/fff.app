package it.fff.business.common.mapper;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.clientserver.common.dto.RegistrationDataResultDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class ResultMapper implements Mapper{

	public static RegistrationDataResultDTO map2DTO(CreateResultBO bo) {
		RegistrationDataResultDTO dto = new RegistrationDataResultDTO();
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

}
