package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.clientserver.common.dto.AttendanceDTO;
import it.fff.clientserver.common.dto.AttendanceStateDTO;
import it.fff.clientserver.common.dto.LanguageDTO;

public class AttendanceStateMapper implements Mapper<AttendanceStateDTO,AttendanceStateBO,AttendanceStateEO>{

	private static AttendanceStateMapper mapper;
	
	private AttendanceStateMapper(){
		
	}
	
	public static AttendanceStateMapper getInstance(){
		if(mapper==null){
			mapper= new  AttendanceStateMapper();
		}
		return mapper;
	}
	
	
	@Override
	public List<AttendanceStateBO> mapDTOs2BOs(List<AttendanceStateDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttendanceStateBO mapDTO2BO(AttendanceStateDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttendanceStateEO> mergeBOs2EOs(List<AttendanceStateBO> bos, List<AttendanceStateEO> eso) {
		return null;
	}

	@Override
	public AttendanceStateEO mergeBO2EO(AttendanceStateBO bo, AttendanceStateEO eo) {
		if(bo!=null){
			if(eo==null){
				eo = new AttendanceStateEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
		}
		return eo;
	}

	@Override
	public List<AttendanceStateBO> mapEOs2BOs(List<AttendanceStateEO> eos) {
		List<AttendanceStateBO> bos = new ArrayList<AttendanceStateBO>();
		if(eos!=null){
			AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
			for (AttendanceStateEO eo : eos) {
				bos.add(attendanceStateMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public AttendanceStateBO mapEO2BO(AttendanceStateEO eo) {
		AttendanceStateBO bo = new AttendanceStateBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}

	@Override
	public List<AttendanceStateDTO> mapBOs2DTOs(List<AttendanceStateBO> bos) {
		List<AttendanceStateDTO> dtos = new ArrayList<AttendanceStateDTO>();
		if(bos!=null){
			AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
			for (AttendanceStateBO bo : bos) {
				dtos.add(attendanceStateMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public AttendanceStateDTO mapBO2DTO(AttendanceStateBO bo) {
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

}
