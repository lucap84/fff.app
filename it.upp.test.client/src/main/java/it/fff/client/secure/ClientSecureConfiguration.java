package it.fff.client.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Properties;

import it.fff.client.util.ClientConfiguration;
import it.fff.client.util.ClientConstants;
import it.fff.clientserver.common.secure.DHSecureConfiguration;

public class ClientSecureConfiguration implements DHSecureConfiguration {

	private static ClientSecureConfiguration secureConfiguration;
	
	public static SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private String userId;
	private String deviceId;
	private String sharedKey;
	
	private ClientSecureConfiguration(){
		loadSecureConfiguration();
	}
	
	public static ClientSecureConfiguration getInstance() {
		if(secureConfiguration==null){
			secureConfiguration = new ClientSecureConfiguration();
		}
		return secureConfiguration;
	}
	
	@Override
	public void removeSharedKey(String userId, String deviceId) {
		storeProperties(userId, deviceId, "");
	}

	@Override
	public void storeSharedKey(String userId, String deviceId, String sharedKey) {
		storeProperties(userId, deviceId, sharedKey);
	}
	
	private void loadSecureConfiguration(){
		ClientConfiguration clientConfiguration = ClientConfiguration.getInstance();
		Properties secureConfigurationProperties = clientConfiguration.loadSecureConfigurationProperties();
		// get the property value and print it out
		this.userId = secureConfigurationProperties.getProperty(ClientConstants.PROP_SECURE_USER);
		this.deviceId = secureConfigurationProperties.getProperty(ClientConstants.PROP_SECURE_DEVICE);
		this.sharedKey = secureConfigurationProperties.getProperty(ClientConstants.PROP_SECURE_SHAREDKEY);
	}	
	
	public void storeProperties(String userId, String deviceId, String sharedKey) {

		ClientConfiguration clientConfiguration = ClientConfiguration.getInstance();
		clientConfiguration.storeSecureConfigurationProperties(userId, deviceId, sharedKey);
		
		this.userId = userId;
		this.deviceId = deviceId;
		this.sharedKey = sharedKey;

	}
	


	@Override
	public String retrieveSharedKey(String userId, String deviceId) {
		if(this.userId.equals(userId) && this.deviceId.equals(deviceId)){
			return this.sharedKey;
		}
		return "";
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSharedKey() {
		return sharedKey;
	}

	

}
