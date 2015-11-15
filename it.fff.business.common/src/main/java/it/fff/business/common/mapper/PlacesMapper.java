package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.clientserver.common.dto.PlaceDTO;

public class PlacesMapper implements Mapper {

	public static List<PlaceDTO> map2DTO(List<PlaceBO> placesBO) {
		List<PlaceDTO> dtos = new ArrayList<PlaceDTO>();
		for (PlaceBO bo : placesBO) {
			dtos.add(PlacesMapper.map2DTO(bo));
		}
		return dtos;
	}

	public static PlaceDTO map2DTO(PlaceBO bo) {
		PlaceDTO dto = new PlaceDTO();
		dto.setGpsLat(String.valueOf(bo.getGpsLat()));
		dto.setGpsLong(String.valueOf(bo.getGpsLong()));
		dto.setNome(bo.getNome());
		return dto;
	}

	public static PlaceBO map2BO(PlaceDTO dto) {
		PlaceBO bo = new PlaceBO();
		bo.setGpsLat(Integer.valueOf(dto.getGpsLat()));
		bo.setGpsLong(Integer.valueOf(dto.getGpsLong()));
		bo.setNome(dto.getNome());
		return bo;
	}

	public static PlaceEO map2EO(PlaceBO bo) {
		PlaceEO eo = new PlaceEO();
		eo.setGpsLat(bo.getGpsLat());
		eo.setGpsLong(bo.getGpsLong());
		eo.setNome(bo.getNome());
		return eo;
	}

	public static List<PlaceBO> map2BO(List<PlaceEO> eos) {
		List<PlaceBO> bos = new ArrayList<PlaceBO>();
		for (PlaceEO eo : eos) {
			bos.add(PlacesMapper.map2DTO(eo));
		}
		return bos;
	}

	public static PlaceBO map2DTO(PlaceEO eo) {
		PlaceBO bo = new PlaceBO();
		bo.setGpsLat(eo.getGpsLat());
		bo.setGpsLong(eo.getGpsLong());
		bo.setNome(eo.getNome());
		return bo;
	}

}
