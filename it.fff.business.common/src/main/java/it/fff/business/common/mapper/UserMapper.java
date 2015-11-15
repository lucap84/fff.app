package it.fff.business.common.mapper;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
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
		bo.setEmail(dto.getEmail());
		return bo;
	}

	public static UserDTO map2DTO(UserBO bo) {
		UserDTO dto = new UserDTO();
		dto.setId(String.valueOf(bo.getId()));
		dto.setCognome(bo.getCognome());
		dto.setDataNascita(bo.getDataNascita());
		dto.setDescrizione(bo.getDescrizione());
		dto.setEmail(bo.getEmail());
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
		bo.setCognome(eo.getCognome());
		bo.setDataNascita(eo.getDataNascita());
		bo.setDescrizione(eo.getDescrizione());
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
}
