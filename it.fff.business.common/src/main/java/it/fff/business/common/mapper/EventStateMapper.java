package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.eo.EventStateEO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;

public class EventStateMapper implements Mapper<EventStateEnum,EventStateEnum,EventStateEO>{
	
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
	public List<EventStateEnum> mapDTOs2BOs(List<EventStateEnum> dtos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EventStateEnum mapDTO2BO(EventStateEnum dto) {
		if(dto==null){
			return EventStateEnum.UNKNOW;
		}
		return dto;
	}


	@Override
	public List<EventStateEO> mergeBOs2EOs(List<EventStateEnum> bos, List<EventStateEO> eos, Session session) {
		if(bos!=null){
			eos = new ArrayList<EventStateEO>();
			EventStateMapper eventStateMapper = EventStateMapper.getInstance();
			for (EventStateEnum bo : bos) {
				eos.add(eventStateMapper.mergeBO2EO(bo,null, session));
			}
		}
		return eos;
	}


	@Override
	public EventStateEO mergeBO2EO(EventStateEnum bo, EventStateEO eo, Session session) {
		if(bo!=null && bo!=EventStateEnum.UNKNOW){
			//L'entita' non va mai creata/modificata quindi avro' sempre id valorizzato se ho il BO
			//Quindi non ho setter sul EO			
			eo = (EventStateEO)session.load(EventStateEO.class, bo.getId());
		}
		return eo;
	}


	@Override
	public List<EventStateEnum> mapEOs2BOs(List<EventStateEO> eos) {
		List<EventStateEnum> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<EventStateEnum>();
			EventStateMapper eventStateMapper = EventStateMapper.getInstance();
			for (EventStateEO eo : eos) {
				bos.add(eventStateMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}


	@Override
	public EventStateEnum mapEO2BO(EventStateEO eo) {
		EventStateEnum bo = EventStateEnum.UNKNOW;
		if(eo!=null && isInitialized(eo)){
			bo = EventStateEnum.valueOf(eo.getNome().toUpperCase());
		}
		else{
			bo = EventStateEnum.UNKNOW;
		}		
		return bo;
	}


	@Override
	public List<EventStateEnum> mapBOs2DTOs(List<EventStateEnum> bos) {
		List<EventStateEnum> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<EventStateEnum>();
			EventStateMapper eventStateMapper = EventStateMapper.getInstance();
			for (EventStateEnum bo : bos) {
				dtos.add(eventStateMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}


	@Override
	public EventStateEnum mapBO2DTO(EventStateEnum bo) {
		if(bo==null){
			return EventStateEnum.UNKNOW;
		}
		return bo;
	}
}
