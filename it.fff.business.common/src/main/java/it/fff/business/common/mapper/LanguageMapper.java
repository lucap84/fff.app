package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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
		List<LanguageBO> bos = null;
		if(dtos!=null){
			bos = new ArrayList<LanguageBO>();
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageDTO dto : dtos) {
				bos.add(languageMapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	@Override
	public LanguageBO mapDTO2BO(LanguageDTO dto) {
		LanguageBO bo = null;
		if(dto!=null){
			bo = new LanguageBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setIso639_1(dto.getIso639_1());
		}
		return bo;
	}

	@Override
	public List<LanguageEO> mergeBOs2EOs(List<LanguageBO> bos, List<LanguageEO> eos, Session session) {
		if(bos!=null){
			if(eos==null){
				eos = new ArrayList<LanguageEO>();
			}
			eos.clear();//elimino vecchie lingue eventualmente presenti per mettere le nuove
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageBO bo : bos) {
				LanguageEO linguaEO = languageMapper.mergeBO2EO(bo, null, session);
				eos.add(linguaEO);
			}
		}
		return eos;
	}

	@Override
	public LanguageEO mergeBO2EO(LanguageBO bo, LanguageEO eo, Session session) {
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
		List<LanguageBO> bos = null;
		if(eos!=null){
			bos = new ArrayList<LanguageBO>();
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageEO eo : eos) {
				bos.add(languageMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public LanguageBO mapEO2BO(LanguageEO eo) {
		LanguageBO bo = null;
		if(eo!=null){
			bo = new LanguageBO();
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setIso639_1(eo.getIso639_1());
		}
		return bo;
	}

	@Override
	public List<LanguageDTO> mapBOs2DTOs(List<LanguageBO> bos) {
		List<LanguageDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<LanguageDTO>();
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			for (LanguageBO bo : bos) {
				dtos.add(languageMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public LanguageDTO mapBO2DTO(LanguageBO bo) {
		LanguageDTO dto = null;
		if(bo!=null){
			dto = new LanguageDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setNome(bo.getNome());
			dto.setIso639_1(bo.getIso639_1());
			dto.setIso639_2(bo.getIso639_2());
			dto.setIso639_3(bo.getIso639_3());
		}
		return dto;
	}

}
