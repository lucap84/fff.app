package it.fff.business.common.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.clientserver.common.util.Constants;


public class ConfigurationProvider {
	
	private static final Logger logger = LogManager.getLogger(ConfigurationProvider.class);
	
	private static ConfigurationProvider configurationProvider;
	private Properties serverSecurityConfigProperties;
	private Properties mailConfigProperties;
	private Properties imagesConfigProperties;
	private Properties placesConfigProperties;
	private Properties facebookConfigProperties;
	private Properties jdbcConfigProperties;
	
	String mailConfigPropertiesFileName;
	String serverSecurityConfigPropertiesFileName;
	String imagesPropertiesFileName;
	String placesPropertiesFileName;
	String facebookPropertiesFileName;
	String jdbcPropertiesFileName;
	
	private ConfigurationProvider(){
		 serverSecurityConfigProperties = new Properties();
		 serverSecurityConfigPropertiesFileName = Constants.FILENAME_CONFIG_SERVERSECURITY;
		 mailConfigProperties = new Properties();
		 mailConfigPropertiesFileName = Constants.FILENAME_CONFIG_MAIL;
		 imagesConfigProperties = new Properties();
		 imagesPropertiesFileName = Constants.FILENAME_CONFIG_IMAGE;
		 placesConfigProperties = new Properties();
		 placesPropertiesFileName = Constants.FILENAME_CONFIG_PLACES;
		facebookConfigProperties = new Properties();
		facebookPropertiesFileName = Constants.FILENAME_CONFIG_FACEBOOK;
		jdbcConfigProperties = new Properties();
		jdbcPropertiesFileName = Constants.FILENAME_CONFIG_JDBC;
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
		InputStream serverSecurityInputStream = getClass().getClassLoader().getResourceAsStream(this.serverSecurityConfigPropertiesFileName);
		InputStream mailInputStream = getClass().getClassLoader().getResourceAsStream(this.mailConfigPropertiesFileName);
		InputStream imagesInputStream = getClass().getClassLoader().getResourceAsStream(this.imagesPropertiesFileName);
		InputStream placesInputStream = getClass().getClassLoader().getResourceAsStream(this.placesPropertiesFileName);
		InputStream facebookInputStream = getClass().getClassLoader().getResourceAsStream(this.facebookPropertiesFileName);
		InputStream jdbcInputStream = getClass().getClassLoader().getResourceAsStream(this.jdbcPropertiesFileName);
		
		if (serverSecurityInputStream != null) {
			serverSecurityConfigProperties.load(serverSecurityInputStream);
			logger.debug(this.serverSecurityConfigPropertiesFileName+" loaded from file");
		} else {
			logger.error("error during loading file: "+this.serverSecurityConfigPropertiesFileName);
			throw new FileNotFoundException("property file '" + serverSecurityConfigPropertiesFileName + "' not found in the classpath");
		}		
		
		if (mailInputStream != null) {
			mailConfigProperties.load(mailInputStream);
			logger.debug(this.mailConfigPropertiesFileName+" loaded from file");
		} else {
			logger.error("error during loading file: "+this.mailConfigPropertiesFileName);
			throw new FileNotFoundException("property file '" + mailConfigPropertiesFileName + "' not found in the classpath");
		}

		if (imagesInputStream != null) {
			imagesConfigProperties.load(imagesInputStream);
			logger.debug(this.imagesPropertiesFileName+" loaded from file");
		} else {
			logger.error("error during loading file: "+this.imagesPropertiesFileName);
			throw new FileNotFoundException("property file '" + imagesPropertiesFileName + "' not found in the classpath");
		}
		
		if (placesInputStream != null) {
			placesConfigProperties.load(placesInputStream);
			logger.debug(this.placesPropertiesFileName+" loaded from file");
		} else {
			logger.error("error during loading file: "+this.placesPropertiesFileName);
			throw new FileNotFoundException("property file '" + placesPropertiesFileName + "' not found in the classpath");
		}		
		
		if (facebookInputStream != null) {
			facebookConfigProperties.load(facebookInputStream);
			logger.debug(this.facebookPropertiesFileName+" loaded from file");
		} else {
			logger.error("error during loading file: "+this.facebookPropertiesFileName);
			throw new FileNotFoundException("property file '" + facebookPropertiesFileName + "' not found in the classpath");
		}

		if (jdbcInputStream != null) {
			jdbcConfigProperties.load(jdbcInputStream);
			logger.debug(this.jdbcPropertiesFileName+" loaded from file");
		} else {
			logger.error("error during loading file: "+this.jdbcPropertiesFileName);
			throw new FileNotFoundException("property file '" + jdbcPropertiesFileName + "' not found in the classpath");
		}
	}
	
	public String loadStringFromFile(String fileName){
		logger.debug("Loading file: "+fileName);
		if(fileName==null){
			logger.error("Nessun file specificato");
			return null;
		}
		
		if(!SystemUtils.IS_OS_WINDOWS){
			logger.debug("We are running in NON-Windows environment: "+SystemUtils.OS_NAME+"("+SystemUtils.OS_VERSION+")");
			fileName = fileName.replace("\\", "/");	
			logger.debug("Backslashes changed in slashes ("+fileName+")");
		}
		
		InputStream fis = getClass().getClassLoader().getResourceAsStream(fileName);
		
		if(fis==null){
			logger.error("File '"+fileName+"' non caricato");
			return null;
		}
		String stringFromInputStream = getStringFromInputStream(fis);
		return stringFromInputStream;
	}
	
	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	
	public String getServerSecurityProperty(String propertyName){
		return serverSecurityConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getServerSecurityProperties(){
		return this.serverSecurityConfigProperties;
	}
	
	public String getEmailProperty(String propertyName){
		return mailConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getEmailProperties(){return this.mailConfigProperties;}
	
	public String getImageConfigProperty(String propertyName){
		return imagesConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getImageConfigProperties(){
		return this.imagesConfigProperties;
	}	
	
	public String getPlacesConfigProperty(String propertyName){
		return placesConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getPlacesConfigProperties(){
		return this.placesConfigProperties;
	}
	
	public String getFacebookConfigProperty(String propertyName){
		return facebookConfigProperties.getProperty(propertyName);	
	}
	
	public Properties getFacebookConfigProperties(){
		return this.facebookConfigProperties;
	}

	public String getJdbcConfigProperty(String propertyName){
		return jdbcConfigProperties.getProperty(propertyName);
	}

	public Properties getJdbcConfigProperties(){
		return this.jdbcConfigProperties;
	}
}
