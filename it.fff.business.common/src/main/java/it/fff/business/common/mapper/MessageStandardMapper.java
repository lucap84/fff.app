package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.eo.MessageEO;
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
		List<MessageStandardDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<MessageStandardDTO>();
			MessageStandardMapper messageStandardMapper = MessageStandardMapper.getInstance();
			for (MessageStandardBO bo : bos) {
				dtos.add(messageStandardMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public MessageStandardDTO mapBO2DTO(MessageStandardBO bo) {
		MessageStandardDTO dto = null;
		if(bo!=null){
			dto = new MessageStandardDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setText(bo.getText());
		}
		return dto;
	}

	@Override
	public List<MessageStandardBO> mapEOs2BOs(List<MessageStandardEO> eos) {
		List<MessageStandardBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<MessageStandardBO>();
			MessageStandardMapper messageStandardMapper = MessageStandardMapper.getInstance();
			for (MessageStandardEO eo : eos) {
				bos.add(messageStandardMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public MessageStandardBO mapEO2BO(MessageStandardEO eo) {
		MessageStandardBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new MessageStandardBO();
			bo.setId(eo.getId());
			bo.setText(eo.getStandardText());
		}
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
	public List<MessageStandardEO> mergeBOs2EOs(List<MessageStandardBO> bos, List<MessageStandardEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageStandardEO mergeBO2EO(MessageStandardBO bo, MessageStandardEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				//L'entita' non va mai creata/modificata quindi avro' sempre id valorizzato se ho il BO
				//Quindi non ho setter sul EO				
				eo = (MessageStandardEO)session.load(MessageStandardEO.class, bo.getId());
			}
		}
		return eo;
	}

}
