package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.clientserver.common.dto.LanguageDTO;

public class LanguageMapper implements Mapper<LanguageDTO,LanguageBO,LanguageEO>{

	private static LanguageMapper mapper;
	
	private LanguageMapper(){
		
	}
	
	public static LanguageMapper getInstance(){
		if(mapper==null){
			mapper= new  LanguageMapper();
		}
		return mapper;
	}
	

	@Override
	public List<LanguageBO> mapDTOs2BOs(List<LanguageDTO> dtos) {
		List<LanguageBO> bos = new ArrayList<LanguageBO>();
		if(dtos!=null){
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageDTO dto : dtos) {
				bos.add(languageMapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	@Override
	public LanguageBO mapDTO2BO(LanguageDTO dto) {
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

	@Override
	public List<LanguageEO> mergeBOs2EOs(List<LanguageBO> bos, List<LanguageEO> eos) {
		if(bos!=null){
			if(eos==null){
				eos = new ArrayList<LanguageEO>();
			}
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageBO bo : bos) {
				LanguageEO linguaEO = languageMapper.mergeBO2EO(bo, null);
				eos.add(linguaEO);
			}
		}
		return eos;
	}

	@Override
	public LanguageEO mergeBO2EO(LanguageBO bo, LanguageEO eo) {
		if(bo!=null){
			if(eo==null){
				eo = new LanguageEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setIso639_1IfNotEmpty(bo.getIso639_1());
			eo.setIso639_2IfNotEmpty(bo.getIso639_2());
			eo.setIso639_3IfNotEmpty(bo.getIso639_3());
		}
		return eo;
	}

	@Override
	public List<LanguageBO> mapEOs2BOs(List<LanguageEO> eos) {
		List<LanguageBO> bos = new ArrayList<LanguageBO>();
		if(eos!=null){
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageEO eo : eos) {
				bos.add(languageMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public LanguageBO mapEO2BO(LanguageEO eo) {
		LanguageBO bo = new LanguageBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setIso639_1(eo.getIso639_1());
		}
		return bo;
	}

	@Override
	public List<LanguageDTO> mapBOs2DTOs(List<LanguageBO> bos) {
		List<LanguageDTO> dtos = new ArrayList<LanguageDTO>();
		if(bos!=null){
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageBO bo : bos) {
				dtos.add(languageMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public LanguageDTO mapBO2DTO(LanguageBO bo) {
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
