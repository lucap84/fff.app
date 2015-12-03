package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.business.common.eo.SessionEO;
import it.fff.clientserver.common.dto.SessionDTO;

public class SessionMapper implements Mapper<SessionDTO,SessionBO,SessionEO>{

	private static SessionMapper mapper;
	
	private SessionMapper(){
		
	}
	
	public static SessionMapper getInstance(){
		if(mapper==null){
			mapper= new  SessionMapper();
		}
		return mapper;
	}
	
	@Override
	public List<SessionEO> mergeBOs2EOs(List<SessionBO> bos, List<SessionEO> eos) {
		if(bos!=null){
			if(eos==null){
				eos = new ArrayList<SessionEO>();
			}
			for (SessionBO bo : bos) {
				SessionMapper sessionMapper = SessionMapper.getInstance();
				SessionEO eo = sessionMapper.mergeBO2EO(bo,null);
				eos.add(eo);
			}
		}
		return eos;
	}
	
	@Override
	public SessionEO mergeBO2EO(SessionBO bo, SessionEO eo) {
		if(bo!=null){
			if(eo==null){
				eo = new SessionEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setDeviceIdIfNotEmpty(bo.getDeviceId());
			eo.setSharedKeyIfNotEmpty(bo.getSharedKey());
			eo.setLogged(bo.isLogged());
			eo.setDataLoginIfNotEmpty(bo.getDataLogin());
			
			AccountMapper accountMapper = AccountMapper.getInstance();
			AccountEO accountEO = accountMapper.mergeBO2EO(bo.getAccount(), null);
			eo.setAccount(accountEO);
		}
		return eo;
	}
	
	@Override
	public SessionBO mapDTO2BO(SessionDTO dto) {
		SessionBO bo = null;
		if(dto!=null){
			bo = new SessionBO();
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setDataLogin(dto.getDataLogin());
			bo.setDataLogout(dto.getDataLogout());
			bo.setDeviceId(dto.getDeviceId());
			bo.setLogged(dto.isLogged());
			bo.setSharedKey(dto.getSharedKey());
			
			//Non uso accountMapper per evitare cicli
			AccountBO bo2 = new AccountBO();
			bo2.setEmail(dto.getAccount().getEmail());
			bo2.setPassword(dto.getAccount().getPassword());
			bo2.setSessions(new ArrayList<SessionBO>());
			bo2.getSessions().add(bo); //aggiungo la sessione creata all account;
			
			bo.setAccount(bo2);//Ogni session ha il riferimento all'account relativo
		}
		
		return bo;
	}

	@Override
	public List<SessionBO> mapDTOs2BOs(List<SessionDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionBO> mapEOs2BOs(List<SessionEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionBO mapEO2BO(SessionEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionDTO> mapBOs2DTOs(List<SessionBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionDTO mapBO2DTO(SessionBO bo) {
		// TODO Auto-generated method stub
		return null;
	}	
}
