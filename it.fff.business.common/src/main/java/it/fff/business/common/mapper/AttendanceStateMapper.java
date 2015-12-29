package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.clientserver.common.dto.AttendanceDTO;
import it.fff.clientserver.common.dto.LanguageDTO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;

public class AttendanceStateMapper implements Mapper<AttendanceStateEnum,AttendanceStateEnum,AttendanceStateEO>{

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
	public List<AttendanceStateEnum> mapDTOs2BOs(List<AttendanceStateEnum> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttendanceStateEnum mapDTO2BO(AttendanceStateEnum dto) {
		if(dto==null){
			return AttendanceStateEnum.UNKNOW;
		}
		return dto;
	}

	@Override
	public List<AttendanceStateEO> mergeBOs2EOs(List<AttendanceStateEnum> bos, List<AttendanceStateEO> eos, Session session) {
		return null;
	}

	@Override
	public AttendanceStateEO mergeBO2EO(AttendanceStateEnum bo, AttendanceStateEO eo, Session session) {
		if(bo!=null && bo!=AttendanceStateEnum.UNKNOW){
			if(eo==null){
				eo = new AttendanceStateEO();
			}
			eo.setNome(bo.name());
		}
		return eo;
	}

	@Override
	public List<AttendanceStateEnum> mapEOs2BOs(List<AttendanceStateEO> eos) {
		List<AttendanceStateEnum> bos = null;
		if(eos!=null && isInitialized(eos)){
			 bos = new ArrayList<AttendanceStateEnum>();
			AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
			for (AttendanceStateEO eo : eos) {
				bos.add(attendanceStateMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public AttendanceStateEnum mapEO2BO(AttendanceStateEO eo) {
		AttendanceStateEnum bo = AttendanceStateEnum.UNKNOW;
		if(eo!=null && isInitialized(eo)){
			bo = AttendanceStateEnum.valueOf(eo.getNome());
		}
		else{
			bo = AttendanceStateEnum.UNKNOW;
		}
		return bo;
	}

	@Override
	public List<AttendanceStateEnum> mapBOs2DTOs(List<AttendanceStateEnum> bos) {
		List<AttendanceStateEnum> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<AttendanceStateEnum>();
			AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
			for (AttendanceStateEnum bo : bos) {
				dtos.add(attendanceStateMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public AttendanceStateEnum mapBO2DTO(AttendanceStateEnum bo) {
		if(bo==null){
			return AttendanceStateEnum.UNKNOW;
		}
		return bo;
	}

}
