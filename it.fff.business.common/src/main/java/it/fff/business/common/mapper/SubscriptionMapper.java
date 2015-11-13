package it.fff.business.common.mapper;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.clientserver.common.dto.SubscriptionDTO;

public class SubscriptionMapper implements Mapper{

	public static SubscriptionBO map2BO(SubscriptionDTO dto) {
		SubscriptionBO bo = new SubscriptionBO();
		if(dto.getId()!=null && !"".equals(dto.getId())){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setDurata(Integer.valueOf(dto.getDurata()));
		return bo;
	}

}
