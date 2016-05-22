package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.clientserver.common.dto.AchievementTypeDTO;

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
	public List<AchievementTypeEO> mergeBOs2EOs(List<AchievementTypeBO> bos, List<AchievementTypeEO> eos, Session session) {
		return null;
		
	}

	@Override
	public AchievementTypeEO mergeBO2EO(AchievementTypeBO bo, AchievementTypeEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				//L'entita' non va mai creata/modificata quindi avro' sempre id valorizzato se ho il BO
				//Quindi non ho setter sul EO
				eo = (AchievementTypeEO)session.load(AchievementTypeEO.class, bo.getId());
			}
		}
		return eo;
	}

	@Override
	public List<AchievementTypeBO> mapEOs2BOs(List<AchievementTypeEO> eos) {
		List<AchievementTypeBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<AchievementTypeBO>();
			AchievementTypeMapper achievementTypeMapper = AchievementTypeMapper.getInstance();
			for (AchievementTypeEO eo : eos) {
				bos.add(achievementTypeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public AchievementTypeBO mapEO2BO(AchievementTypeEO eo) {
		AchievementTypeBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new AchievementTypeBO();
			bo.setId(eo.getId()==null?0:eo.getId()); //per evitare nullpointer in fase di cast
			bo.setNome(eo.getNome());
			bo.setDescrizione(eo.getDescrizione());
		}
		return bo;
	}

	@Override
	public List<AchievementTypeDTO> mapBOs2DTOs(List<AchievementTypeBO> bos) {
		List<AchievementTypeDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<AchievementTypeDTO>();
			AchievementTypeMapper achievementTypeMapper = AchievementTypeMapper.getInstance();
			for (AchievementTypeBO bo : bos) {
				dtos.add(achievementTypeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public AchievementTypeDTO mapBO2DTO(AchievementTypeBO bo) {
		AchievementTypeDTO dto = null;
		if(bo!=null){
			dto = new AchievementTypeDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

}
