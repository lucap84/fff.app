package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.FeedbackBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.clientserver.common.dto.AttendanceDTO;
import it.fff.clientserver.common.dto.FeedbackDTO;

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
		dto.setId(String.valueOf(bo.getId()));
		dto.setValid(bo.isValid());
		dto.setUser(UserMapper.map2DTO(bo.getUser()));
		return dto;
	}

	public static AttendanceBO map2BO(AttendanceDTO dto) {
		AttendanceBO bo = new AttendanceBO();
		bo.setId(Integer.valueOf(dto.getId()));
		bo.setValid(dto.isValid());
		FeedbackBO fkBO = new FeedbackBO();
		fkBO.setPositiveFeedback(dto.getFeedback().isPositiveFeedback());
		bo.setFeedback(fkBO);
		return bo;
	}

	public static List<AttendanceBO> map2BO(List<AttendanceEO> eos) {
		List<AttendanceBO> bos = new  ArrayList<AttendanceBO>();
		for (AttendanceEO eo : eos) {
			bos.add(AttendanceMapper.map2DTO(eo));
		}
		return bos; 
	}

	public static AttendanceBO map2DTO(AttendanceEO eo) {
		AttendanceBO bo = new AttendanceBO();
		bo.setId(eo.getId());
		bo.setNumPartecipanti(eo.getNumPartecipanti());
		bo.setOrganizer(eo.isOrganizer());
		bo.setStatusId(eo.getStatusId());
		bo.setValid(eo.isValid());
		bo.setUser(UserMapper.map2BO(eo.getUser()));
		return bo;
	}

}
