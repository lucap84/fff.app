package it.fff.business.common.mapper;

import it.fff.business.common.bo.NazioneBO;
import it.fff.business.common.eo.NazioneEO;
import it.fff.clientserver.common.dto.NazioneDTO;

public class NazioneMapper implements Mapper{
	
	public static NazioneBO mapDTO2BO(NazioneDTO dto) {
		NazioneBO bo = new NazioneBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setInternationalKey(dto.getInternationalKey());
		}
		return bo;
	}
	
	public static void mapBO2EO(NazioneBO bo, NazioneEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setInternationalKeyIfNotEmpty(bo.getInternationalKey());
		}
	}
	
	public static NazioneBO mapEO2BO(NazioneEO eo) {
		NazioneBO bo = new NazioneBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setInternationalKey(eo.getInternationalKey());
		}
		return bo;
	}

	public static NazioneDTO mapBO2DTO(NazioneBO bo) {
		NazioneDTO dto = new NazioneDTO();
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
