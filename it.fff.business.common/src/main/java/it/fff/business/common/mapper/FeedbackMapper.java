package it.fff.business.common.mapper;

import java.util.List;

import org.hibernate.Session;

import it.fff.clientserver.common.enums.FeedbackEnum;

public class FeedbackMapper implements Mapper<FeedbackEnum, FeedbackEnum, Void>{

	@Override
	public List<FeedbackEnum> mapDTOs2BOs(List<FeedbackEnum> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedbackEnum mapDTO2BO(FeedbackEnum dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Void> mergeBOs2EOs(List<FeedbackEnum> bos, List<Void> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void mergeBO2EO(FeedbackEnum bo, Void eo, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeedbackEnum> mapEOs2BOs(List<Void> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedbackEnum mapEO2BO(Void eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeedbackEnum> mapBOs2DTOs(List<FeedbackEnum> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedbackEnum mapBO2DTO(FeedbackEnum bo) {
		// TODO Auto-generated method stub
		return null;
	}

}
