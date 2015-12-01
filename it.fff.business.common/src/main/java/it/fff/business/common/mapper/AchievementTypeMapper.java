package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementBO;
import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.eo.AchievementObtainedEO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.clientserver.common.dto.AchievementDTO;
import it.fff.clientserver.common.dto.AchievementTypeDTO;
import it.fff.clientserver.common.dto.LanguageDTO;

public class AchievementTypeMapper  implements Mapper<AchievementTypeDTO,AchievementTypeBO,AchievementTypeEO>{

	private static AchievementTypeMapper mapper;
	
	private AchievementTypeMapper(){
		
	}
	
	public static AchievementTypeMapper getInstance(){
		if(mapper==null){
			mapper= new  AchievementTypeMapper();
		}
		return mapper;
	}
	

	@Override
	public List<AchievementTypeBO> mapDTOs2BOs(List<AchievementTypeDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchievementTypeBO mapDTO2BO(AchievementTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AchievementTypeEO> mergeBOs2EOs(List<AchievementTypeBO> bos, List<AchievementTypeEO> eso) {
		return null;
		
	}

	@Override
	public AchievementTypeEO mergeBO2EO(AchievementTypeBO bo, AchievementTypeEO eo) {
		return null;
	}

	@Override
	public List<AchievementTypeBO> mapEOs2BOs(List<AchievementTypeEO> eos) {
		List<AchievementTypeBO> bos = new ArrayList<AchievementTypeBO>();
		if(eos!=null){
			AchievementTypeMapper achievementTypeMapper = AchievementTypeMapper.getInstance();
			for (AchievementTypeEO eo : eos) {
				bos.add(achievementTypeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public AchievementTypeBO mapEO2BO(AchievementTypeEO eo) {
		AchievementTypeBO bo = new AchievementTypeBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}

	@Override
	public List<AchievementTypeDTO> mapBOs2DTOs(List<AchievementTypeBO> bos) {
		List<AchievementTypeDTO> dtos = new ArrayList<AchievementTypeDTO>();
		if(bos!=null){
			AchievementTypeMapper achievementTypeMapper = AchievementTypeMapper.getInstance();
			for (AchievementTypeBO bo : bos) {
				dtos.add(achievementTypeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public AchievementTypeDTO mapBO2DTO(AchievementTypeBO bo) {
		AchievementTypeDTO dto = new AchievementTypeDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

}
