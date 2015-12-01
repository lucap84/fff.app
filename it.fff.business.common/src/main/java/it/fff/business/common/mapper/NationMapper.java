package it.fff.business.common.mapper;

import java.util.List;

import it.fff.business.common.bo.NationBO;
import it.fff.business.common.eo.NationEO;
import it.fff.clientserver.common.dto.NationDTO;

public class NationMapper implements Mapper<NationDTO,NationBO,NationEO>{
	
	private static NationMapper mapper;
	
	private NationMapper(){
		
	}
	
	public static NationMapper getInstance(){
		if(mapper==null){
			mapper= new  NationMapper();
		}
		return mapper;
	}
	
	@Override
	public NationBO mapDTO2BO(NationDTO dto) {
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
	
	@Override
	public NationEO mergeBO2EO(NationBO bo, NationEO eo) {
		if(bo!=null){
			if(eo==null){
				eo= new NationEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setInternationalKeyIfNotEmpty(bo.getInternationalKey());
		}
		return eo;
	}
	
	@Override
	public NationBO mapEO2BO(NationEO eo) {
		NationBO bo = new NationBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setInternationalKey(eo.getInternationalKey());
		}
		return bo;
	}

	@Override
	public NationDTO mapBO2DTO(NationBO bo) {
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

	@Override
	public List<NationBO> mapDTOs2BOs(List<NationDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NationEO> mergeBOs2EOs(List<NationBO> bos, List<NationEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NationBO> mapEOs2BOs(List<NationEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NationDTO> mapBOs2DTOs(List<NationBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}	

}
