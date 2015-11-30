package it.fff.business.common.mapper;

import it.fff.business.common.bo.NationBO;
import it.fff.business.common.eo.NationEO;
import it.fff.clientserver.common.dto.NationDTO;

public class NationMapper implements Mapper{
	
	public static NationBO mapDTO2BO(NationDTO dto) {
		NationBO bo = new NationBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setInternationalKey(dto.getInternationalKey());
		}
		return bo;
	}
	
	public static void mapBO2EO(NationBO bo, NationEO eo) {
		if(bo!=null){
			if(eo==null){
				eo= new NationEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setInternationalKeyIfNotEmpty(bo.getInternationalKey());
		}
	}
	
	public static NationBO mapEO2BO(NationEO eo) {
		NationBO bo = new NationBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setInternationalKey(eo.getInternationalKey());
		}
		return bo;
	}

	public static NationDTO mapBO2DTO(NationBO bo) {
		NationDTO dto = new NationDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setInternationalKey(bo.getInternationalKey());
			dto.setNome(bo.getNome());
		}
		return dto;
	}	

}
