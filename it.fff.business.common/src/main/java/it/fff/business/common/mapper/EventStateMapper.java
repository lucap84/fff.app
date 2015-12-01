package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.clientserver.common.dto.EventStateDTO;

public class EventStateMapper implements Mapper<EventStateDTO,EventStateBO,EventStateEO>{
	
	private static EventStateMapper mapper;
	
	private EventStateMapper(){
		
	}
	
	public static EventStateMapper getInstance(){
		if(mapper==null){
			mapper= new  EventStateMapper();
		}
		return mapper;
	}


	@Override
	public List<EventStateBO> mapDTOs2BOs(List<EventStateDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EventStateBO mapDTO2BO(EventStateDTO dto) {
		EventStateBO bo = new EventStateBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setDescrizione(dto.getDescrizione());
		}
		return bo;
	}


	@Override
	public List<EventStateEO> mergeBOs2EOs(List<EventStateBO> bos, List<EventStateEO> eso) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EventStateEO mergeBO2EO(EventStateBO bo, EventStateEO eo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<EventStateBO> mapEOs2BOs(List<EventStateEO> eos) {
		List<EventStateBO> bos = new ArrayList<EventStateBO>();
		if(eos!=null){
			EventStateMapper eventStateMapper = EventStateMapper.getInstance();
			for (EventStateEO eo : eos) {
				bos.add(eventStateMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}


	@Override
	public EventStateBO mapEO2BO(EventStateEO eo) {
		EventStateBO bo = new EventStateBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}


	@Override
	public List<EventStateDTO> mapBOs2DTOs(List<EventStateBO> bos) {
		List<EventStateDTO> dtos = new ArrayList<EventStateDTO>();
		if(bos!=null){
			EventStateMapper eventStateMapper = EventStateMapper.getInstance();
			for (EventStateBO bo : bos) {
				dtos.add(eventStateMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}


	@Override
	public EventStateDTO mapBO2DTO(EventStateBO bo) {
		EventStateDTO dto = null;
		if(bo!=null){
			dto = new EventStateDTO();
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}
}
