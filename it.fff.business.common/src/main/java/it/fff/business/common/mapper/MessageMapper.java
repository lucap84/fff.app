package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.eo.MessageEO;
import it.fff.clientserver.common.dto.MessageDTO;

public class MessageMapper implements Mapper {


	public static List<MessageDTO> mapBO2DTO(List<MessageBO> bos) {
		List<MessageDTO> dtos = new ArrayList<MessageDTO>();
		if(bos!=null){
			for (MessageBO bo : bos) {
				dtos.add(MessageMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}
	
	public static MessageDTO mapBO2DTO(MessageBO bo) {
		MessageDTO dto = new MessageDTO();
		if(bo!=null){
			dto.setId(String.valueOf(bo.getId()));
			dto.setStandard(bo.isStandard());
			dto.setText(bo.getText());
		}
		return dto;
	}

	public static List<MessageBO> mapEO2BO(List<MessageEO> eos) {
		List<MessageBO> bos = new ArrayList<MessageBO>();
		if(eos!=null){
			for (MessageEO eo : eos) {
				bos.add(MessageMapper.mapEO2DTO(eo));
			}
		}
		return bos;
	}

	public static MessageBO mapEO2DTO(MessageEO eo) {
		MessageBO bo = new MessageBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setStandard(eo.getMsgStd()!=null);
			bo.setText(eo.getText());
		}
		return bo;
	}

	public static List<MessageBO> mapDTO2BO(List<MessageDTO> dtos) {
		List<MessageBO> bos = new ArrayList<MessageBO>();
		if(dtos!=null){
			for (MessageDTO dto : dtos) {
				bos.add(MessageMapper.mapDTO2BO(dto));
			}
		}
		return bos;
	}

	public static MessageBO mapDTO2BO(MessageDTO dto) {
		MessageBO bo = new MessageBO();
		if(dto!=null){
			if(dto.getId()!=null && "".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setStandard(dto.isStandard());
			bo.setText(dto.getText());
		}
		return bo;
	}

}
