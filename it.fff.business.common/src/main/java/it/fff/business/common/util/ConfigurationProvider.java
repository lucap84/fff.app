package it.fff.business.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigurationProvider {
	
	private static ConfigurationProvider configurationProvider;
	private Properties authConfigProperties;
	private Properties mailConfigProperties;
	private Properties imagesConfigProperties;
	
	String mailConfigPropertiesFileName;
	String authConfigPropertiesFileName;
	String imagesPropertiesFileName;
	
	private ConfigurationProvider(){
		 authConfigProperties = new Properties();
		 authConfigPropertiesFileName = Constants.AUTH_CONF_FILENAME;
		 mailConfigProperties = new Properties();
		 mailConfigPropertiesFileName = Constants.MAIL_CONF_FILENAME;
		 imagesConfigProperties = new Properties();
		 imagesPropertiesFileName = Constants.IMAGE_CONF_FILENAME;
		 try {
			this.loadConfigurationFromFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigurationProvider getInstance(){
		if(configurationProvider==null){
			configurationProvider = new ConfigurationProvider();
		}
		return configurationProvider;
	}
	
	private void loadConfigurationFromFile() throws IOException{
		InputStream authInputStream = getClass().getClassLoader().getResourceAsStream(this.authConfigPropertiesFileName);
		InputStream mailInputStream = getClass().getClassLoader().getResourceAsStream(this.mailConfigPropertiesFileName);
		InputStream persistenceInputStream = getClass().getClassLoader().getResourceAsStream(this.mailConfigPropertiesFileName);
		
		if (authInputStream != null) {
			authConfigProperties.load(authInputStream);
		} else {
			throw new FileNotFoundException("property file '" + authConfigPropertiesFileName + "' not found in the classpath");
		}		
		
		if (mailInputStream != null) {
			mailConfigProperties.load(mailInputStream);
		} else {
			throw new FileNotFoundException("property file '" + mailConfigPropertiesFileName + "' not found in the classpath");
		}

		if (persistenceInputStream != null) {
			imagesConfigProperties.load(persistenceInputStream);
		} else {
			throw new FileNotFoundException("property file '" + imagesPropertiesFileName + "' not found in the classpath");
		}		
		
	}
	
	public String getAuthProperty(String propertyName){
		return authConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getAuthProperties(){
		return this.authConfigProperties;
	}
	
	public String getEmailProperty(String propertyName){
		return mailConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getEmailProperties(){
		return this.mailConfigProperties;
	}	
	
	public String getImageConfigProperty(String propertyName){
		return imagesConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getImageConfigProperties(){
		return this.imagesConfigProperties;
	}	
	

}