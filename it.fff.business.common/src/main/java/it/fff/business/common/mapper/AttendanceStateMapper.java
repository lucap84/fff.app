package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.clientserver.common.dto.AttendanceStateDTO;
import it.fff.clientserver.common.dto.LanguageDTO;

public class AttendanceStateMapper implements Mapper{

	public static void mapBO2EO(AttendanceStateBO bo, AttendanceStateEO eo) {
		eo.setIdIfNotEmpty(bo.getId());
		eo.setNomeIfNotEmpty(bo.getNome());
		eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
	}

	public static List<AttendanceStateDTO> mapBO2DTO(List<AttendanceStateBO> bos) {
		List<AttendanceStateDTO> dtos = new ArrayList<AttendanceStateDTO>();
		if(bos!=null){
			for (AttendanceStateBO bo : bos) {
				dtos.add(AttendanceStateMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	public static AttendanceStateDTO mapBO2DTO(AttendanceStateBO bo) {
		AttendanceStateDTO dto = new AttendanceStateDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

	public static List<AttendanceStateBO> mapEOs2BOs(List<AttendanceStateEO> eos) {
		List<AttendanceStateBO> bos = new ArrayList<AttendanceStateBO>();
		if(eos!=null){
			for (AttendanceStateEO eo : eos) {
				bos.add(AttendanceStateMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}	
	
	public static AttendanceStateBO mapEO2BO(AttendanceStateEO eo) {
		AttendanceStateBO bo = new AttendanceStateBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}
	
}
