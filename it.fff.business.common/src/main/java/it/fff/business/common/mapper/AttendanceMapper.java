package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.clientserver.common.dto.AttendanceDTO;

public class AttendanceMapper implements Mapper{

	public static List<AttendanceDTO> map2DTO(List<AttendanceBO> attendancesBO) {
		List<AttendanceDTO> attendancesDTO = new  ArrayList<AttendanceDTO>();
		for (AttendanceBO bo : attendancesBO) {
			attendancesDTO.add(AttendanceMapper.map2DTO(bo));
		}
		return attendancesDTO; 
	}

	public static AttendanceDTO map2DTO(AttendanceBO bo) {
		AttendanceDTO dto = new AttendanceDTO();
		dto.setId(bo.getId());
		dto.setValid(bo.isValid());
		return dto;
	}

	public static AttendanceBO map2BO(AttendanceDTO dto) {
		AttendanceBO bo = new AttendanceBO();
		bo.setId(dto.getId());
		bo.setValid(dto.isValid());
		return bo;
	}

}
