package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.LinguaBO;
import it.fff.business.common.eo.LinguaEO;

public class LinguaMapper implements Mapper {

	public static List<LinguaBO> map2BO(List<LinguaEO> eos) {
		List<LinguaBO> bos = new ArrayList<LinguaBO>();
		for (LinguaEO eo : eos) {
			bos.add(LinguaMapper.map2BO(eo));
		}
		return bos;
	}
	
	public static LinguaBO map2BO(LinguaEO eo) {
		LinguaBO bo = new LinguaBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setCodice(eo.getCodice());
		return bo;
	}	
}
