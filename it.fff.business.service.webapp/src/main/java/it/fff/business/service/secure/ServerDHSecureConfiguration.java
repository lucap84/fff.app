package it.fff.business.service.secure;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import it.fff.clientserver.common.secure.DHSecureConfiguration;

public class ServerDHSecureConfiguration implements DHSecureConfiguration {
	
	private Map<String,String> clientSecrets;
	public static SecureRandom SECURE_RANDOM = new SecureRandom();	

	public ServerDHSecureConfiguration(){
		this.clientSecrets = new HashMap<String,String>();
	}
	
	@Override
	public void storeSharedKey(String userId, String sharedKey) {
		this.clientSecrets.put(userId, sharedKey);
	}

	@Override
	public String retrieveSharedKey(String userId) {
		return this.clientSecrets.get(userId);
	}

}
