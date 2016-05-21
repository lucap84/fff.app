package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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
	public List<SessionEO> mergeBOs2EOs(List<SessionBO> bos, List<SessionEO> eos, Session session) {
		if(bos!=null){
			if(eos==null){
				eos = new ArrayList<SessionEO>();
			}
			for (SessionBO bo : bos) {
				SessionMapper sessionMapper = SessionMapper.getInstance();
				SessionEO eo = sessionMapper.mergeBO2EO(bo,null, session);
				eos.add(eo);
			}
		}
		return eos;
	}
	
	@Override
	public SessionEO mergeBO2EO(SessionBO bo, SessionEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (SessionEO)session.load(SessionEO.class, bo.getId());
			}
			if(eo==null){
				eo = new SessionEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setDeviceIdIfNotEmpty(bo.getDeviceId());
			eo.setSharedKeyIfNotEmpty(bo.getSharedKey());
			eo.setExpiresKeyIfNotEmpty(bo.getExpiresKey());
			eo.setLogged(bo.isLogged());
			eo.setDataLoginIfNotEmpty(bo.getDataLogin());
			
			AccountMapper accountMapper = AccountMapper.getInstance();
			AccountEO accountEO = accountMapper.mergeBO2EO(bo.getAccount(), eo.getAccount(), session);
			eo.setAccount(accountEO);
		}
		return eo;
	}
	
	@Override
	public SessionBO mapDTO2BO(SessionDTO dto) {
		SessionBO bo = null;
		if(dto!=null){
			bo = new SessionBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setDataLogin(dto.getDataLogin());
			bo.setDataLogout(dto.getDataLogout());
			bo.setDeviceId(dto.getDeviceId());
			bo.setLogged(dto.isLogged());
			bo.setSharedKey(dto.getSharedKey());
			bo.setExpiresKey(dto.getExpiresKey());
			
			if(dto.getAccountId()>0){
				AccountBO bo2 = new AccountBO();
				bo2.setId(Integer.valueOf(dto.getAccountId()));
				bo.setAccount(bo2);
			}
			
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
		List<SessionDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<SessionDTO>();
			for (SessionBO bo : bos) {
				SessionMapper sessionMapper = SessionMapper.getInstance();
				SessionDTO dto = sessionMapper.mapBO2DTO(bo);
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public SessionDTO mapBO2DTO(SessionBO bo) {
		SessionDTO dto = null;
		if(bo!=null){
			dto = new SessionDTO();
			dto.setAccountId(bo.getId());
			dto.setId(bo.getId());
			dto.setDeviceId(bo.getDeviceId());
			dto.setSharedKey(bo.getSharedKey());
			dto.setExpiresKey(bo.getExpiresKey());
			dto.setLogged(bo.isLogged());
			dto.setDataLogin(bo.getDataLogin());
			dto.setDataLogout(bo.getDataLogout());
		}
		return dto;
	}	
}
