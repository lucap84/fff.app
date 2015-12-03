package it.fff.business.common.mapper;

import java.util.List;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class ResultMapper implements Mapper<WriteResultDTO, WriteResultBO, Void>{

	private static ResultMapper mapper;
	
	private ResultMapper(){
		
	}
	
	public static ResultMapper getInstance(){
		if(mapper==null){
			mapper= new  ResultMapper();
		}
		return mapper;
	}
	
	public static AuthDataResponseDTO map2AuthDataDTO(WriteResultBO bo) {
		AuthDataResponseDTO dto = new AuthDataResponseDTO();
		if(!bo.isSuccess()){
			for (String errCode : bo.getErrorCodes()) {
				dto.putErrorInMap(errCode, errCode);
			}
		}
		dto.setUserId((String.valueOf(bo.getWrittenKey())));
		return dto;
	}
	
	
	@Override
	public List<WriteResultBO> mapDTOs2BOs(List<WriteResultDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO mapDTO2BO(WriteResultDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Void> mergeBOs2EOs(List<WriteResultBO> bos, List<Void> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void mergeBO2EO(WriteResultBO bo, Void eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WriteResultBO> mapEOs2BOs(List<Void> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultBO mapEO2BO(Void eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WriteResultDTO> mapBOs2DTOs(List<WriteResultBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResultDTO mapBO2DTO(WriteResultBO bo) {
		WriteResultDTO dto = new WriteResultDTO();
		dto.setAffectedRecords(bo.getAffectedRecords());
		if(!bo.isSuccess()){
			for (String errCode : bo.getErrorCodes()) {
				dto.putErrorInMap(errCode, errCode);
			}
		}
		dto.setIdentifier((String.valueOf(bo.getWrittenKey())));
		return dto;
	}	

}
