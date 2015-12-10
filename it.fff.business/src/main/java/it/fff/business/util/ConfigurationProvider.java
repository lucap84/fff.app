package it.fff.business.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProvider {
	
	private static ConfigurationProvider configurationProvider;
	private Properties mailConfigProperties;
	String mailConfigPropertiesFileName;
	
	private ConfigurationProvider(){
		 mailConfigProperties = new Properties();
		 mailConfigPropertiesFileName = Constants.BUSINESS_MAIL_CONF_FILENAME;
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
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.mailConfigPropertiesFileName);
		 
		if (inputStream != null) {
			mailConfigProperties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + mailConfigPropertiesFileName + "' not found in the classpath");
		}
	}
	
	public String getProperty(String propertyName){
		return mailConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getEmailProperties(){
		return this.mailConfigProperties;
	}
	
	

}
