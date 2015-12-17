package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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
		List<SubscriptionTypeDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<SubscriptionTypeDTO>();
			SubscriptionTypeMapper subscriptionTypeMapper = SubscriptionTypeMapper.getInstance();
			for (SubscriptionTypeBO bo : bos) {
				dtos.add(subscriptionTypeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public SubscriptionTypeDTO mapBO2DTO(SubscriptionTypeBO bo) {
		SubscriptionTypeDTO dto = null;
		if(bo!=null){
			dto = new SubscriptionTypeDTO();
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDurataGiorni(String.valueOf(bo.getDurataGiorni()));
			dto.setDurataMesi(String.valueOf(bo.getDurataMesi()));
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

	@Override
	public List<SubscriptionTypeBO> mapEOs2BOs(List<SubscriptionTypeEO> eos) {
		List<SubscriptionTypeBO> bos = null;
		if(eos!=null){
			bos = new ArrayList<SubscriptionTypeBO>();
			SubscriptionTypeMapper subscriptionTypeMapper = SubscriptionTypeMapper.getInstance();
			for (SubscriptionTypeEO eo : eos) {
				bos.add(subscriptionTypeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public SubscriptionTypeBO mapEO2BO(SubscriptionTypeEO eo) {
		SubscriptionTypeBO bo = null;
		if(eo!=null){
			bo = new SubscriptionTypeBO();
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setDurataGiorni(Integer.valueOf(eo.getDurataGiorni()));
			bo.setDurataMesi(Integer.valueOf(eo.getDurataMesi()));
			bo.setDescrizione(eo.getDescrizione());
		}
		return bo;
	}

	@Override
	public List<SubscriptionTypeBO> mapDTOs2BOs(List<SubscriptionTypeDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionTypeBO mapDTO2BO(SubscriptionTypeDTO dto) {
		SubscriptionTypeBO bo = null;
		if(dto!=null){
			bo = new SubscriptionTypeBO();
			bo.setNome(dto.getNome());
			bo.setId(Integer.valueOf(dto.getId()));
			bo.setDescrizione(dto.getDescrizione());
			bo.setDurataGiorni(Integer.valueOf(dto.getDurataGiorni()));
			bo.setDurataMesi(Integer.valueOf(dto.getDurataMesi()));
		}
		
		return bo;
	}

	@Override
	public List<SubscriptionTypeEO> mergeBOs2EOs(List<SubscriptionTypeBO> bos, List<SubscriptionTypeEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionTypeEO mergeBO2EO(SubscriptionTypeBO bo, SubscriptionTypeEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (SubscriptionTypeEO)session.load(SubscriptionTypeEO.class, bo.getId());
			}
		}
		return eo;
	}

}
