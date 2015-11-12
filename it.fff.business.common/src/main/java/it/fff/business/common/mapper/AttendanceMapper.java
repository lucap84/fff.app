package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.clientserver.common.dto.AttendanceDTO;

public class AttendanceMapper {

	public List<AttendanceDTO> bo2dto(List<AttendanceBO> attendancesBO) {
		List<AttendanceDTO> attendancesDTO = new  ArrayList<AttendanceDTO>();
		for (AttendanceBO bo : attendancesBO) {
			attendancesDTO.add(this.bo2dto(bo));
		}
		return attendancesDTO; 
	}

	public AttendanceDTO bo2dto(AttendanceBO bo) {
		AttendanceDTO dto = new AttendanceDTO();
		dto.setId(bo.getId());
		dto.setValid(bo.isValid());
		return dto;
	}

}
