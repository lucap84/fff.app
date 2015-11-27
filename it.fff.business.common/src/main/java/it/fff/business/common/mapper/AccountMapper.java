package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.SessionEO;

public class AccountMapper implements Mapper{

	public static void mapBO2EO(AccountBO bo, AccountEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setEmailIfNotEmpty(bo.getEmail());
			eo.setFlgValidita(bo.isFlgValidita());
			eo.setFlgVerificato(bo.isFlgVerificato());
			eo.setPasswordIfNotEmpty(bo.getPassword());
			eo.setVerificationCodeIfNotEmpty(bo.getVerificationCode());
			
			//NON riutilizzo metodi di mapping per NON CREARE LOOP RICORSIVI!
			List<SessionEO> sessionsEO = new ArrayList<SessionEO>();
			
			List<SessionBO> sessionsBO = bo.getSessions();
			if(sessionsBO==null){
				sessionsBO = new ArrayList<SessionBO>();
			}
			for (SessionBO ssBO : sessionsBO) {
				SessionEO ssEO = new SessionEO();
				ssEO.setAccount(eo); //set del parent
				ssEO.setIdIfNotEmpty(ssBO.getId());
				ssEO.setDeviceIdIfNotEmpty(ssBO.getDeviceId());
				ssEO.setSharedKeyIfNotEmpty(ssBO.getSharedKey());
				ssEO.setLogged(ssBO.isLogged());
				ssEO.setDataLoginIfNotEmpty(ssBO.getDataLogin());
				ssEO.setDataLogoutIfNotEmpty(ssBO.getDataLogout());
				sessionsEO.add(ssEO);
			}
			eo.setSessions(sessionsEO);
		}
	}
	
	
}
