package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.*;
import it.fff.business.common.eo.*;
import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.enums.UserSexEnum;

public class UserMapper implements Mapper<UserDTO,UserBO,UserEO>{
	
	private static UserMapper mapper;
	
	private UserMapper(){
		
	}
	
	public static UserMapper getInstance(){
		if(mapper==null){
			mapper= new  UserMapper();
		}
		return mapper;
	}
	
	@Override
	public UserBO mapDTO2BO(UserDTO dto) {
		UserBO bo = null;
		if(dto!=null){
			bo = new UserBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			AccountMapper accountMapper = AccountMapper.getInstance();
			bo.setAccount(accountMapper.mapDTO2BO(dto.getAccount()));
			
			bo.setFacebookId(dto.getFacebookId());
			bo.setNome(dto.getNome());
			bo.setCognome(dto.getCognome());
			bo.setSesso(dto.getSesso());
			bo.setDataNascita(dto.getDataNascita());
			bo.setDescrizione(dto.getDescrizione());
			
			NationMapper nationMapper = NationMapper.getInstance();
			bo.setNazionalita(nationMapper.mapDTO2BO(dto.getNazionalita()));
			
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			bo.setLingue(languageMapper.mapDTOs2BOs(dto.getLingue()));
			
			bo.setLastPositionDate(dto.getLastPositionDate());
			if(dto.getLastPositionLat()!=null){
				bo.setLastPositionLat(Double.valueOf(dto.getLastPositionLat()));
			}
			if(dto.getLastPositionLong()!=null){
				bo.setLastPositionLong(Double.valueOf(dto.getLastPositionLong()));
			}
		}
		return bo;
	}

	@Override
	public UserDTO mapBO2DTO(UserBO bo) {
		UserDTO dto = null;
		if(bo!=null){
			dto = new UserDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			AccountMapper accountMapper = AccountMapper.getInstance();
			dto.setAccount(accountMapper.mapBO2DTO(bo.getAccount()));
			
			dto.setNome(bo.getNome());
			dto.setFacebookId(bo.getFacebookId());
			dto.setCognome(bo.getCognome());
			dto.setSesso(bo.getSesso());
			dto.setDataNascita(bo.getDataNascita());
			dto.setDescrizione(bo.getDescrizione());
			
			NationMapper nationMapper = NationMapper.getInstance();
			dto.setNazionalita(nationMapper.mapBO2DTO(bo.getNazionalita()));
			
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			dto.setLingue(languageMapper.mapBOs2DTOs(bo.getLingue()));
			
			dto.setLastPositionDate(bo.getLastPositionDate());
			if(bo.getLastPositionLat()!=0){
				dto.setLastPositionLat(String.valueOf(bo.getLastPositionLat()));
			}
			if(bo.getLastPositionLong()!=0){
				dto.setLastPositionLong(String.valueOf(bo.getLastPositionLong()));
			}
			
			List<FeedbackEnum> feedbacks = bo.getFeedbacks();
			int numPositiveFeedbacks = 0;
			int numNegativeFeedbacks = 0;
			for (FeedbackEnum feedbackEnum : feedbacks) {
				if(feedbackEnum== FeedbackEnum.POSITIVE){numPositiveFeedbacks++;}
				if(feedbackEnum== FeedbackEnum.NEGATIVE){numNegativeFeedbacks++;}
			}
			dto.setNumPositiveFeedbacks(numPositiveFeedbacks);
			dto.setNumNegativeFeedbacks(numNegativeFeedbacks);
		}
		return dto;
	}

	@Override
	public UserBO mapEO2BO(UserEO eo) {
		UserBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new UserBO();
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setCognome(eo.getCognome());
			bo.setDataNascita(eo.getDataNascita());
			bo.setDescrizione(eo.getDescrizione());
			bo.setFlagAttivo(eo.isFlagAttivo());
			bo.setLastPositionDate(eo.getLastPositionDate());
			bo.setLastPositionLat(eo.getLastPositionLat());
			bo.setLastPositionLong(eo.getLastPositionLong());
			
			bo.setSesso(UserSexEnum.valueOf(eo.getSesso().toUpperCase()));
			
			AccountMapper accountMapper = AccountMapper.getInstance();
			bo.setAccount(accountMapper.mapEO2BO(eo.getAccount()));
			
			NationMapper nationMapper = NationMapper.getInstance();
			bo.setNazionalita(nationMapper.mapEO2BO(eo.getNazionalita()));

			LanguageMapper languageMapper = LanguageMapper.getInstance();
			bo.setLingue(languageMapper.mapEOs2BOs(eo.getLingue()));

			AchievementMapper achievementMapper = AchievementMapper.getInstance();
			bo.setAchievements(achievementMapper.mapEOs2BOs(eo.getAchievements()));
		}
		return bo;
	}


	public UserBO mapDTO2BO(RegistrationInputDTO dto) {
		UserBO bo1 = null;
		
		if(dto!=null){
			bo1 = new UserBO();
			SessionBO bo3 = new SessionBO();
			bo3.setDeviceId(dto.getDeviceId());
			bo3.setSharedKey(dto.getSharedKey());
			
			AccountBO bo2 = new AccountBO();
			bo2.setEmail(dto.getEmail());
			bo2.setPassword(dto.getEncodedPassword());
			bo2.setSessions(new ArrayList<SessionBO>());
			bo3.setAccount(bo2);//Ogni session ha il riferimento all'account relativo
			bo2.getSessions().add(bo3);
			
			bo1.setNome(dto.getNome());
			bo1.setCognome(dto.getCognome());
			bo1.setDataNascita(dto.getDataNascita());
			bo1.setSesso(dto.getSesso());
			bo1.setAccount(bo2);
		}
		return bo1;
	}

	@Override
	public UserEO mergeBO2EO(UserBO bo, UserEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (UserEO)session.load(UserEO.class, bo.getId());
			}
			if(eo==null){
				eo = new UserEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			//TODO aggiungi facebookID sul DB
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setCognomeIfNotEmpty(bo.getCognome());
			eo.setSessoIfNotEmpty(bo.getSesso().toString());
			eo.setDataNascitaIfNotEmpty(bo.getDataNascita());
			eo.setDescrizioneIfNotEmpty(bo.getDescrizione());
			eo.setLastPositionLatIfNotEmpty(bo.getLastPositionLat());
			eo.setLastPositionLongIfNotEmpty(bo.getLastPositionLong());
			eo.setLastPositionDateIfNotEmpty(bo.getLastPositionDate());
			eo.setFlagAttivo(bo.isFlagAttivo());
			
			LanguageMapper languageMapper = LanguageMapper.getInstance();
			List<LanguageEO> lingueEO = languageMapper.mergeBOs2EOs(bo.getLingue(),eo.getLingue(), session);
			eo.setLingue(lingueEO);
			
			AccountMapper accountMapper = AccountMapper.getInstance();
			AccountEO accountEO = accountMapper.mergeBO2EO(bo.getAccount(), eo.getAccount(), session);
			eo.setAccount(accountEO);
			
			NationMapper nationMapper = NationMapper.getInstance();
			NationEO nazionalitaEO = nationMapper.mergeBO2EO(bo.getNazionalita(),eo.getNazionalita(), session);
			eo.setNazionalita(nazionalitaEO);
		}
		return eo;
	}

	@Override
	public List<UserBO> mapDTOs2BOs(List<UserDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEO> mergeBOs2EOs(List<UserBO> bos, List<UserEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserBO> mapEOs2BOs(List<UserEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> mapBOs2DTOs(List<UserBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
