package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.SessionEO;
import it.fff.clientserver.common.dto.AccountDTO;

public class AccountMapper implements Mapper<AccountDTO,AccountBO,AccountEO>{
	
	private static AccountMapper mapper;
	
	private AccountMapper(){
		
	}
	
	public static AccountMapper getInstance(){
		if(mapper==null){
			mapper= new  AccountMapper();
		}
		return mapper;
	}

	@Override
	public List<AccountBO> mapDTOs2BOs(List<AccountDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBO mapDTO2BO(AccountDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountEO> mergeBOs2EOs(List<AccountBO> bos, List<AccountEO> eso, Session session) {
		return null;
	}

	@Override
	public AccountEO mergeBO2EO(AccountBO bo, AccountEO eo, Session session) {
		if(bo!=null){
			if(eo==null){
				eo = new AccountEO();
			}
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
		return eo;
	}

	@Override
	public List<AccountBO> mapEOs2BOs(List<AccountEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBO mapEO2BO(AccountEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDTO> mapBOs2DTOs(List<AccountBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDTO mapBO2DTO(AccountBO bo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
