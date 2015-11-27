package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.clientserver.common.dto.PlaceDTO;

public class PlaceMapper implements Mapper {

	public static List<PlaceDTO> map2DTO(List<PlaceBO> bos) {
		List<PlaceDTO> dtos = new ArrayList<PlaceDTO>();
		if(bos!=null){
			for (PlaceBO bo : bos) {
				dtos.add(PlaceMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	public static PlaceDTO mapBO2DTO(PlaceBO bo) {
		PlaceDTO dto = new PlaceDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setGpsLat(String.valueOf(bo.getGpsLat()));
			dto.setGpsLong(String.valueOf(bo.getGpsLong()));
			dto.setCivico(bo.getCivico());
			dto.setVia(bo.getVia());
			dto.setCap(bo.getCap());
			dto.setCity(CityMapper.mapBO2DTO(bo.getCity()));
		}
		return dto;
	}

	public static PlaceBO mapDTO2BO(PlaceDTO dto) {
		PlaceBO bo = new PlaceBO();
		if(dto!=null){
			bo.setGpsLat(Integer.valueOf(dto.getGpsLat()));
			bo.setGpsLong(Integer.valueOf(dto.getGpsLong()));
			bo.setNome(dto.getNome());
		}
		return bo;
	}

	public static PlaceEO map2EO(PlaceBO bo) {
		PlaceEO eo = new PlaceEO();
		if(bo!=null){
			eo.setGpsLat(bo.getGpsLat());
			eo.setGpsLong(bo.getGpsLong());
			eo.setNome(bo.getNome());
		}
		return eo;
	}

	public static List<PlaceBO> mapEOs2BOs(List<PlaceEO> eos) {
		List<PlaceBO> bos = new ArrayList<PlaceBO>();
		if(eos!=null){
			for (PlaceEO eo : eos) {
				bos.add(PlaceMapper.map2DTO(eo));
			}
		}
		return bos;
	}

	public static PlaceBO map2DTO(PlaceEO eo) {
		PlaceBO bo = new PlaceBO();
		if(eo!=null){
			bo.setGpsLat(eo.getGpsLat());
			bo.setGpsLong(eo.getGpsLong());
			bo.setNome(eo.getNome());
		}
		return bo;
	}

}
