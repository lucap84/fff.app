package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.eo.MessageStandardEO;
import it.fff.clientserver.common.dto.MessageStandardDTO;

public class MessageStandardMapper implements Mapper<MessageStandardDTO,MessageStandardBO,MessageStandardEO>{

	private static MessageStandardMapper mapper;
	
	private MessageStandardMapper(){
		
	}
	
	public static MessageStandardMapper getInstance(){
		if(mapper==null){
			mapper= new  MessageStandardMapper();
		}
		return mapper;
	}
	
	@Override
	public List<MessageStandardDTO> mapBOs2DTOs(List<MessageStandardBO> bos) {
		List<MessageStandardDTO> dtos = new ArrayList<MessageStandardDTO>();
		if(bos!=null){
			MessageStandardMapper messageStandardMapper = MessageStandardMapper.getInstance();
			for (MessageStandardBO bo : bos) {
				dtos.add(messageStandardMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public MessageStandardDTO mapBO2DTO(MessageStandardBO bo) {
		MessageStandardDTO dto = new MessageStandardDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setText(bo.getText());
		}
		return dto;
	}

	@Override
	public List<MessageStandardBO> mapEOs2BOs(List<MessageStandardEO> eos) {
		List<MessageStandardBO> bos = new ArrayList<MessageStandardBO>();
		if(eos!=null){
			MessageStandardMapper messageStandardMapper = MessageStandardMapper.getInstance();
			for (MessageStandardEO eo : eos) {
				bos.add(messageStandardMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public MessageStandardBO mapEO2BO(MessageStandardEO eo) {
		MessageStandardBO bo = new MessageStandardBO();
		bo.setId(eo.getId());
		bo.setText(eo.getStandardText());
		return bo;
	}

	@Override
	public List<MessageStandardBO> mapDTOs2BOs(List<MessageStandardDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageStandardBO mapDTO2BO(MessageStandardDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageStandardEO> mergeBOs2EOs(List<MessageStandardBO> bos, List<MessageStandardEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageStandardEO mergeBO2EO(MessageStandardBO bo, MessageStandardEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

}
