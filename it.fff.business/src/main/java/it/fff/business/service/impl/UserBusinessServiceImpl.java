package it.fff.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.notification.MailManager;
import it.fff.business.service.UserBusinessService;
import it.fff.business.strategy.ImageValidationStrategy;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.util.Constants;
import it.fff.integration.facade.exception.IntegrationException;
import it.fff.integration.facade.service.IntegrationServiceFacade;

public class UserBusinessServiceImpl implements UserBusinessService {
	
	private static final Logger logger = LogManager.getLogger(UserBusinessServiceImpl.class);

	private IntegrationServiceFacade integrationFacade;
	private ImageValidationStrategy imageValidationStrategy;
	
	public IntegrationServiceFacade getIntegrationFacade() {
		return integrationFacade;
	}

	public void setIntegrationFacade(IntegrationServiceFacade integrationFacade) {
		this.integrationFacade = integrationFacade;
	}

	public ImageValidationStrategy getImageValidationStrategy() {
		return imageValidationStrategy;
	}
	
	public void setImageValidationStrategy(ImageValidationStrategy imageValidationStrategy) {
		this.imageValidationStrategy = imageValidationStrategy;
	}	
	
	@Override
	public WriteResultBO createUser(UserBO userBO) throws IntegrationException {
		logger.info("createUser start...");
		
		AccountBO creatingAccount = userBO.getAccount();//Inizializzo le validita dell'account
		creatingAccount.setFlgValidita(true);
		
		SessionBO firstSession = creatingAccount.getSessions().get(0);//imposto l'utente come loggato appena creato l'account
		firstSession.setLogged(true);
		
		String loginDate = Constants.DATE_FORMATTER.format(new Date());
		firstSession.setDataLogin(loginDate);//imposto la data login della prima sessione
		
		WriteResultBO resultBO = integrationFacade.registerUser(userBO);
		logger.info("...createUser persistence done");
		
		//Se registrazione avvenuta con successo invio email di conferma
		boolean isSent = false;
		if(resultBO!=null && resultBO.isSuccess()){
			String nomeUtente = userBO.getNome();
			String mailUtente = userBO.getAccount().getEmail();
			
			isSent  = MailManager.getInstance().sendRegistrationConfirmMail(mailUtente, nomeUtente);

			if(!isSent){
				logger.warn("ERRORE: Invio mail verification code non riuscito per utente "+nomeUtente+" (email="+mailUtente+")");
//				throw new IntegrationException("Invio mail verification code non riuscito", null);
			}		
			logger.info("...createUser: mail sending process finished");
		}
		
		return resultBO;
	}


	@Override
	public WriteResultBO updateProfileImage(ProfileImageBO imgBO) throws IntegrationException {
		logger.info("updateProfileImage start...");
		
		logger.debug("image validation...");
		if(!imageValidationStrategy.isValid(imgBO)){
			logger.debug("image is not valid, resizing...");
			imgBO = imageValidationStrategy.validate(imgBO);
		}
		logger.debug("...image validation successfully finished");
		
		imgBO.setProfileImage(true);
		
		WriteResultBO bo = integrationFacade.updateProfileImage(imgBO);
		
		logger.info("...updateProfileImage end");
		return bo;
	}
	

	@Override
	public WriteResultBO updateUserData(UserBO userBO) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.updateUserData(userBO);
		return resultBO;
	}


	@Override
	public UserBO getUser(int userId) throws IntegrationException {
		UserBO bo = integrationFacade.getUser(userId);
		
		//Recupero anche i Feedback dell'utente
		if(bo!=null){
			List<FeedbackEnum> userFeedbacks = integrationFacade.getUserFeedbacks(userId);
			bo.setFeedbacks(userFeedbacks);
		}
		
		//Recupero anche l'immagini utente
		if(bo!=null && (bo.getProfileImages()==null || bo.getProfileImages().size()==0) ){
			ProfileImageBO imageBO = integrationFacade.readProfileImage(userId);
			bo.setProfileImages(new ArrayList<ProfileImageBO>());
			bo.getProfileImages().add(imageBO);
		}		
		
		return bo;
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.cancelAttendance(eventId, userId);
		return resultBO;
	}

	@Override
	public EmailInfoBO getEmailInfo(String email) throws IntegrationException {
		EmailInfoBO result = integrationFacade.getEmailInfo(email);
		return result;
	}

	@Override
	public List<FeedbackEnum> getUserFeedbacks(int userIdInt) throws IntegrationException {
		List<FeedbackEnum> result = integrationFacade.getUserFeedbacks(userIdInt);
		return result;
	}

	@Override
	public ProfileImageBO readProfileImage(int userId) throws IntegrationException {
		ProfileImageBO result = integrationFacade.readProfileImage(userId);
		return result;
	}

	@Override
	public UserBO getFacebookUserData(String token, int socialTokenExpires, String deviceId) throws IntegrationException {
		UserBO result = integrationFacade.getFacebookUserData(token, socialTokenExpires, deviceId);
		return result;
	}

	@Override
	public List<AttendanceBO> getAttendancesByUser(int userId) throws IntegrationException {
		List<AttendanceBO> result =integrationFacade.getAttendancesByUser(userId);
		return result;
	}

	@Override
	public AccountBO getUserAccountByFacebookId(long facebookId) throws IntegrationException {
		AccountBO result =integrationFacade.getUserAccountByFacebookId(facebookId);
		return result;
	}

	@Override
	public AccountBO getUserAccountByEmail(String email) throws IntegrationException {
		AccountBO result =integrationFacade.getUserAccountByEmail(email);
		return result;
	}

}
