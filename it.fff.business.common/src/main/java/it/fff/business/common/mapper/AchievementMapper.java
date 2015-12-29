package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AchievementBO;
import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.eo.AchievementObtainedEO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.clientserver.common.dto.AchievementDTO;

import static org.hibernate.Hibernate.isInitialized;

public class AchievementMapper implements Mapper<AchievementDTO,AchievementBO,AchievementObtainedEO>{

	private static AchievementMapper mapper;
	
	private AchievementMapper(){
		
	}
	
	public static AchievementMapper getInstance(){
		if(mapper==null){
			mapper= new  AchievementMapper();
		}
		return mapper;
	}
	
	@Override
	public List<AchievementBO> mapDTOs2BOs(List<AchievementDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AchievementBO mapDTO2BO(AchievementDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public  List<AchievementObtainedEO> mergeBOs2EOs(List<AchievementBO> bos, List<AchievementObtainedEO> eos, Session session) {
		return null;
	}


	@Override
	public AchievementObtainedEO mergeBO2EO(AchievementBO bo, AchievementObtainedEO eo, Session session) {
		return null;
		
	}


	@Override
	public List<AchievementBO> mapEOs2BOs(List<AchievementObtainedEO> eos) {
		List<AchievementBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<AchievementBO>();
			AchievementMapper achievementMapper = AchievementMapper.getInstance();
			for (AchievementObtainedEO eo : eos) {
				bos.add(achievementMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}


	@Override
	public AchievementBO mapEO2BO(AchievementObtainedEO eo) {
		AchievementBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new AchievementBO();
			bo.setId(eo.getId());
			bo.setDataOttenimento(eo.getDataCreazione());
			
			AchievementTypeMapper achievementTypeMapper = AchievementTypeMapper.getInstance();
			bo.setType(achievementTypeMapper.mapEO2BO(eo.getType()));
		}
		return bo;
	}


	@Override
	public List<AchievementDTO> mapBOs2DTOs(List<AchievementBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AchievementDTO mapBO2DTO(AchievementBO bo) {
		// TODO Auto-generated method stub
		return null;
	}

}
