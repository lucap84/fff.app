package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementBO;
import it.fff.business.common.bo.NazioneBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AchievementEO;
import it.fff.business.common.eo.NazioneEO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.clientserver.common.dto.*;

public class UserMapper implements Mapper {

	public static UserBO map2BO(UserDTO dto) {
		UserBO bo = new UserBO();
		if(dto.getId()!=null && !"".equals(dto.getId())){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setCognome(dto.getCognome());
		bo.setNome(dto.getNome());
		bo.setSesso(dto.getSesso());
		bo.setDataNascita(dto.getDataNascita());
		bo.setDescrizione(dto.getDescrizione());
		return bo;
	}

	public static UserDTO map2DTO(UserBO bo) {
		UserDTO dto = new UserDTO();
		dto.setId(String.valueOf(bo.getId()));
		dto.setCognome(bo.getCognome());
		dto.setDataNascita(bo.getDataNascita());
		dto.setDescrizione(bo.getDescrizione());
		return dto;
	}

	public static UserEO map2EO(UserBO bo) {
		UserEO eo = new UserEO();
		eo.setId(bo.getId());
		eo.setNome(bo.getNome());
		eo.setCognome(bo.getCognome());
		eo.setSesso(bo.getSesso());
		eo.setDataNascita(bo.getDataNascita());
		eo.setDescrizione(bo.getDescrizione());
		return eo;
	}

	public static UserBO map2BO(UserEO eo) {
		UserBO bo = new UserBO();
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
		return bo;
	}

	public static List<AchievementBO> map2BO(List<AchievementEO> eos) {
		List<AchievementBO> bos = new ArrayList<AchievementBO>();
		for (AchievementEO eo : eos) {
			bos.add(UserMapper.map2BO(eo));
		}
		return bos;
	}

	public static AchievementBO map2BO(AchievementEO eo) {
		AchievementBO bo = new AchievementBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getDescrizione());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}




	public static NazioneBO map2BO(NazioneEO eo) {
		NazioneBO bo = new NazioneBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setInternationalKey(eo.getInternationalKey());
		return bo;
	}

	public static ProfileImageBO map2BO(ProfileImageDTO dto) {
		ProfileImageBO bo = new ProfileImageBO();
		bo.setImageInputStream(dto.getImageInputStream());
		bo.setFileName(dto.getFileName());
		bo.setUserId(dto.getUserId());
		bo.setImgHashCode(dto.getImgHashCode());
		return bo;
	}

	public static ProfileImageDTO map2DTO(ProfileImageBO bo) {
		ProfileImageDTO dto = new ProfileImageDTO();
		dto.setImageInputStream(bo.getImageInputStream());
		dto.setFileName(bo.getFileName());
		dto.setUserId(bo.getUserId());
		dto.setImgHashCode(bo.getImgHashCode());
		return dto;
	}

	public ProfileImageEO map2EO(ProfileImageBO bo) {
		ProfileImageEO eo = new ProfileImageEO();
		eo.setImageInputStream(bo.getImageInputStream());
		eo.setFileName(bo.getFileName());
		eo.setUserId(bo.getUserId());
		eo.setImageIdentifier(bo.getImgHashCode());
		return eo;
	}

	public ProfileImageBO map2BO(ProfileImageEO eo) {
		ProfileImageBO bo = new ProfileImageBO();
		bo.setImageInputStream(eo.getImageInputStream());
		bo.setFileName(eo.getFileName());
		bo.setUserId(eo.getUserId());
		bo.setImgHashCode(eo.getImageIdentifier());
		return bo;
	}

	public static UserBO map2BO(RegistrationDataDTO dto) {
		UserBO bo = new UserBO();
		bo.setNome(dto.getNome());
		bo.setCognome(dto.getCognome());
		bo.setDataNascita(dto.getDataNascita());
		bo.setSesso(dto.getSesso());
		return bo;
	}	
}
