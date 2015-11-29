package it.fff.business.service.secure;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.clientserver.common.secure.DHSecureConfiguration;

public class ServerSecureConfiguration implements DHSecureConfiguration {
	
	private Map<String,Map<String, String>> clientSecrets;
	public static SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private BusinessServiceFacade businessServiceFacade;

	public ServerSecureConfiguration(){
		this.clientSecrets = new HashMap<String,Map<String,String>>();
	}
	
	@Override
	public void storeSharedKey(String userId, String deviceId, String sharedKey) {
		Map<String, String> clientSharedSecrets = clientSecrets.get(userId);
		if (clientSharedSecrets==null){
			clientSharedSecrets = new HashMap<String, String>();
			this.clientSecrets.put(userId, clientSharedSecrets);
		}
		clientSharedSecrets.put(deviceId, sharedKey);
	}

	@Override
	public String retrieveSharedKey(String userId, String deviceId) {
		if(this.clientSecrets==null || this.clientSecrets.isEmpty()){
			this.populateClientSecrets();
		}
		Map<String, String> device2sharedKey = this.clientSecrets.get(userId);
		if(device2sharedKey==null){
			return "";
		}
		return device2sharedKey.get(deviceId);
	}

	public void populateClientSecrets() {
		try {
			this.clientSecrets = businessServiceFacade.retrieveClientSecrets();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeSharedKey(String userId, String deviceId) {
		Map<String, String> device2sharedKey = this.clientSecrets.get(userId);
		if(device2sharedKey!=null){
			device2sharedKey.remove(deviceId);
		}
		
	}

	public BusinessServiceFacade getBusinessServiceFacade() {
		return businessServiceFacade;
	}

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}
	

}
