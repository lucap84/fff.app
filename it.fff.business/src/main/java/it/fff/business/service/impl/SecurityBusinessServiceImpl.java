package it.fff.business.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.util.Constants;
import it.fff.business.notification.MailManager;
import it.fff.business.service.SecurityBusinessService;
import it.fff.business.strategy.VerificationCodeStrategy;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class SecurityBusinessServiceImpl implements SecurityBusinessService{
	
	private static final Logger logger = LogManager.getLogger(SecurityBusinessServiceImpl.class);

	private PersistenceServiceFacade persistenceFacade;
	private VerificationCodeStrategy verificationCodeStrategy;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	public VerificationCodeStrategy getVerificationCodeStrategy() {
		return verificationCodeStrategy;
	}

	public void setVerificationCodeStrategy(VerificationCodeStrategy verificationCodeStrategy) {
		this.verificationCodeStrategy = verificationCodeStrategy;
	}

	@Override
	public WriteResultBO login(SessionBO session) throws PersistenceException {
		//Inizializzo le validita dell'account
		session.setLogged(true);
		//imposto la data login della prima sessione
		String loginDate = Constants.DATE_FORMATTER.format(new Date());
		session.setDataLogin(loginDate);		
		WriteResultBO bo = persistenceFacade.login(session);
		return bo;
	}

	@Override
	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws PersistenceException {
		WriteResultBO bo = persistenceFacade.updatePassword(userId, email, encodedOldPassword, encodedNewPassword);
		return bo;
	}

	@Override
	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		WriteResultBO bo = persistenceFacade.checkVerificationCode(email, verificationcode);
		return bo;
	}

	@Override
	public WriteResultBO generateAndSendVerficationCode(String email) throws PersistenceException {
		
		String verificationCode = verificationCodeStrategy.generateVerificationCode(email);

		WriteResultBO bo = persistenceFacade.saveVerficationCode(email, verificationCode);

		boolean isSent = false;
		if(bo.isSuccess()){
			//TODO send email dopo esito generazione e scrittura su db
			MailManager mailManager = MailManager.getInstance();
			isSent = mailManager.sendVerificationCodeMail(email, verificationCode);
		}
		
		if(!isSent){
			throw new PersistenceException("Invio mail verification code non riuscito", null);
		}
		
		return bo;
	}

	@Override
	public WriteResultBO logout(int userId,String deviceId) throws PersistenceException {
		WriteResultBO bo = persistenceFacade.logout(userId, deviceId);
		return bo;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws PersistenceException {
		Map<Integer, Map<String, String>> secrets = persistenceFacade.retrieveClientSecrets();
		return secrets;
	}

	@Override
	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws PersistenceException {
		WriteResultBO bo = persistenceFacade.resetPassword(email, newPassword, verificationCode);
		return bo;
	}	
	
}
