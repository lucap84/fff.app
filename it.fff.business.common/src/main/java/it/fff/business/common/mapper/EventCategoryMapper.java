package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.EventCategoryBO;
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
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setDescrizione(dto.getDescrizione());
		}
		return bo;
	}

	@Override
	public List<EventCategoryEO> mergeBOs2EOs(List<EventCategoryBO> bos, List<EventCategoryEO> eso) {
		return null;
	}

	@Override
	public EventCategoryEO mergeBO2EO(EventCategoryBO bo, EventCategoryEO eo) {
		return null;
	}

	@Override
	public List<EventCategoryBO> mapEOs2BOs(List<EventCategoryEO> eos) {
		List<EventCategoryBO> bos = null;
		if(eos!=null){
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
		if(eo!=null){
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
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}	
}
