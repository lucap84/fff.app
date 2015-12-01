package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.clientserver.common.dto.SubscriptionTypeDTO;

public class SubscriptionTypeMapper implements Mapper<SubscriptionTypeDTO,SubscriptionTypeBO,SubscriptionTypeEO>{

	private static SubscriptionTypeMapper mapper;
	
	private SubscriptionTypeMapper(){
		
	}
	
	public static SubscriptionTypeMapper getInstance(){
		if(mapper==null){
			mapper= new  SubscriptionTypeMapper();
		}
		return mapper;
	}
	
	@Override
	public List<SubscriptionTypeDTO> mapBOs2DTOs(List<SubscriptionTypeBO> bos) {
		List<SubscriptionTypeDTO> dtos = new ArrayList<SubscriptionTypeDTO>();
		if(bos!=null){
			SubscriptionTypeMapper subscriptionTypeMapper = SubscriptionTypeMapper.getInstance();
			for (SubscriptionTypeBO bo : bos) {
				dtos.add(subscriptionTypeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public SubscriptionTypeDTO mapBO2DTO(SubscriptionTypeBO bo) {
		SubscriptionTypeDTO dto = new SubscriptionTypeDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

	@Override
	public List<SubscriptionTypeBO> mapEOs2BOs(List<SubscriptionTypeEO> eos) {
		List<SubscriptionTypeBO> bos = new ArrayList<SubscriptionTypeBO>();
		if(bos!=null){
			SubscriptionTypeMapper subscriptionTypeMapper = SubscriptionTypeMapper.getInstance();
			for (SubscriptionTypeEO eo : eos) {
				bos.add(subscriptionTypeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public SubscriptionTypeBO mapEO2BO(SubscriptionTypeEO eo) {
		SubscriptionTypeBO bo = new SubscriptionTypeBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}

	@Override
	public List<SubscriptionTypeBO> mapDTOs2BOs(List<SubscriptionTypeDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionTypeBO mapDTO2BO(SubscriptionTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscriptionTypeEO> mergeBOs2EOs(List<SubscriptionTypeBO> bos, List<SubscriptionTypeEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionTypeEO mergeBO2EO(SubscriptionTypeBO bo, SubscriptionTypeEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

}
