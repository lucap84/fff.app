package it.upp.test.secure;

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

import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.upp.test.util.ClientConstants;

public class ClientSecureConfiguration implements DHSecureConfiguration {

	public static SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private String userId;
	private String deviceId;
	private String sharedKey;
	
	public ClientSecureConfiguration(){
		readFromFile();
	}
	
	@Override
	public void removeSharedKey(String userId, String deviceId) {
		storeProperties(userId, deviceId, "");
	}

	@Override
	public void storeSharedKey(String userId, String deviceId, String sharedKey) {
		storeProperties(userId, deviceId, sharedKey);
	}
	
	public void storeProperties(String userId, String deviceId, String sharedKey) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

//			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(ClientConstants.SECURE_CONF_PATH+ClientConstants.SECURE_CONF_FILENAME);
			if(!file.exists()) {
				file = this.createNewSecureFile();
			}
			output = new FileOutputStream(file);

			this.userId = userId;
			this.deviceId = deviceId;
			this.sharedKey = sharedKey;

			// set the properties value
			prop.setProperty(ClientConstants.PROP_SECURE_USER, userId);
			prop.setProperty(ClientConstants.PROP_SECURE_DEVICE, deviceId);
			prop.setProperty(ClientConstants.PROP_SECURE_SHAREDKEY, sharedKey);

			// save properties to project root folder
			prop.store(output, null);
			

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}			
	}
	


	private File createNewSecureFile() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(ClientConstants.SECURE_CONF_PATH+ClientConstants.SECURE_CONF_FILENAME, "UTF-8");
		writer.println(ClientConstants.PROP_SECURE_USER+"=");
		writer.println(ClientConstants.PROP_SECURE_DEVICE+"=");
		writer.println(ClientConstants.PROP_SECURE_SHAREDKEY+"=");
		writer.close();
		return new File(ClientConstants.SECURE_CONF_PATH+ClientConstants.SECURE_CONF_FILENAME);
		
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

	
	private void readFromFile(){
		Properties prop = new Properties();
		InputStream input = null;

		try {

//			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(ClientConstants.SECURE_CONF_PATH+ClientConstants.SECURE_CONF_FILENAME);
			if(!file.exists()) {
				file = this.createNewSecureFile();
			}
			input = new FileInputStream(file);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.userId = prop.getProperty(ClientConstants.PROP_SECURE_USER);
			this.deviceId = prop.getProperty(ClientConstants.PROP_SECURE_DEVICE);
			this.sharedKey = prop.getProperty(ClientConstants.PROP_SECURE_SHAREDKEY);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}



}
