package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.LinguaBO;
import it.fff.business.common.eo.LinguaEO;
import it.fff.clientserver.common.dto.LinguaDTO;

public class LinguaMapper implements Mapper {

	public static List<LinguaBO> mapEO2BO(List<LinguaEO> eos) {
		List<LinguaBO> bos = new ArrayList<LinguaBO>();
		if(eos!=null){
			for (LinguaEO eo : eos) {
				bos.add(LinguaMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}
	
	public static LinguaBO mapEO2BO(LinguaEO eo) {
		LinguaBO bo = new LinguaBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setIso639_1(eo.getIso639_1());
		}
		return bo;
	}

	public static void mapBO2EO(List<LinguaBO> bos,List<LinguaEO> eos) {
		if(bos!=null){
			for (LinguaBO bo : bos) {
				LinguaEO linguaEO = new LinguaEO();
				LinguaMapper.mapBO2EO(bo,linguaEO);
				eos.add(linguaEO);
			}
		}
	}

	public static void mapBO2EO(LinguaBO bo, LinguaEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setIso639_1IfNotEmpty(bo.getIso639_1());
			eo.setIso639_2IfNotEmpty(bo.getIso639_2());
			eo.setIso639_3IfNotEmpty(bo.getIso639_3());
		}
	}

	public static List<LinguaBO> mapDTO2BO(List<LinguaDTO> dtos) {
		List<LinguaBO> bos = new ArrayList<LinguaBO>();
		if(dtos!=null){
			for (LinguaDTO dto : dtos) {
				bos.add(LinguaMapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	public static LinguaBO mapDTO2BO(LinguaDTO dto) {
		LinguaBO bo = new LinguaBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setIso639_1(dto.getIso639_1());
		}
		return bo;
	}	
}
