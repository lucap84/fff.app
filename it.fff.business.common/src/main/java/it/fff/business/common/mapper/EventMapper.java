package it.fff.business.common.mapper;


import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.util.AttendancesUtil;
import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.enums.EventStateEnum;

public class EventMapper implements Mapper<EventDTO,EventBO,EventEO>{
	
	private static final Logger logger = LogManager.getLogger(EventMapper.class);
	
	private static EventMapper mapper;
	
	private EventMapper(){
		
	}
	
	public static EventMapper getInstance(){
		if(mapper==null){
			mapper= new  EventMapper();
		}
		return mapper;
	}

	@Override
	public List<EventBO> mapDTOs2BOs(List<EventDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventBO mapDTO2BO(EventDTO dto) {
		EventBO bo = null;
		if(dto!=null){
			bo = new EventBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setTitolo(dto.getTitolo());
			bo.setDescrizione(dto.getDescrizione());
			bo.setDurata(Integer.valueOf(dto.getDurata()));
			bo.setDataInizio(dto.getDataInizio());
			
			bo.setStato(dto.getStato());
			
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			bo.setLocation(placeMapper.mapDTO2BO(dto.getLocation()));
			
			MessageMapper messageMapper = MessageMapper.getInstance();
			bo.setMessages(messageMapper.mapDTOs2BOs(dto.getMessages()));
			
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			bo.setPartecipazioni(attendanceMapper.mapDTOs2BOs(dto.getPartecipazioni()));
			
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			bo.setCategoria(eventCategoryMapper.mapDTO2BO(dto.getCategoria()));
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	@Override
	public List<EventEO> mergeBOs2EOs(List<EventBO> bos, List<EventEO> eso, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventEO mergeBO2EO(EventBO bo, EventEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (EventEO)session.load(EventEO.class, bo.getId());
			}
			if(eo==null){
				eo = new EventEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setTitoloIfNotEmpty(bo.getTitolo());
			eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
			eo.setDurataIfNotEmpty(bo.getDurata());
			eo.setDataInizioIfNotEmpty(bo.getDataInizio());
			
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			eo.setLocation(placeMapper.mergeBO2EO(bo.getLocation(), eo.getLocation(), session));
			
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			eo.setPartecipazioni(attendanceMapper.mergeBOs2EOs(bo.getPartecipazioni(),eo.getPartecipazioni(), session));
			
			int numUsers = AttendancesUtil.calculateTotalUserAttendances(bo.getPartecipazioni());
			int numGuests = AttendancesUtil.calculateTotalGuestAttendances(bo.getPartecipazioni());
			eo.setNumUserAttendancesIfNotEmpty(numUsers);
			eo.setNumGuestAttendancesIfNotEmpty(numGuests);
			
			
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			eo.setCategoria(eventCategoryMapper.mergeBO2EO(bo.getCategoria(),eo.getCategoria(), session));
			
			EventStateMapper eventStateMapper = EventStateMapper.getInstance();
			eo.setStato(eventStateMapper.mergeBO2EO(bo.getStato(), eo.getStato(), session));
			
		}
		return eo;
	}

	@Override
	public List<EventBO> mapEOs2BOs(List<EventEO> eos) {
		List<EventBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<EventBO>();
			EventMapper eventMapper = EventMapper.getInstance();
			for (EventEO eo : eos) {
				bos.add(eventMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public EventBO mapEO2BO(EventEO eo) {
		EventBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new EventBO();
			bo.setId(eo.getId()==null?0:eo.getId()); //per evitare nullpointer in fase di cast
			bo.setTitolo(eo.getTitolo());
			bo.setDescrizione(eo.getDescrizione());
			bo.setDurata(eo.getDurata()==null?0:eo.getDurata()); //per evitare nullpointer in fase di cast
			bo.setDataInizio(eo.getDataInizio());
			
			bo.setStato(EventStateEnum.valueOf(eo.getStato().getNome()));

			MessageMapper messageMapper = MessageMapper.getInstance();
			bo.setMessages(messageMapper.mapEOs2BOs(eo.getMessages()));
			
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			bo.setLocation(placeMapper.mapEO2BO(eo.getLocation()));
			
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			bo.setPartecipazioni(attendanceMapper.mapEOs2BOs(eo.getPartecipazioni()));
			
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			bo.setCategoria(eventCategoryMapper.mapEO2BO(eo.getCategoria()));
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	@Override
	public List<EventDTO> mapBOs2DTOs(List<EventBO> bos) {
		List<EventDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<EventDTO>();
			EventMapper eventMapper = EventMapper.getInstance();
			for (EventBO bo : bos) {
				dtos.add(eventMapper.mapBO2DTO(bo));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return dtos;
	}

	@Override
	public EventDTO mapBO2DTO(EventBO bo) {
		EventDTO dto = null;
		if(bo!=null){
			dto = new EventDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setTitolo(bo.getTitolo());
			dto.setDescrizione(bo.getDescrizione());
			dto.setDurata(bo.getDurata());
			dto.setDataInizio(bo.getDataInizio());
			
			dto.setStato(bo.getStato());
			
			MessageMapper messageMapper = MessageMapper.getInstance();
			dto.setMessages(messageMapper.mapBOs2DTOs(bo.getMessages()));
			
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			dto.setLocation(placeMapper.mapBO2DTO(bo.getLocation()));
			
			if(bo.getPartecipazioni()!=null){
				AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
				dto.setPartecipazioni(attendanceMapper.mapBOs2DTOs(bo.getPartecipazioni()));
				
				int numOspiti = 0;
				int numUtentiPartecipanti = 0;
				for (AttendanceBO attBO : bo.getPartecipazioni()) {
					if(attBO.isValid()){
						numUtentiPartecipanti++;
						numOspiti += attBO.getNumeroOspiti();
					}
				}
				int totalePartecipanti = numUtentiPartecipanti + numOspiti; 
				dto.setTotalePartecipanti(totalePartecipanti);
			}
			
			EventCategoryMapper eventCategoryMapper = EventCategoryMapper.getInstance();
			dto.setCategoria(eventCategoryMapper.mapBO2DTO(bo.getCategoria()));
		}
		else{logger.warn("Mapping null objects!!");}
		return dto;
	}


}
