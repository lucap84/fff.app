package it.fff.business.common.mapper;

import it.fff.business.common.bo.CityBO;
import it.fff.clientserver.common.dto.CityDTO;

public class CityMapper implements Mapper{

	public static CityDTO mapBO2DTO(CityBO bo) {
		CityDTO dto = new CityDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setNazione(NazioneMapper.mapBO2DTO(bo.getNazione()));
		}
		return dto;
	}

}
