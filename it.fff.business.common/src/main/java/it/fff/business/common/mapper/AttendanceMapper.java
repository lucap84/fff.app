package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.UserEO;
import it.fff.clientserver.common.dto.AttendanceDTO;
import it.fff.clientserver.common.enums.FeedbackEnum;

public class AttendanceMapper implements Mapper<AttendanceDTO,AttendanceBO,AttendanceEO>{

	private static AttendanceMapper mapper;
	
	private AttendanceMapper(){
		
	}
	
	public static AttendanceMapper getInstance(){
		if(mapper==null){
			mapper= new  AttendanceMapper();
		}
		return mapper;
	}
	
	@Override
	public List<AttendanceBO> mapDTOs2BOs(List<AttendanceDTO> dtos) {
		List<AttendanceBO> bos = null;
		if(dtos!=null){
			 bos = new  ArrayList<AttendanceBO>();
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceDTO dto : dtos) {
				bos.add(attendanceMapper.mapDTO2BO(dto));
			}
		}
		return bos; 
	}	
	
	@Override
	public AttendanceBO mapDTO2BO(AttendanceDTO dto) {
		AttendanceBO bo = null;
		if(dto!=null){
			bo = new AttendanceBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			
			bo.setNumeroOspiti(dto.getNumeroOspiti());
			bo.setValid(dto.isValid());
			bo.setStato(dto.getStato());
			bo.setOrganizer(dto.isOrganizer());
			
			bo.setFeedback(dto.getFeedback());
			
			if(dto.getEventId()>0){
				EventBO event = new EventBO();
				event.setId(Integer.valueOf(dto.getEventId()));
				bo.setEventId(event.getId());
			}

			if(dto.getUserId()>0){
				UserBO user = new UserBO();
				user.setId(Integer.valueOf(dto.getUserId()));
				bo.setUtente(user);
			}
			
		}
		return bo;
	}	
	
	@Override
	public List<AttendanceDTO> mapBOs2DTOs(List<AttendanceBO> bos) {
		List<AttendanceDTO> dtos = null;
		if(bos!=null){
			dtos = new  ArrayList<AttendanceDTO>();
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceBO bo : bos) {
				dtos.add(attendanceMapper.mapBO2DTO(bo));
			}
		}
		return dtos; 
	}

	@Override
	public AttendanceDTO mapBO2DTO(AttendanceBO bo) {
		AttendanceDTO dto = new AttendanceDTO();
		if(bo!=null){
			dto.setId(bo.getId());
			dto.setValid(bo.isValid());
			dto.setFeedback(bo.getFeedback());
			dto.setNumeroOspiti(bo.getNumeroOspiti());
			dto.setOrganizer(bo.isOrganizer());
			dto.setStato(bo.getStato());
			dto.setDataCreazione(bo.getDataCreazione());
			dto.setDataAggiornamento(bo.getDataAggiornamento());
			
			dto.setEventId(bo.getEventId());
			dto.setUserId(bo.getUtente().getId());
			
		}
		return dto;
	}
	
	@Override
	public AttendanceEO mergeBO2EO(AttendanceBO bo, AttendanceEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (AttendanceEO)session.load(AttendanceEO.class, bo.getId());
			}
			if(eo==null){
				eo = new AttendanceEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNumeroOspitiIfNotEmpty(bo.getNumeroOspiti());
			eo.setOrganizer(bo.isOrganizer());
			eo.setValid(bo.isValid());
			
			switch (bo.getFeedback()) {
			case POSITIVE:
				eo.setPositiveFeedback(true);
				break;
			case NEGATIVE:
				eo.setPositiveFeedback(false);
				break;
			case UNKNOW:
				eo.setPositiveFeedback(null);
				break;				
			default:
				break;
			}
			
			if(bo.getEventId()>0 && eo.getEvent()==null){
				EventEO eventEO = (EventEO)session.load(EventEO.class, bo.getEventId());
				eo.setEvent(eventEO);
			}
			
			AttendanceStateMapper attendanceMapper = AttendanceStateMapper.getInstance();
			eo.setStato(attendanceMapper.mergeBO2EO(bo.getStato(), eo.getStato(), session));
			
			if(bo.getUtente()!=null && bo.getUtente().getId()>0 && eo.getUtente()==null){
				UserEO userEO = (UserEO)session.load(UserEO.class, bo.getUtente().getId());
				eo.setUtente(userEO);
			}
		}
		return eo;
	}	
	
	@Override
	public List<AttendanceBO> mapEOs2BOs(List<AttendanceEO> eos) {
		List<AttendanceBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new  ArrayList<AttendanceBO>();
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceEO eo : eos) {
				bos.add(attendanceMapper.mapEO2BO(eo));
			}
		}
		return bos; 
	}

	@Override
	public AttendanceBO mapEO2BO(AttendanceEO eo) {
		AttendanceBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new AttendanceBO();
			bo.setId(eo.getId()==null?0:eo.getId()); //per evitare nullpointer in fase di cast
			bo.setNumeroOspiti(eo.getNumeroOspiti()==null?0:eo.getNumeroOspiti()); //per evitare nullpointer in fase di cast
			bo.setOrganizer(eo.isOrganizer());
			bo.setValid(eo.isValid());
			bo.setDataCreazione(eo.getDataCreazione());
			bo.setDataAggiornamento(eo.getDataAggiornamento());

			if(eo.isPositiveFeedback()==null){
				bo.setFeedback(FeedbackEnum.UNKNOW);
			}
			else if(eo.isPositiveFeedback()==true){
				bo.setFeedback(FeedbackEnum.POSITIVE);
			}
			else if(eo.isPositiveFeedback()==false){
				bo.setFeedback(FeedbackEnum.NEGATIVE);
			}
			
			EventBO eventBO = new EventBO(); //Evito mapping ciclici (EventBO ha una lista di attendances)!
			eventBO.setId(eo.getEvent().getId());
			bo.setEventId(eventBO.getId());
			
			AttendanceStateMapper attendanceStateMapper = AttendanceStateMapper.getInstance();
			bo.setStato(attendanceStateMapper.mapEO2BO(eo.getStato()));
			
			UserMapper userMapper = UserMapper.getInstance();
			bo.setUtente(userMapper.mapEO2BO(eo.getUtente()));
		}
		return bo;
	}


	@Override
	public List<AttendanceEO> mergeBOs2EOs(List<AttendanceBO> bos, List<AttendanceEO> eos, Session session) {
		if(bos!=null){
			if(eos==null){
				eos = new  ArrayList<AttendanceEO>();
			}
			AttendanceMapper attendanceMapper = AttendanceMapper.getInstance();
			for (AttendanceBO bo : bos) {
				AttendanceEO eo = attendanceMapper.mergeBO2EO(bo, null, session);
				eos.add(eo);
			}
		}
		return eos;
	}


}
