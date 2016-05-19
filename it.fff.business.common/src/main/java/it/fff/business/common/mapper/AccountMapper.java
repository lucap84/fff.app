package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.SessionEO;
import it.fff.clientserver.common.dto.AccountDTO;
import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.dto.SessionDTO;

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
			if(bo.getId()>0){
				eo = (AccountEO)session.load(AccountEO.class, bo.getId());
			}
			if(eo==null){
				eo = new AccountEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setEmailIfNotEmpty(bo.getEmail());
			eo.setFlgValidita(bo.isFlgValidita());
			eo.setFlgVerificato(bo.isFlgVerificato());
			eo.setPasswordIfNotEmpty(bo.getPassword());
			eo.setVerificationCodeIfNotEmpty(bo.getVerificationCode());
			//TODO social id
			//NON riutilizzo metodi di mapping per NON CREARE LOOP RICORSIVI!
			List<SessionEO> sessionsEO  = null;
			
			List<SessionBO> sessionsBO = bo.getSessions();
			if(sessionsBO!=null){
				sessionsEO = new ArrayList<SessionEO>();
				for (SessionBO ssBO : sessionsBO) {
					SessionEO ssEO = null;
					if(ssBO.getId()>0){
						ssEO = (SessionEO)session.load(SessionEO.class, ssBO.getId());
					}
					else{
						ssEO = new SessionEO();
					}
					ssEO.setAccount(eo); //set del parent
					ssEO.setIdIfNotEmpty(ssBO.getId());
					ssEO.setDeviceIdIfNotEmpty(ssBO.getDeviceId());
					ssEO.setSharedKeyIfNotEmpty(ssBO.getSharedKey());
					ssEO.setLogged(ssBO.isLogged());
					ssEO.setDataLoginIfNotEmpty(ssBO.getDataLogin());
					ssEO.setDataLogoutIfNotEmpty(ssBO.getDataLogout());
					//TODO aggiungi SocialToken + Expires su DB: se sono presenti, non serve la sharedKey e il deviceID
					sessionsEO.add(ssEO);
				}
				
				eo.setSessions(sessionsEO);
			}
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
		AccountBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new AccountBO();
			bo.setId(eo.getId());
			bo.setEmail(eo.getEmail());
			bo.setFlgValidita(eo.isFlgValidita());
			bo.setFlgVerificato(eo.isFlgVerificato());
			bo.setPassword(eo.getPassword());
			bo.setVerificationCode(eo.getVerificationCode());
			//TODO social id
			//NON riutilizzo metodi di mapping per NON CREARE LOOP RICORSIVI!
			List<SessionBO> sessionsBO = null;
			
			List<SessionEO> sessionsEO = eo.getSessions();
			if(sessionsEO!=null && isInitialized(sessionsEO)){
				sessionsBO = new ArrayList<SessionBO>();
				for (SessionEO ssEO : sessionsEO) {
					SessionBO ssBO = new SessionBO();
					ssBO.setAccount(bo); //set del parent
					ssBO.setId(ssEO.getId());
					ssBO.setDeviceId(ssEO.getDeviceId());
					ssBO.setSharedKey(ssEO.getSharedKey());
					ssBO.setLogged(ssEO.isLogged());
					ssBO.setDataLogin(ssEO.getDataLogin());
					ssBO.setDataLogout(ssEO.getDataLogout());
					
					sessionsBO.add(ssBO);
				}
			}
			bo.setSessions(sessionsBO);			
		}
		return bo;
	}

	@Override
	public List<AccountDTO> mapBOs2DTOs(List<AccountBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDTO mapBO2DTO(AccountBO bo) {
		AccountDTO dto = null;
		if(bo!=null){
			dto = new AccountDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setId(bo.getId());
			dto.setSocialId(bo.getSocialId());
			dto.setEmail(bo.getEmail());
			dto.setFlgValidita(bo.isFlgValidita());
			dto.setFlgVerificato(bo.isFlgVerificato());
			dto.setPassword(bo.getPassword());
			dto.setVerificationCode(bo.getVerificationCode());
			
			//Posso usare i mapper senza creare cicli perche i dto hanno solo l'id degli oggetti padre all'interno
			dto.setSessions(SessionMapper.getInstance().mapBOs2DTOs(bo.getSessions()));
			
		}
		return dto;
	}
	
	
}
