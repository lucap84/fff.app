package it.fff.business.common.mapper;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class ResultMapper {

	public WriteResultDTO map2Dto(CreateResultBO bo) {
		WriteResultDTO dto = new WriteResultDTO();
		dto.setAffectedRecords(bo.getNumRecordsCreated());
		dto.setOk(bo.isSuccess());
		dto.setIdentifier(String.valueOf(bo.getCreatedKey()));
		return dto;
	}

	public WriteResultDTO map2Dto(UpdateResultBO bo) {
		WriteResultDTO dto = new WriteResultDTO();
		dto.setAffectedRecords(bo.getNumRecordsUpdated());
		dto.setOk(bo.isSuccess());
		dto.setIdentifier(String.valueOf(bo.getUpdatedKey()));
		return dto;
	}

}
