package it.fff.business.common.mapper;

import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.eo.AttendanceStateEO;

public class AttendanceStateMapper implements Mapper{

	public static AttendanceStateBO mapEO2BO(AttendanceStateEO eo) {
		AttendanceStateBO bo = new AttendanceStateBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}
	
	public static void mapBO2EO(AttendanceStateBO bo, AttendanceStateEO eo) {
		eo.setIdIfNotEmpty(bo.getId());
		eo.setNomeIfNotEmpty(bo.getNome());
		eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
	}	
	
}
