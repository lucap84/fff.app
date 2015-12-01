package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.UserEO;
import it.fff.clientserver.common.dto.AttendanceDTO;

public class AttendanceMapper implements Mapper<AttendanceDTO,AttendanceBO,AttendanceEO>{

	private static AttendanceMapper mapper;
	
	private AttendanceMapper(){
		
	}
	
	public static AttendanceMapper getInstance(){
		if(mapper==null){
			mapper= new  AttendanceMapper();
		}
		return mapper;
	}
	
	@Override
	public List<AttendanceBO> mapDTOs2BOs(List<AttendanceDTO> dtos) {
		List<AttendanceBO> bos = new  ArrayList<AttendanceBO>();
		if(dtos!=null){
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceDTO dto : dtos) {
				bos.add(attendanceMapper.mapDTO2BO(dto));
			}
		}
		return bos; 
	}	
	
	@Override
	public AttendanceBO mapDTO2BO(AttendanceDTO dto) {
		AttendanceBO bo = new AttendanceBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setValid(dto.isValid());
			if(dto.getFeedback()!=null){
				bo.setPositiveFeedback(dto.getFeedback().isPositiveFeedback());
			}
		}
		return bo;
	}	
	
	@Override
	public List<AttendanceDTO> mapBOs2DTOs(List<AttendanceBO> bos) {
		List<AttendanceDTO> attendancesDTO = new  ArrayList<AttendanceDTO>();
		if(bos!=null){
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceBO bo : bos) {
				attendancesDTO.add(attendanceMapper.mapBO2DTO(bo));
			}
		}
		return attendancesDTO; 
	}

	@Override
	public AttendanceDTO mapBO2DTO(AttendanceBO bo) {
		AttendanceDTO dto = new AttendanceDTO();
		if(bo!=null){
			dto.setId(String.valueOf(bo.getId()));
			dto.setValid(bo.isValid());
			
			UserMapper userMapper = UserMapper.getInstance();
			dto.setUser(userMapper.mapBO2DTO(bo.getUtente()));
		}
		return dto;
	}
	
	@Override
	public AttendanceEO mergeBO2EO(AttendanceBO bo, AttendanceEO eo) {
		if(bo!=null){
			if(eo==null){
				eo = new AttendanceEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNumPartecipantiIfNotEmpty(bo.getNumPartecipanti());
			eo.setOrganizer(bo.isOrganizer());
			eo.setPositiveFeedback(bo.isPositiveFeedback());
			eo.setValid(bo.isValid());
			
			EventEO eventEO = new EventEO(); //Evito mapping ciclici (EventBO ha una lista di attendances)!
			eventEO.setIdIfNotEmpty(bo.getEvent().getId());
			eo.setEvent(eventEO);
			
			AttendanceStateEO stateEO = new AttendanceStateEO();
			stateEO.setIdIfNotEmpty(bo.getStato().getId());
			eo.setStato(stateEO);
			
			UserEO userEO = new UserEO();
			userEO.setIdIfNotEmpty(bo.getUtente().getId());
			eo.setUtente(userEO);
		}
		return eo;
	}	
	
	@Override
	public List<AttendanceBO> mapEOs2BOs(List<AttendanceEO> eos) {
		List<AttendanceBO> bos = new  ArrayList<AttendanceBO>();
		if(eos!=null){
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceEO eo : eos) {
				bos.add(attendanceMapper.mapEO2BO(eo));
			}
		}
		return bos; 
	}

	@Override
	public AttendanceBO mapEO2BO(AttendanceEO eo) {
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
			
			AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
			bo.setStato(attendanceStateMapper.mapEO2BO(eo.getStato()));
			
			UserMapper userMapper = UserMapper.getInstance();
			bo.setUtente(userMapper.mapEO2BO(eo.getUtente()));
		}
		return bo;
	}


	@Override
	public List<AttendanceEO> mergeBOs2EOs(List<AttendanceBO> bos, List<AttendanceEO> eos) {
		if(bos!=null){
			if(eos==null){
				eos = new  ArrayList<AttendanceEO>();
			}
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceBO bo : bos) {
				AttendanceEO eo = attendanceMapper.mergeBO2EO(bo, null);
				eos.add(eo);
			}
		}
		return eos;
	}


}
