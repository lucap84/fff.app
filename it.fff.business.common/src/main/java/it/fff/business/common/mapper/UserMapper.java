package it.fff.business.common.mapper;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.clientserver.common.dto.*;

public class UserMapper implements BeanMapper<UserDTO, UserBO, UserEO> {

	@Override
	public UserBO mapDto2Bo(UserDTO dto) {
		UserBO bo = new UserBO();
		if(dto.getId()!=null){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setCognome(dto.getCognome());
		bo.setDataNascita(dto.getDataNascita());
		bo.setDescrizione(dto.getDescrizione());
		bo.setEmail(dto.getEmail());
		return bo;
	}

	@Override
	public UserDTO mapBo2Dto(UserBO bo) {
		UserDTO dto = new UserDTO();
		dto.setId(String.valueOf(bo.getId()));
		dto.setCognome(bo.getCognome());
		dto.setDataNascita(bo.getDataNascita());
		dto.setDescrizione(bo.getDescrizione());
		dto.setEmail(bo.getEmail());
		return dto;
	}

	@Override
	public UserEO mapBo2Eo(UserBO bo) {
		UserEO eo = new UserEO();
		eo.setId(bo.getId());
		eo.setCognome(bo.getCognome());
		eo.setDataNascita(bo.getDataNascita());
		eo.setDescrizione(bo.getDescrizione());
		eo.setEmail(bo.getEmail());
		return eo;
	}

	@Override
	public UserBO mapEo2Bo(UserEO eo) {
		UserBO bo = new UserBO();
		bo.setId(eo.getId());
		bo.setCognome(eo.getCognome());
		bo.setDataNascita(eo.getDataNascita());
		bo.setDescrizione(eo.getDescrizione());
		bo.setEmail(eo.getEmail());
		return bo;
	}

	public ProfileImageBO mapProfileImageDto2Bo(ProfileImageDTO dto) {
		ProfileImageBO bo = new ProfileImageBO();
		bo.setImageInputStream(dto.getImageInputStream());
		bo.setFileName(dto.getFileName());
		bo.setUserId(dto.getUserId());
		bo.setImgHashCode(dto.getImgHashCode());
		return bo;
	}

	public ProfileImageDTO mapProfileImageBo2Dto(ProfileImageBO bo) {
		ProfileImageDTO dto = new ProfileImageDTO();
		dto.setImageInputStream(bo.getImageInputStream());
		dto.setFileName(bo.getFileName());
		dto.setUserId(bo.getUserId());
		dto.setImgHashCode(bo.getImgHashCode());
		return dto;
	}

	public ProfileImageEO mapProfileImageBo2Eo(ProfileImageBO bo) {
		ProfileImageEO eo = new ProfileImageEO();
		eo.setImageInputStream(bo.getImageInputStream());
		eo.setFileName(bo.getFileName());
		eo.setUserId(bo.getUserId());
		eo.setImageIdentifier(bo.getImgHashCode());
		return eo;
	}

	public ProfileImageBO mapProfileImageEo2Bo(ProfileImageEO eo) {
		ProfileImageBO bo = new ProfileImageBO();
		bo.setImageInputStream(eo.getImageInputStream());
		bo.setFileName(eo.getFileName());
		bo.setUserId(eo.getUserId());
		bo.setImgHashCode(eo.getImageIdentifier());
		return bo;
	}	
}
