package it.upp.test.secure;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Map;

import it.fff.clientserver.common.secure.DHSecureConfiguration;

public class ClientDHSecureConfiguration implements DHSecureConfiguration {

	public static SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private Map.Entry<String,String> clientSecretEntry;
	
	public ClientDHSecureConfiguration(){
		this.clientSecretEntry = null;
	}
	
	@Override
	public void storeSharedKey(String userId, String sharedKey) {
		if(clientSecretEntry==null){
			clientSecretEntry = new AbstractMap.SimpleEntry<String, String>(userId, sharedKey);
		}
		else{
			this.clientSecretEntry.setValue(sharedKey);
		}
	}

	@Override
	public String retrieveSharedKey(String userId) {
		if(clientSecretEntry==null){
			return "";
		}
		return this.clientSecretEntry.getValue();
	}

}
