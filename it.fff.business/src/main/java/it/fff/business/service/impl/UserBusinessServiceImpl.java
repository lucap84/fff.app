package it.fff.business.service.impl;


import java.util.Date;

import javax.activation.MailcapCommandMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.util.Constants;
import it.fff.business.notification.MailManager;
import it.fff.business.service.UserBusinessService;
import it.fff.business.strategy.ImageValidationStrategy;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.integration.facade.exception.PersistenceException;
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
	public WriteResultBO createUser(UserBO userBO) throws PersistenceException {
		logger.info("createUser start...");
		//Inizializzo le validita dell'account
		AccountBO creatingAccount = userBO.getAccount();
		creatingAccount.setFlgValidita(true);
		//imposto l'utente come loggato appena creato l'account
		SessionBO firstSession = creatingAccount.getSessions().get(0);
		firstSession.setLogged(true);
		//imposto la data login della prima sessione
		String loginDate = Constants.DATE_FORMATTER.format(new Date());
		firstSession.setDataLogin(loginDate);
		
		WriteResultBO resultBO = integrationFacade.registerUser(userBO);
		logger.info("...createUser persistence done");
		
		//Se registrazione avvenuta con successo invio email di conferma
		boolean isSent = false;
		if(resultBO!=null && resultBO.isSuccess()){
			String nomeUtente = userBO.getNome();
			String mailUtente = userBO.getAccount().getEmail();
			
			isSent  = MailManager.getInstance().sendRegistrationConfirmMail(mailUtente, nomeUtente);

			if(!isSent){
				throw new PersistenceException("Invio mail verification code non riuscito", null);
			}		
			logger.info("...createUser mail sent");
		}
		
		return resultBO;
	}


	@Override
	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws PersistenceException {
		logger.info("updateProfileImage start...");
		
		logger.debug("image validation...");
		if(!imageValidationStrategy.isValid(imgBO)){
			logger.debug("image is not valid, resizing...");
			imgBO = imageValidationStrategy.validate(imgBO);
		}
		logger.debug("...image validation successfully finished");
		
		ProfileImageBO bo = integrationFacade.updateProfileImage(imgBO);
		
		logger.info("...updateProfileImage end");
		return bo;
	}
	

	@Override
	public WriteResultBO updateUserData(UserBO userBO) throws PersistenceException {
		WriteResultBO resultBO = integrationFacade.updateUserData(userBO);
		return resultBO;
	}


	@Override
	public UserBO getUser(int userId) throws PersistenceException {
		UserBO bo = integrationFacade.getUser(userId);
		return bo;
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws PersistenceException {
		WriteResultBO resultBO = integrationFacade.cancelAttendance(eventId, userId);
		return resultBO;
	}

	@Override
	public EmailInfoBO isExistingEmail(String email) throws PersistenceException {
		EmailInfoBO result = integrationFacade.isExistingEmail(email);
		return result;
	}

}
