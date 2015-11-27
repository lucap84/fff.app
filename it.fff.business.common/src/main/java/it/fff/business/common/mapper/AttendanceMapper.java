package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.clientserver.common.dto.AttendanceDTO;

public class AttendanceMapper implements Mapper{

	public static List<AttendanceBO> mapDTO2BO(List<AttendanceDTO> dtos) {
		List<AttendanceBO> bos = new  ArrayList<AttendanceBO>();
		if(dtos!=null){
			for (AttendanceDTO dto : dtos) {
				bos.add(AttendanceMapper.mapDTO2BO(dto));
			}
		}
		return bos; 
	}	

	public static AttendanceBO mapDTO2BO(AttendanceDTO dto) {
		AttendanceBO bo = new AttendanceBO();
		if(dto!=null){
			bo.setId(Integer.valueOf(dto.getId()));
			bo.setValid(dto.isValid());
			bo.setPositiveFeedback(dto.getFeedback().isPositiveFeedback());
		}
		return bo;
	}	
	
	public static List<AttendanceDTO> mapBO2DTO(List<AttendanceBO> bos) {
		List<AttendanceDTO> attendancesDTO = new  ArrayList<AttendanceDTO>();
		if(bos!=null){
			for (AttendanceBO bo : bos) {
				attendancesDTO.add(AttendanceMapper.mapBO2DTO(bo));
			}
		}
		return attendancesDTO; 
	}

	public static AttendanceDTO mapBO2DTO(AttendanceBO bo) {
		AttendanceDTO dto = new AttendanceDTO();
		if(bo!=null){
			dto.setId(String.valueOf(bo.getId()));
			dto.setValid(bo.isValid());
			dto.setUser(UserMapper.mapBO2DTO(bo.getUtente()));
		}
		return dto;
	}
	
	public static void mapBO2EO(AttendanceBO bo, AttendanceEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNumPartecipantiIfNotEmpty(bo.getNumPartecipanti());
			
			AttendanceStateEO attStateEO = new AttendanceStateEO();
			AttendanceStateMapper.mapBO2EO(bo.getStato(),attStateEO);
			eo.setStato(attStateEO);
			
			eo.setValid(bo.isValid());
			eo.setOrganizer(bo.isOrganizer());
		}
	}	
	

	public static List<AttendanceBO> mapEO2BO(List<AttendanceEO> eos) {
		List<AttendanceBO> bos = new  ArrayList<AttendanceBO>();
		if(eos!=null){
			for (AttendanceEO eo : eos) {
				bos.add(AttendanceMapper.mapEO2BO(eo));
			}
		}
		return bos; 
	}

	public static AttendanceBO mapEO2BO(AttendanceEO eo) {
		AttendanceBO bo = new AttendanceBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNumPartecipanti(eo.getNumPartecipanti());
			bo.setOrganizer(eo.isOrganizer());
			bo.setPositiveFeedback(eo.isPositiveFeedback());
			bo.setValid(eo.isValid());
			
			EventBO eventBO = new EventBO(); //Evito mapping ciclici (EventBO ha una lista di attendances)!
			eventBO.setId(eo.getEvent().getId());
			bo.setEvent(eventBO);
			
			bo.setStato(AttendanceStateMapper.mapEO2BO(eo.getStato()));
			bo.setUtente(UserMapper.mapEO2BO(eo.getUtente()));
		}
		return bo;
	}



	public static AttendanceBO mapEO2DTO(AttendanceEO eo) {
		AttendanceBO bo = new AttendanceBO();
		bo.setId(eo.getId());
		bo.setNumPartecipanti(eo.getNumPartecipanti());
		bo.setOrganizer(eo.isOrganizer());
		bo.setStato(AttendanceStateMapper.mapEO2BO(eo.getStato()));
		bo.setValid(eo.isValid());
		bo.setUtente(UserMapper.mapEO2BO(eo.getUtente()));
		return bo;
	}


}
