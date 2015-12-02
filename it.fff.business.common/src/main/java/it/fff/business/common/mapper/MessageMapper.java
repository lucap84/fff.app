package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.MessageBO;
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
			if(dto.getId()!=null && "".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setStandard(dto.isStandard());
			bo.setText(dto.getText());
		}
		return bo;
	}

	@Override
	public List<MessageEO> mergeBOs2EOs(List<MessageBO> bos, List<MessageEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageEO mergeBO2EO(MessageBO bo, MessageEO eo) {
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
			bo.setStandard(eo.getMsgStd()!=null);
			bo.setText(eo.getText());
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
			dto.setId(String.valueOf(bo.getId()));
			dto.setStandard(bo.isStandard());
			dto.setText(bo.getText());
		}
		return dto;
	}

}
