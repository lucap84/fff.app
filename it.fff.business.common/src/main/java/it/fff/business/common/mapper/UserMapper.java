package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.*;
import it.fff.business.common.eo.*;
import it.fff.clientserver.common.dto.*;

public class UserMapper implements Mapper {
	
	private static final Logger logger = LogManager.getLogger(UserMapper.class);

	public static UserBO map2BO(UserDTO dto) {
		UserBO bo = new UserBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			AccountBO account = new AccountBO();
			account.setEmail(dto.getEmail());
			bo.setAccount(account);
			
			bo.setNome(dto.getNome());
			bo.setCognome(dto.getCognome());
			bo.setSesso(dto.getSesso());
			bo.setDataNascita(dto.getDataNascita());
			bo.setDescrizione(dto.getDescrizione());
			bo.setNazionalita(UserMapper.map2BO(dto.getNazionalita()));
			bo.setLingue(LinguaMapper.mapDTO2BO(dto.getLingue()));
			
			bo.setLastPositionDate(dto.getLastPositionDate());
			if(dto.getLastPositionLat()!=null){
//				double lastPositionLat = "".equals(dto.getLastPositionLat())?0:Double.valueOf(dto.getLastPositionLat());
				bo.setLastPositionLat(Double.valueOf(dto.getLastPositionLat()));
			}
			if(dto.getLastPositionLong()!=null){
//				double lastPositionLong = "".equals(dto.getLastPositionLong())?0:Double.valueOf(dto.getLastPositionLong());
				bo.setLastPositionLong(Double.valueOf(dto.getLastPositionLong()));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static NazioneBO map2BO(NazioneDTO dto) {
		NazioneBO bo = new NazioneBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setInternationalKey(dto.getInternationalKey());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static UserDTO map2DTO(UserBO bo) {
		UserDTO dto = new UserDTO();
		if(bo!=null){
			dto.setId(String.valueOf(bo.getId()));
			dto.setCognome(bo.getCognome());
			dto.setDataNascita(bo.getDataNascita());
			dto.setDescrizione(bo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return dto;
	}


	public static void mapBO2EO(NazioneBO bo, NazioneEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setInternationalKeyIfNotEmpty(bo.getInternationalKey());
		}
		else{logger.warn("Mapping null objects!!");}
	}

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
		else{logger.warn("Mapping null objects!!");}
	}

	public static List<SessionEO> map2EO(List<SessionBO> bos) {
		List<SessionEO> eos = new ArrayList<SessionEO>();
		if(bos!=null){
			for (SessionBO bo : bos) {
				SessionEO eo = new SessionEO();
				UserMapper.mapBO2EO(bo,eo);
				eos.add(eo);
			}
		}
		else{logger.warn("Mapping null objects!!");}
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
			UserMapper.mapBO2EO(bo.getAccount(), accountEO);
			eo.setAccount(accountEO);
		}
		else{logger.warn("Mapping null objects!!");}
	}

	public static UserBO map2BO(UserEO eo) {
		UserBO bo = new UserBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setCognome(eo.getCognome());
			bo.setDataNascita(eo.getDataNascita());
			bo.setDescrizione(eo.getDescrizione());
			bo.setFlagAttivo(eo.isFlagAttivo());
			bo.setNazionalita(UserMapper.map2BO(eo.getNazionalita()));
			if(eo.getLingue()!=null && org.hibernate.Hibernate.isInitialized(eo.getLingue())){
				bo.setLingue(LinguaMapper.mapEO2BO(eo.getLingue()));
			}
			if(eo.getAchievements()!=null && org.hibernate.Hibernate.isInitialized(eo.getAchievements())){
				bo.setAchievements(map2BO(eo.getAchievements()));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static List<AchievementBO> map2BO(List<AchievementEO> eos) {
		List<AchievementBO> bos = new ArrayList<AchievementBO>();
		if(eos!=null){
			for (AchievementEO eo : eos) {
				bos.add(UserMapper.map2BO(eo));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return bos;
	}

	public static AchievementBO map2BO(AchievementEO eo) {
		AchievementBO bo = new AchievementBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getDescrizione());
			bo.setDescrizione(eo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}




	public static NazioneBO map2BO(NazioneEO eo) {
		NazioneBO bo = new NazioneBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setInternationalKey(eo.getInternationalKey());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static ProfileImageBO map2BO(ProfileImageDTO dto) {
		ProfileImageBO bo = new ProfileImageBO();
		if(dto!=null){
			bo.setImageInputStream(dto.getImageInputStream());
			bo.setFileName(dto.getFileName());
			bo.setUserId(dto.getUserId());
			bo.setImgHashCode(dto.getImgHashCode());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static ProfileImageDTO map2DTO(ProfileImageBO bo) {
		ProfileImageDTO dto = new ProfileImageDTO();
		if(bo!=null){
			dto.setImageInputStream(bo.getImageInputStream());
			dto.setFileName(bo.getFileName());
			dto.setUserId(bo.getUserId());
			dto.setImgHashCode(bo.getImgHashCode());
		}
		else{logger.warn("Mapping null objects!!");}
		return dto;
	}


	public static UserBO map2BO(RegistrationDataRequestDTO dto) {
		UserBO bo1 = new UserBO();
		
		if(dto!=null){
			SessionBO bo3 = new SessionBO();
			bo3.setDeviceId(dto.getDeviceId());
			bo3.setSharedKey(dto.getSharedKey());
			
			
			AccountBO bo2 = new AccountBO();
			bo2.setEmail(dto.getEmail());
			bo2.setPassword(dto.getEncodedPassword());
			bo2.setSessions(new ArrayList<SessionBO>());
			bo3.setAccount(bo2);//Ogni session ha il riferimento all'account relativo
			bo2.getSessions().add(bo3);
			
			bo1.setNome(dto.getNome());
			bo1.setCognome(dto.getCognome());
			bo1.setDataNascita(dto.getDataNascita());
			bo1.setSesso(dto.getSesso());
			bo1.setAccount(bo2);
		}
		else{logger.warn("Mapping null objects!!");}
		return bo1;
	}

	public static SessionBO map2BO(LoginDataRequestDTO dto) {
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

	public static void mapBO2EO(UserBO bo, UserEO eo) {
		if(bo!=null){
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setCognomeIfNotEmpty(bo.getCognome());
			eo.setSessoIfNotEmpty(bo.getSesso());
			eo.setDataNascitaIfNotEmpty(bo.getDataNascita());
			eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
			eo.setLastPositionLatIfNotEmpty(bo.getLastPositionLat());
			eo.setLastPositionLongIfNotEmpty(bo.getLastPositionLong());
			eo.setLastPositionDateIfNotEmpty(bo.getLastPositionDate());
			
			List<LinguaEO> lingueEO = new ArrayList<LinguaEO>();
			LinguaMapper.mapBO2EO(bo.getLingue(),lingueEO);
			eo.setLingue(lingueEO);
			
			AccountEO accountEO = new AccountEO();
			UserMapper.mapBO2EO(bo.getAccount(), accountEO);
			eo.setAccount(accountEO);
			
			NazioneEO nazionalitaEO = new NazioneEO();
			UserMapper.mapBO2EO(bo.getNazionalita(),nazionalitaEO);
			eo.setNazionalita(nazionalitaEO);
		}
		else{logger.warn("Mapping null objects!!");}
	}
	
}
