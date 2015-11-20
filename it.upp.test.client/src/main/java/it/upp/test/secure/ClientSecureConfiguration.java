package it.upp.test.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Properties;

import it.fff.clientserver.common.secure.DHSecureConfiguration;

public class ClientSecureConfiguration implements DHSecureConfiguration {

	public static final String SECURE_PROPERTIES_FILENAME = "\\secure.properties";
	public static SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private String userId;
	private String deviceId;
	private String sharedKey;
	
	public ClientSecureConfiguration(){
		readFromFile();
	}
	
	@Override
	public void storeSharedKey(String userId, String deviceId, String sharedKey) {
		
		Properties prop = new Properties();
		OutputStream output = null;

		try {

//			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(SECURE_PROPERTIES_FILENAME);
			output = new FileOutputStream(file);

			// set the properties value
			prop.setProperty("user.id", userId);
			prop.setProperty("device.id", deviceId);
			prop.setProperty("shared.key", sharedKey);

			// save properties to project root folder
			prop.store(output, null);
			
			this.userId = userId;
			this.deviceId = deviceId;
			this.sharedKey = sharedKey;

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
			File file = new File(SECURE_PROPERTIES_FILENAME);
			input = new FileInputStream(file);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.userId = prop.getProperty("user.id");
			this.deviceId = prop.getProperty("device.id");
			this.sharedKey = prop.getProperty("shared.key");

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

	@Override
	public void removeSharedKey(String userId, String deviceId) {
		storeSharedKey("", deviceId, "");
	}

}
