package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.SessionEO;
import it.fff.clientserver.common.dto.LoginDataRequestDTO;

public class SessionMapper implements Mapper{

	public static List<SessionEO> mapBOs2EOs(List<SessionBO> bos) {
		List<SessionEO> eos = new ArrayList<SessionEO>();
		if(bos!=null){
			for (SessionBO bo : bos) {
				SessionEO eo = new SessionEO();
				SessionMapper.mapBO2EO(bo,eo);
				eos.add(eo);
			}
		}
		return eos;
	}
	
	public static void mapBO2EO(SessionBO bo, SessionEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setDeviceIdIfNotEmpty(bo.getDeviceId());
			eo.setSharedKeyIfNotEmpty(bo.getSharedKey());
			eo.setLogged(bo.isLogged());
			eo.setDataLoginIfNotEmpty(bo.getDataLogin());
			
			AccountEO accountEO = new AccountEO();
			AccountMapper.mapBO2EO(bo.getAccount(), accountEO);
			eo.setAccount(accountEO);
		}
	}
	
	public static SessionBO mapDTO2BO(LoginDataRequestDTO dto) {
		AccountBO bo2 = new AccountBO();
		bo2.setEmail(dto.getEmail());
		bo2.setPassword(dto.getEncodedPassword());
		bo2.setSessions(new ArrayList<SessionBO>());
		
		SessionBO bo = new SessionBO();
		bo.setDeviceId(dto.getDeviceId());
		bo.setAccount(bo2);//Ogni session ha il riferimento all'account relativo
		bo2.getSessions().add(bo);
		
		return bo;
	}	
}
