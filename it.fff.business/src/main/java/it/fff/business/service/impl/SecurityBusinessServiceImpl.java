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
import it.fff.integration.facade.exception.IntegrationException;
import it.fff.integration.facade.service.IntegrationServiceFacade;

public class SecurityBusinessServiceImpl implements SecurityBusinessService{
	
	private static final Logger logger = LogManager.getLogger(SecurityBusinessServiceImpl.class);

	private IntegrationServiceFacade integrationFacade;
	private VerificationCodeStrategy verificationCodeStrategy;

	public IntegrationServiceFacade getIntegrationFacade() {
		return integrationFacade;
	}

	public void setIntegrationFacade(IntegrationServiceFacade integrationFacade) {
		this.integrationFacade = integrationFacade;
	}

	public VerificationCodeStrategy getVerificationCodeStrategy() {
		return verificationCodeStrategy;
	}

	public void setVerificationCodeStrategy(VerificationCodeStrategy verificationCodeStrategy) {
		this.verificationCodeStrategy = verificationCodeStrategy;
	}

	@Override
	public WriteResultBO login(SessionBO session) throws IntegrationException {
		//Inizializzo le validita dell'account
		session.setLogged(true);
		//imposto la data login della prima sessione
		String loginDate = Constants.DATE_FORMATTER.format(new Date());
		session.setDataLogin(loginDate);		
		WriteResultBO bo = integrationFacade.login(session);
		return bo;
	}

	@Override
	public WriteResultBO updatePassword(int userId, String email, String encodedOldPassword, String encodedNewPassword) throws IntegrationException {
		WriteResultBO bo = integrationFacade.updatePassword(userId, email, encodedOldPassword, encodedNewPassword);
		return bo;
	}

	@Override
	public WriteResultBO checkVerificationCode(String email, String verificationcode) throws IntegrationException {
		WriteResultBO bo = integrationFacade.checkVerificationCode(email, verificationcode);
		return bo;
	}

	@Override
	public WriteResultBO generateAndSendVerficationCode(String email) throws IntegrationException {
		
		String verificationCode = verificationCodeStrategy.generateVerificationCode(email);

		WriteResultBO bo = integrationFacade.saveVerficationCode(email, verificationCode);
		logger.info("...verification code persistence done");
		
		boolean isSent = false;
		if(bo!=null && bo.isSuccess()){
			//TODO send email dopo esito generazione e scrittura su db
			MailManager mailManager = MailManager.getInstance();
			isSent = mailManager.sendVerificationCodeMail(email, verificationCode);

			if(!isSent){
				throw new IntegrationException("Invio mail verification code non riuscito", null);
			}
			logger.info("...verification code mail sent");
		}
		
		return bo;
	}

	@Override
	public WriteResultBO logout(int userId,String deviceId) throws IntegrationException {
		WriteResultBO bo = integrationFacade.logout(userId, deviceId);
		return bo;
	}

	@Override
	public Map<Integer, Map<String, String>> retrieveClientSecrets() throws IntegrationException {
		Map<Integer, Map<String, String>> secrets = integrationFacade.retrieveClientSecrets();
		return secrets;
	}

	@Override
	public WriteResultBO resetPassword(String email, String newPassword, String verificationCode) throws IntegrationException {
		WriteResultBO bo = integrationFacade.resetPassword(email, newPassword, verificationCode);
		return bo;
	}	
	
}
