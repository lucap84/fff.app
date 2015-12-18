package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.eo.MessageEO;
import it.fff.clientserver.common.dto.MessageDTO;

public class MessageMapper implements Mapper<MessageDTO,MessageBO,MessageEO>{

	private static MessageMapper mapper;
	
	private MessageMapper(){
		
	}
	
	public static MessageMapper getInstance(){
		if(mapper==null){
			mapper= new  MessageMapper();
		}
		return mapper;
	}
	

	@Override
	public List<MessageBO> mapDTOs2BOs(List<MessageDTO> dtos) {
		List<MessageBO> bos = null;
		if(dtos!=null){
			bos = new ArrayList<MessageBO>();
			MessageMapper messageMapper = MessageMapper.getInstance();
			for (MessageDTO dto : dtos) {
				bos.add(messageMapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	@Override
	public MessageBO mapDTO2BO(MessageDTO dto) {
		MessageBO bo = null;
		if(dto!=null){
			bo = new MessageBO();
			if(dto.isStandard()){
				MessageStandardBO stdbo = new MessageStandardBO();
				stdbo.setId(Integer.valueOf(dto.getId()));
				bo.setMsgStd(stdbo);
			}
			else{
				if(dto.getId()>0){
					bo.setId(Integer.valueOf(dto.getId()));
				}
				bo.setText(dto.getText());
			}
			
			bo.setDataCreazione(dto.getDataCreazione());
			
			if(dto.getAttendanceId()>0){
				AttendanceBO attbo = new AttendanceBO();
				attbo.setId(Integer.valueOf(dto.getAttendanceId()));
				bo.setAttendance(attbo);
			}
				
			if(dto.getEventId()>0){
				EventBO evbo = new EventBO();
				evbo.setId(Integer.valueOf(dto.getEventId()));
				bo.setEvent(evbo);
			}			
		}
		return bo;
	}

	@Override
	public List<MessageEO> mergeBOs2EOs(List<MessageBO> bos, List<MessageEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageEO mergeBO2EO(MessageBO bo, MessageEO eo, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageBO> mapEOs2BOs(List<MessageEO> eos) {
		List<MessageBO> bos = null;
		if(eos!=null){
			bos = new ArrayList<MessageBO>();
			MessageMapper messageMapper = MessageMapper.getInstance();
			for (MessageEO eo : eos) {
				bos.add(messageMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public MessageBO mapEO2BO(MessageEO eo) {
		MessageBO bo = null;
		if(eo!=null){
			bo = new MessageBO();
			bo.setId(eo.getId());
			
			MessageStandardMapper messageStandardMapper = MessageStandardMapper.getInstance();
			bo.setMsgStd(messageStandardMapper.mapEO2BO(eo.getMsgStd()));
			bo.setText(eo.getText());
			bo.setDataCreazione(eo.getDataCreazione());
			
			EventMapper eventMapper = EventMapper.getInstance();
			bo.setEvent(eventMapper.mapEO2BO(eo.getEvent()));
		}
		return bo;
	}

	@Override
	public List<MessageDTO> mapBOs2DTOs(List<MessageBO> bos) {
		List<MessageDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<MessageDTO>();
			MessageMapper messageMapper = MessageMapper.getInstance();
			for (MessageBO bo : bos) {
				dtos.add(messageMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public MessageDTO mapBO2DTO(MessageBO bo) {
		MessageDTO dto = null;
		if(bo!=null){
			dto = new MessageDTO();
			
			if(bo.getMsgStd()!=null){
				dto.setId(bo.getMsgStd().getId());
				dto.setStandard(true);
				dto.setText(bo.getMsgStd().getText());
			}else{
				if(bo.getId()>0){
					dto.setId(bo.getId());
				}
				dto.setText(bo.getText());
			}

			dto.setDataCreazione(bo.getDataCreazione());
			
			dto.setAttendanceId(bo.getAttendance().getId());
			
			dto.setEventId(bo.getEvent().getId());
			
		}
		return dto;
	}

}
