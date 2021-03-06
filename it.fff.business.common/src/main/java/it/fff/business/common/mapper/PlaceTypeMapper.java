package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.eo.PlaceTypeEO;
import it.fff.clientserver.common.enums.PlaceTypeEnum;

public class PlaceTypeMapper implements Mapper<PlaceTypeEnum,PlaceTypeEnum,PlaceTypeEO>{

	private static PlaceTypeMapper mapper;
	private static final Logger logger = LogManager.getLogger(PlaceTypeMapper.class);
	
	private PlaceTypeMapper(){
	}
	
	public static PlaceTypeMapper getInstance(){
		if(mapper==null){
			mapper= new  PlaceTypeMapper();
		}
		return mapper;
	}

	@Override
	public List<PlaceTypeEnum> mapEOs2BOs(List<PlaceTypeEO> eos) {
		List<PlaceTypeEnum> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<PlaceTypeEnum>();
			PlaceTypeMapper mapper = PlaceTypeMapper.getInstance();
			for (PlaceTypeEO eo : eos) {
				bos.add(mapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public PlaceTypeEnum mapEO2BO(PlaceTypeEO eo) {
		PlaceTypeEnum bo = PlaceTypeEnum.UNKNOW;
		if(eo!=null && isInitialized(eo)){
			try{
				bo = PlaceTypeEnum.valueOf(eo.getNome().toUpperCase());
				bo.setId(eo.getId());
			}
			catch(IllegalArgumentException e){
				logger.error("State not Recognized! :"+eo.getNome());
			}
		}
		else{
			bo = PlaceTypeEnum.UNKNOW;
		}		
		return bo;
	}

	@Override
	public PlaceTypeEnum mapBO2DTO(PlaceTypeEnum bo) {
		if(bo==null){
			return PlaceTypeEnum.UNKNOW;
		}
		return bo;
	}

	@Override
	public List<PlaceTypeEnum> mapDTOs2BOs(List<PlaceTypeEnum> dtos) {
		List<PlaceTypeEnum> bos = null;
		if(dtos!=null){
			 bos = new  ArrayList<PlaceTypeEnum>();
			 PlaceTypeMapper mapper = PlaceTypeMapper.getInstance();
			for (PlaceTypeEnum dto : dtos) {
				bos.add(mapper.mapDTO2BO(dto));
			}
		}
		return bos; 
	}

	@Override
	public PlaceTypeEnum mapDTO2BO(PlaceTypeEnum dto) {
		if(dto==null){
			return PlaceTypeEnum.UNKNOW;
		}
		return dto;
	}

	@Override
	public List<PlaceTypeEO> mergeBOs2EOs(List<PlaceTypeEnum> bos, List<PlaceTypeEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceTypeEO mergeBO2EO(PlaceTypeEnum bo, PlaceTypeEO eo, Session session) {
		if(bo!=null && bo!=PlaceTypeEnum.UNKNOW){
			//L'entita' non va mai creata/modificata quindi avro' sempre id valorizzato se ho il BO (non uso setter sul EO)
			eo = (PlaceTypeEO)session.load(PlaceTypeEO.class, bo.getId());
		}
		return eo;
	}

	@Override
	public List<PlaceTypeEnum> mapBOs2DTOs(List<PlaceTypeEnum> bos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
