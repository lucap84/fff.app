package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.clientserver.common.dto.LanguageDTO;

public class LanguageMapper implements Mapper {

	public static List<LanguageBO> mapEOs2BOs(List<LanguageEO> eos) {
		List<LanguageBO> bos = new ArrayList<LanguageBO>();
		if(eos!=null){
			for (LanguageEO eo : eos) {
				bos.add(LanguageMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}
	
	public static LanguageBO mapEO2BO(LanguageEO eo) {
		LanguageBO bo = new LanguageBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setIso639_1(eo.getIso639_1());
		}
		return bo;
	}

	public static void mapBO2EO(List<LanguageBO> bos,List<LanguageEO> eos) {
		if(bos!=null){
			for (LanguageBO bo : bos) {
				LanguageEO linguaEO = new LanguageEO();
				LanguageMapper.mapBO2EO(bo,linguaEO);
				eos.add(linguaEO);
			}
		}
	}

	public static void mapBO2EO(LanguageBO bo, LanguageEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setIso639_1IfNotEmpty(bo.getIso639_1());
			eo.setIso639_2IfNotEmpty(bo.getIso639_2());
			eo.setIso639_3IfNotEmpty(bo.getIso639_3());
		}
	}

	public static List<LanguageBO> mapDTO2BO(List<LanguageDTO> dtos) {
		List<LanguageBO> bos = new ArrayList<LanguageBO>();
		if(dtos!=null){
			for (LanguageDTO dto : dtos) {
				bos.add(LanguageMapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	public static LanguageBO mapDTO2BO(LanguageDTO dto) {
		LanguageBO bo = new LanguageBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setIso639_1(dto.getIso639_1());
		}
		return bo;
	}

	public static List<LanguageDTO> mapBO2DTO(List<LanguageBO> bos) {
		List<LanguageDTO> dtos = new ArrayList<LanguageDTO>();
		if(bos!=null){
			for (LanguageBO bo : bos) {
				dtos.add(LanguageMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	public static LanguageDTO mapBO2DTO(LanguageBO bo) {
		LanguageDTO dto = new LanguageDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setIso639_1(bo.getIso639_1());
			dto.setIso639_2(bo.getIso639_2());
			dto.setIso639_3(bo.getIso639_3());
		}
		return dto;
	}

}
