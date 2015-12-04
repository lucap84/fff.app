package it.fff.business.service.impl;

import java.util.Date;
import java.util.Map;

import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.WriteResultBO;
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
	public WriteResultBO login(SessionBO session) throws PersistenceException {
		//Inizializzo le validita dell'account
		session.setLogged(true);
		//imposto la data login della prima sessione
		String loginDate = DHSecureConfiguration.DATE_FORMATTER.format(new Date());
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
	public WriteResultBO generateAndVerificationCode(String email) throws PersistenceException {
		WriteResultBO bo = persistenceFacade.generateVerficationCode(email);
		//TODO send email dopo esito generazione
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
	
}
