package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.eo.MessageStandardEO;
import it.fff.clientserver.common.dto.LanguageDTO;
import it.fff.clientserver.common.dto.MessageStandardDTO;

public class MessageStandardMapper implements Mapper{

	public static List<MessageStandardDTO> mapBO2DTO(List<MessageStandardBO> bos) {
		List<MessageStandardDTO> dtos = new ArrayList<MessageStandardDTO>();
		if(bos!=null){
			for (MessageStandardBO bo : bos) {
				dtos.add(MessageStandardMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	public static MessageStandardDTO mapBO2DTO(MessageStandardBO bo) {
		MessageStandardDTO dto = new MessageStandardDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setText(bo.getText());
		}
		return dto;
	}

	public static List<MessageStandardBO> mapEOs2BOs(List<MessageStandardEO> eos) {
		List<MessageStandardBO> bos = new ArrayList<MessageStandardBO>();
		if(eos!=null){
			for (MessageStandardEO eo : eos) {
				bos.add(MessageStandardMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	public static MessageStandardBO mapEO2BO(MessageStandardEO eo) {
		MessageStandardBO bo = new MessageStandardBO();
		bo.setId(eo.getId());
		bo.setText(eo.getStandardText());
		return bo;
	}

}
