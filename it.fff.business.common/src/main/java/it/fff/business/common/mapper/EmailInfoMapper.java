package it.fff.business.common.mapper;

import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.eo.AccountEO;
import it.fff.clientserver.common.dto.AccountDTO;
import it.fff.clientserver.common.dto.EmailInfoDTO;

public class EmailInfoMapper implements Mapper<EmailInfoDTO,EmailInfoBO,Void>{

	private static EmailInfoMapper mapper;
	
	private EmailInfoMapper(){
		
	}
	
	public static EmailInfoMapper getInstance(){
		if(mapper==null){
			mapper= new  EmailInfoMapper();
		}
		return mapper;
	}

	@Override
	public List<EmailInfoBO> mapDTOs2BOs(List<EmailInfoDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailInfoBO mapDTO2BO(EmailInfoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Void> mergeBOs2EOs(List<EmailInfoBO> bos, List<Void> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void mergeBO2EO(EmailInfoBO bo, Void eo, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailInfoBO> mapEOs2BOs(List<Void> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailInfoBO mapEO2BO(Void eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailInfoDTO> mapBOs2DTOs(List<EmailInfoBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailInfoDTO mapBO2DTO(EmailInfoBO bo) {
		EmailInfoDTO dto = null;
		if(bo!=null){
			dto = new EmailInfoDTO();
			dto.setEmail(bo.getEmail());
			dto.setExisting(bo.isExisting());
			dto.setValidAccount(bo.isValidAccount());
			dto.setVerifiedAccount(bo.isVerifiedAccount());
		}
		return dto;
	}
	


}
