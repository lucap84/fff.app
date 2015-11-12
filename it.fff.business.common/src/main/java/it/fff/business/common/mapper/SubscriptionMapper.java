package it.fff.business.common.mapper;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.clientserver.common.dto.SubscriptionDTO;

public class SubscriptionMapper {

	public SubscriptionBO map(SubscriptionDTO dto) {
		SubscriptionBO bo = new SubscriptionBO();
		if(dto.getId()!=null && !"".equals(dto.getId())){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setDurata(Integer.valueOf(dto.getDurata()));
		return bo;
	}

}
