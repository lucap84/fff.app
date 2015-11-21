package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.MapperListener;
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
			bo.setCognome(dto.getCognome());
			bo.setNome(dto.getNome());
			bo.setSesso(dto.getSesso());
			bo.setDataNascita(dto.getDataNascita());
			bo.setDescrizione(dto.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static UserDTO map2DTO(UserBO bo) {
		UserDTO dto = new UserDTO();
		if(dto!=null){
			dto.setId(String.valueOf(bo.getId()));
			dto.setCognome(bo.getCognome());
			dto.setDataNascita(bo.getDataNascita());
			dto.setDescrizione(bo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return dto;
	}

	public static UserEO map2EO(UserBO bo) {
		UserEO eo = new UserEO();
		if(bo!=null){
			if(bo.getId()>0){
				eo.setId(bo.getId());
			}
			eo.setNome(bo.getNome());
			eo.setCognome(bo.getCognome());
			eo.setSesso(bo.getSesso());
			eo.setDataNascita(bo.getDataNascita());
			eo.setDescrizione(bo.getDescrizione());
			eo.setLastPositionLat(bo.getLastPositionLat());
			eo.setLastPositionLong(bo.getLastPositionLong());
			eo.setLastPositionDate(bo.getLastPositionDate());
			eo.setLingue(LinguaMapper.map2EO(bo.getLingue()));
			eo.setAccount(UserMapper.map2EO(bo.getAccount()));
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
	}

	public static AccountEO map2EO(AccountBO bo) {
		AccountEO eo = new AccountEO();
		if(bo!=null){
			eo.setEmail(bo.getEmail());
			eo.setFlgValidita(bo.isFlgValidita());
			eo.setFlgVerificato(bo.isFlgVerificato());
			if(bo.getId()>0){
				eo.setId(bo.getId());
			}
			eo.setPassword(bo.getPassword());
			eo.setVerificationCode(bo.getVerificationCode());
			
			//NON riutilizzo metodi di mapping per NON CREARE LOOP RICORSIVI!
			List<SessionEO> sessionsEO = new ArrayList<SessionEO>();
			
			List<SessionBO> sessionsBO = bo.getSessions();
			for (SessionBO ssBO : sessionsBO) {
				SessionEO ssEO = new SessionEO();
				if(ssBO.getId()>0){
					ssEO.setId(ssBO.getId());
				}
				ssEO.setAccount(eo);
				ssEO.setDeviceId(ssBO.getDeviceId());
				ssEO.setSharedKey(ssBO.getSharedKey());
				ssEO.setValidSession(ssBO.isValidSession());
				sessionsEO.add(ssEO);
			}
			eo.setSessions(sessionsEO);
//			eo.setSessions(UserMapper.map2EO(sessions));
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
	}

	public static List<SessionEO> map2EO(List<SessionBO> bos) {
		List<SessionEO> eos = new ArrayList<SessionEO>();
		if(bos!=null){
			for (SessionBO bo : bos) {
				eos.add(UserMapper.map2EO(bo));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return eos;
	}

	public static SessionEO map2EO(SessionBO bo) {
		SessionEO eo = new SessionEO();
		if(bo!=null){
			if(bo.getId()>0){
				eo.setId(bo.getId());
			}
			eo.setDeviceId(bo.getDeviceId());
			eo.setSharedKey(bo.getSharedKey());
			eo.setValidSession(bo.isValidSession());
			eo.setAccount(UserMapper.map2EO(bo.getAccount()));
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
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
				bo.setLingue(LinguaMapper.map2BO(eo.getLingue()));
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

	public ProfileImageEO map2EO(ProfileImageBO bo) {
		ProfileImageEO eo = new ProfileImageEO();
		if(bo!=null){
			eo.setImageInputStream(bo.getImageInputStream());
			eo.setFileName(bo.getFileName());
			eo.setUserId(bo.getUserId());
			eo.setImageIdentifier(bo.getImgHashCode());
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
	}

	public ProfileImageBO map2BO(ProfileImageEO eo) {
		ProfileImageBO bo = new ProfileImageBO();
		if(eo!=null){
			bo.setImageInputStream(eo.getImageInputStream());
			bo.setFileName(eo.getFileName());
			bo.setUserId(eo.getUserId());
			bo.setImgHashCode(eo.getImageIdentifier());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
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
}
