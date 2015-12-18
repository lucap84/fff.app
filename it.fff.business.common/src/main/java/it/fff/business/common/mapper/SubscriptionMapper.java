package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.business.common.eo.UserEO;
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
		SubscriptionBO bo = null;
		if(dto!=null){
			 bo = new SubscriptionBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setDataInizio(dto.getDataInizio());
			bo.setDataFine(dto.getDataFine());
			bo.setSconto(Double.valueOf(dto.getSconto()));
			bo.setUserIdAbbonato(Integer.valueOf(dto.getUserIdAbbonato()));
			
			SubscriptionTypeMapper subscriptionTypeMapper = SubscriptionTypeMapper.getInstance();
			bo.setTipo(subscriptionTypeMapper.mapDTO2BO(dto.getTipo()));
		}
		return bo;
	}

	@Override
	public SubscriptionEO mergeBO2EO(SubscriptionBO bo, SubscriptionEO eo, Session session) {
		if(bo!=null){
			if(eo==null){
				eo = new SubscriptionEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setDataInizio(bo.getDataInizio());
			eo.setDataFine(bo.getDataFine());
			eo.setSconto(bo.getSconto());
			
			if(bo.getUserIdAbbonato()>0){
				UserEO abbonatoEO = (UserEO)session.load(UserEO.class, bo.getUserIdAbbonato());
				eo.setAbbonato(abbonatoEO);
			}
			
			SubscriptionTypeMapper mapper = SubscriptionTypeMapper.getInstance();
			eo.setTipo(mapper.mergeBO2EO(bo.getTipo(), eo.getTipo(), session));
			
		}
		return eo;
	}

	@Override
	public List<SubscriptionBO> mapDTOs2BOs(List<SubscriptionDTO> dtos) {
		List<SubscriptionBO> bos = null;
		if(dtos!=null){
			bos = new ArrayList<SubscriptionBO>();
			SubscriptionMapper mapper = SubscriptionMapper.getInstance();
			for (SubscriptionDTO dto : dtos) {
				bos.add(mapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	@Override
	public List<SubscriptionEO> mergeBOs2EOs(List<SubscriptionBO> bos, List<SubscriptionEO> eos, Session session) {
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
