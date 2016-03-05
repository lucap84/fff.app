package it.fff.business.common.mapper;

import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.PlaceTypeBO;
import it.fff.business.common.eo.PlaceTypeEO;
import it.fff.clientserver.common.dto.PlaceDTO;
import it.fff.clientserver.common.dto.PlaceTypeDTO;

public class PlaceTypeMapper implements Mapper<PlaceTypeDTO,PlaceTypeBO,PlaceTypeEO>{

	private static PlaceTypeMapper mapper;
	
	private PlaceTypeMapper(){
	}
	
	public static PlaceTypeMapper getInstance(){
		if(mapper==null){
			mapper= new  PlaceTypeMapper();
		}
		return mapper;
	}
	
	@Override
	public List<PlaceTypeBO> mapDTOs2BOs(List<PlaceTypeDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceTypeBO mapDTO2BO(PlaceTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceTypeEO> mergeBOs2EOs(List<PlaceTypeBO> bos, List<PlaceTypeEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceTypeEO mergeBO2EO(PlaceTypeBO bo, PlaceTypeEO eo, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceTypeBO> mapEOs2BOs(List<PlaceTypeEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceTypeBO mapEO2BO(PlaceTypeEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceTypeDTO> mapBOs2DTOs(List<PlaceTypeBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceTypeDTO mapBO2DTO(PlaceTypeBO bo) {
		PlaceTypeDTO dto = null;
		if(bo!=null){
			dto = new PlaceTypeDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setNome(bo.getNome());
		}
		return dto;
	}

}
