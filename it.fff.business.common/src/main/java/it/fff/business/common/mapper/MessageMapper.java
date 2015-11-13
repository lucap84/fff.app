package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.MessageBO;
import it.fff.clientserver.common.dto.MessageDTO;

public class MessageMapper implements Mapper {


	public static List<MessageDTO> map2DTO(List<MessageBO> messagesBO) {
		List<MessageDTO> dtos = new ArrayList<MessageDTO>();
		for (MessageBO bo : messagesBO) {
			dtos.add(MessageMapper.map2DTO(bo));
		}
		return dtos;
	}
	
	public static MessageDTO map2DTO(MessageBO bo) {
		MessageDTO dto = new MessageDTO();
		dto.setId(String.valueOf(bo.getId()));
		dto.setStandard(bo.isStandard());
		dto.setText(bo.getText());
		return dto;
	}	
	
}
