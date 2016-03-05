package it.fff.business.common.mapper;

import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.PriceBO;
import it.fff.business.common.eo.PriceEO;
import it.fff.clientserver.common.dto.PriceDTO;

public class PriceMapper implements Mapper<PriceDTO,PriceBO,PriceEO>{

	private static PriceMapper mapper;
	
	private PriceMapper(){
	}
	
	public static PriceMapper getInstance(){
		if(mapper==null){
			mapper= new  PriceMapper();
		}
		return mapper;
	}
	
	@Override
	public List<PriceBO> mapDTOs2BOs(List<PriceDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceBO mapDTO2BO(PriceDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PriceEO> mergeBOs2EOs(List<PriceBO> bos, List<PriceEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceEO mergeBO2EO(PriceBO bo, PriceEO eo, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PriceBO> mapEOs2BOs(List<PriceEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceBO mapEO2BO(PriceEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PriceDTO> mapBOs2DTOs(List<PriceBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceDTO mapBO2DTO(PriceBO bo) {
		// TODO Auto-generated method stub
		return null;
	}

}
