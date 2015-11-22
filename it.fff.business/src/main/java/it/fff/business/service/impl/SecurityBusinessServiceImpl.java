package it.fff.business.service.impl;

import java.util.Date;
import java.util.Map;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.service.SecurityBusinessService;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class SecurityBusinessServiceImpl implements SecurityBusinessService{

	private PersistenceServiceFacade persistenceFacade;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	@Override
	public UpdateResultBO login(SessionBO session) throws PersistenceException {
		//Inizializzo le validita dell'account
		session.setLogged(true);
		//imposto la data login della prima sessione
		String loginDate = DHSecureConfiguration.DATE_FORMATTER.format(new Date());
		session.setDataLogin(loginDate);		
		UpdateResultBO bo = persistenceFacade.login(session);
		return bo;
	}

	@Override
	public UpdateResultBO updatePassword(String email, String encodedPassword) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.updatePassword(email, encodedPassword);
		return bo;
	}

	@Override
	public UpdateResultBO checkVerificationCode(String email, String verificationcode) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.checkVerificationCode(email, verificationcode);
		return bo;
	}

	@Override
	public UpdateResultBO generateAndVerificationCode(String email) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.generateVerficationCode(email);
		//TODO send email dopo esito generazione
		return bo;
	}

	@Override
	public UpdateResultBO logout(int userId,String deviceId) throws PersistenceException {
		UpdateResultBO bo = persistenceFacade.logout(userId, deviceId);
		return bo;
	}

	@Override
	public Map<String, Map<String, String>> retrieveClientSecrets() throws PersistenceException {
		Map<String, Map<String, String>> secrets = persistenceFacade.retrieveClientSecrets();
		return secrets;
	}	
	
}
