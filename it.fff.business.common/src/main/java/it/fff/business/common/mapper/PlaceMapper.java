package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.clientserver.common.dto.PlaceDTO;

public class PlaceMapper implements Mapper<PlaceDTO,PlaceBO,PlaceEO>{
	
	private static PlaceMapper mapper;
	
	private PlaceMapper(){
		
	}
	
	public static PlaceMapper getInstance(){
		if(mapper==null){
			mapper= new  PlaceMapper();
		}
		return mapper;
	}

	@Override
	public List<PlaceDTO> mapBOs2DTOs(List<PlaceBO> bos) {
		List<PlaceDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<PlaceDTO>();
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			for (PlaceBO bo : bos) {
				dtos.add(placeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public PlaceDTO mapBO2DTO(PlaceBO bo) {
		PlaceDTO dto = null;
		if(bo!=null){
			dto = new PlaceDTO();
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setGpsLat(String.valueOf(bo.getGpsLat()));
			dto.setGpsLong(String.valueOf(bo.getGpsLong()));
			dto.setCivico(bo.getCivico());
			dto.setVia(bo.getVia());
			dto.setCap(bo.getCap());
			
			CityMapper cityMapper = CityMapper.getInstance();
			dto.setCity(cityMapper.mapBO2DTO(bo.getCity()));
		}
		return dto;
	}

	@Override
	public PlaceBO mapDTO2BO(PlaceDTO dto) {
		PlaceBO bo = null;
		if(dto!=null){
			bo = new PlaceBO();
			bo.setGpsLat(Double.valueOf(dto.getGpsLat()));
			bo.setGpsLong(Double.valueOf(dto.getGpsLong()));
			bo.setNome(dto.getNome());
		}
		return bo;
	}

	@Override
	public PlaceEO mergeBO2EO(PlaceBO bo, PlaceEO eo) {
		if(bo!=null){
			if(eo==null){
				eo = new PlaceEO();
			}
			eo.setGpsLat(bo.getGpsLat());
			eo.setGpsLong(bo.getGpsLong());
			eo.setNome(bo.getNome());
		}
		return eo;
	}

	@Override
	public List<PlaceBO> mapEOs2BOs(List<PlaceEO> eos) {
		List<PlaceBO> bos = null;
		if(eos!=null){
			bos = new ArrayList<PlaceBO>();
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			for (PlaceEO eo : eos) {
				bos.add(placeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public PlaceBO mapEO2BO(PlaceEO eo) {
		PlaceBO bo = null;
		if(eo!=null){
			bo = new PlaceBO();
			bo.setGpsLat(eo.getGpsLat());
			bo.setGpsLong(eo.getGpsLong());
			bo.setNome(eo.getNome());
		}
		return bo;
	}

	@Override
	public List<PlaceBO> mapDTOs2BOs(List<PlaceDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceEO> mergeBOs2EOs(List<PlaceBO> bos, List<PlaceEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

}
