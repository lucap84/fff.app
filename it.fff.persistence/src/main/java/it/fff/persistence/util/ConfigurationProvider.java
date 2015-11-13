package it.fff.persistence.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProvider {
	
	private static ConfigurationProvider configurationProvider;
	private Properties configurationProperties;
	String configurationPropertiesFileName;
	
	private ConfigurationProvider(){
		 configurationProperties = new Properties();
		 configurationPropertiesFileName = Constants.PERSISTENCE_CONF_FILENAME;
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
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.configurationPropertiesFileName);
		 
		if (inputStream != null) {
			configurationProperties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + configurationPropertiesFileName + "' not found in the classpath");
		}
	}
	
	public String getProperty(String propertyName){
		return configurationProperties.getProperty(propertyName);	
	}
	
	

}
