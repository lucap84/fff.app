package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.eo.CityEO;
import it.fff.business.common.eo.EventCategoryEO;
import it.fff.clientserver.common.dto.EventCategoryDTO;

public class EventCategoryMapper implements Mapper<EventCategoryDTO,EventCategoryBO,EventCategoryEO>{

	private static EventCategoryMapper mapper;
	
	private EventCategoryMapper(){
		
	}
	
	public static EventCategoryMapper getInstance(){
		if(mapper==null){
			mapper= new  EventCategoryMapper();
		}
		return mapper;
	}
	
	
	@Override
	public List<EventCategoryBO> mapDTOs2BOs(List<EventCategoryDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventCategoryBO mapDTO2BO(EventCategoryDTO dto) {
		EventCategoryBO bo = null;
		if(dto!=null){
			bo = new EventCategoryBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setDescrizione(dto.getDescrizione());
		}
		return bo;
	}

	@Override
	public List<EventCategoryEO> mergeBOs2EOs(List<EventCategoryBO> bos, List<EventCategoryEO> eos, Session session) {
		if(bos!=null){
			if(eos==null){
				eos = new ArrayList<EventCategoryEO>();
			}
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			for (EventCategoryBO bo : bos) {
				eos.add(eventCategoryMapper.mergeBO2EO(bo,null,session));
			}
		}
		return eos;
	}

	@Override
	public EventCategoryEO mergeBO2EO(EventCategoryBO bo, EventCategoryEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				//L'entita' non va mai creata/modificata quindi avro' sempre id valorizzato se ho il BO
				//Quindi non ho setter sul EO
				eo = (EventCategoryEO)session.load(EventCategoryEO.class, bo.getId());
			}
		}
		return eo;
		
//		if(bo!=null){
//			if(bo.getId()>0){
//				eo = (EventCategoryEO)session.load(EventCategoryEO.class, bo.getId());
//			}
//			if(eo==null){
//				eo = new EventCategoryEO();
//			}
//			eo.setIdIfNotEmpty(bo.getId());
//			eo.setNomeIfNotEmpty(bo.getNome());
//			eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
//		}
//		return eo;
	}

	@Override
	public List<EventCategoryBO> mapEOs2BOs(List<EventCategoryEO> eos) {
		List<EventCategoryBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<EventCategoryBO>();
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			for (EventCategoryEO eo : eos) {
				bos.add(eventCategoryMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public EventCategoryBO mapEO2BO(EventCategoryEO eo) {
		EventCategoryBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new EventCategoryBO();
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setDescrizione(eo.getDescrizione());
		}
		return bo;
	}

	@Override
	public List<EventCategoryDTO> mapBOs2DTOs(List<EventCategoryBO> bos) {
		List<EventCategoryDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<EventCategoryDTO>();
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			for (EventCategoryBO bo : bos) {
				dtos.add(eventCategoryMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public EventCategoryDTO mapBO2DTO(EventCategoryBO bo) {
		EventCategoryDTO dto = null;
		if(bo!=null){
			dto = new EventCategoryDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}	
}
