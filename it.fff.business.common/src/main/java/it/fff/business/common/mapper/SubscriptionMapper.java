package it.fff.business.common.mapper;

import java.util.List;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.clientserver.common.dto.SubscriptionDTO;

public class SubscriptionMapper implements Mapper<SubscriptionDTO,SubscriptionBO, SubscriptionEO>{

	private static SubscriptionMapper mapper;
	
	private SubscriptionMapper(){
		
	}
	
	public static SubscriptionMapper getInstance(){
		if(mapper==null){
			mapper= new  SubscriptionMapper();
		}
		return mapper;
	}
	
	
	@Override
	public SubscriptionBO mapDTO2BO(SubscriptionDTO dto) {
		SubscriptionBO bo = new SubscriptionBO();
		if(dto.getId()!=null && !"".equals(dto.getId())){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setDurata(Integer.valueOf(dto.getDurata()));
		return bo;
	}

	@Override
	public SubscriptionEO mergeBO2EO(SubscriptionBO bo, SubscriptionEO eo) {
		if(bo!=null){
			if(eo==null){
				eo = new SubscriptionEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
//		eo.setDataInizio(bo.getDataInizio()); TODO
			
		}
		return eo;
	}

	@Override
	public List<SubscriptionBO> mapDTOs2BOs(List<SubscriptionDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscriptionEO> mergeBOs2EOs(List<SubscriptionBO> bos, List<SubscriptionEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscriptionBO> mapEOs2BOs(List<SubscriptionEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionBO mapEO2BO(SubscriptionEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscriptionDTO> mapBOs2DTOs(List<SubscriptionBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionDTO mapBO2DTO(SubscriptionBO bo) {
		// TODO Auto-generated method stub
		return null;
	}

}
