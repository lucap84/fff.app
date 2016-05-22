package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.eo.ProfileImageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.clientserver.common.dto.ProfileImageDTO;

public class ProfileImageMapper implements Mapper<ProfileImageDTO,ProfileImageBO,ProfileImageEO>{

	private static ProfileImageMapper mapper;
	
	private ProfileImageMapper(){
		
	}
	
	public static ProfileImageMapper getInstance(){
		if(mapper==null){
			mapper= new  ProfileImageMapper();
		}
		return mapper;
	}
	
	@Override
	public List<ProfileImageBO> mapDTOs2BOs(List<ProfileImageDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileImageBO mapDTO2BO(ProfileImageDTO dto) {
		ProfileImageBO bo = null;
		if(dto!=null){
			bo = new ProfileImageBO();
			bo.setImageInputStream(dto.getImageInputStream());
			bo.setFileName(dto.getFileName());
			bo.setName(dto.getName());
			bo.setUserId(dto.getUserId());
			bo.setHash(dto.getHash());
			bo.setSize(dto.getSize());
			bo.setParameters(dto.getParameters());
			bo.setExtension(dto.getExtension());
			
//			if(dto.getFileName()!=null && !"".equals(dto.getFileName())){
//				String ext = "";
//				String[] split = dto.getFileName().split("\\.");
//				if(split.length>1){
//					ext = split[1];
//				}
//				bo.setExtension(ext);
//			}
			
		}
		return bo;
	}

	@Override
	public List<ProfileImageEO> mergeBOs2EOs(List<ProfileImageBO> bos, List<ProfileImageEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileImageEO mergeBO2EO(ProfileImageBO bo, ProfileImageEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (ProfileImageEO)session.load(ProfileImageEO.class, bo.getId());
			}
			if(eo==null){
				eo = new ProfileImageEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setExtensionIfNotEmpty(bo.getExtension());
			eo.setFilenameIfNotEmpty(bo.getFileName());
			eo.setHashIfNotEmpty(bo.getHash());
			eo.setPathIfNotEmpty(bo.getPath());
			eo.setProfileImage(bo.isProfileImage());
			eo.setSizeIfNotEmpty(bo.getSize());

			if(bo.getUserId()>0){
				UserEO userEO = (UserEO)session.load(UserEO.class, bo.getUserId());
				eo.setUser(userEO);
			}
		}
		return eo;
	}

	@Override
	public List<ProfileImageBO> mapEOs2BOs(List<ProfileImageEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileImageBO mapEO2BO(ProfileImageEO eo) {
		ProfileImageBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new ProfileImageBO();
			bo.setId(eo.getId()==null?0:eo.getId()); //per evitare nullpointer in fase di cast
			bo.setExtension(eo.getExtension());
			bo.setFileName(eo.getFilename());
			bo.setHash(eo.getHash());
			bo.setImageAsB64(null);
			bo.setImageInputStream(null);
			bo.setName(null);
			bo.setParameters(null);
			bo.setPath(eo.getPath());
			bo.setProfileImage(eo.isProfileImage());
			bo.setSize(eo.getSize()==null?0:eo.getSize()); //per evitare nullpointer in fase di cast
			bo.setUserId(eo.getUser().getId()==null?0:eo.getUser().getId()); //per evitare nullpointer in fase di cast
		}
		return bo;
	}

	@Override
	public List<ProfileImageDTO> mapBOs2DTOs(List<ProfileImageBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileImageDTO mapBO2DTO(ProfileImageBO bo) {
		ProfileImageDTO dto = null;
		if(bo!=null){
			dto = new ProfileImageDTO();
			dto.setExtension(bo.getExtension());
			dto.setFileName(bo.getFileName());
			dto.setHash(bo.getHash());
			dto.setId(bo.getId());
			dto.setImageAsB64(bo.getImageAsB64());
			dto.setImageInputStream(bo.getImageInputStream());
			dto.setName(bo.getName());
			dto.setParameters(bo.getParameters());
			dto.setPath(bo.getPath());
			dto.setProfileImage(bo.isProfileImage());
			dto.setSize(bo.getSize());
			dto.setUserId(bo.getUserId());
		}
		return dto;
	}

}
