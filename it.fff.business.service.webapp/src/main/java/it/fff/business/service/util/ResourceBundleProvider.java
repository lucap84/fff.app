package it.fff.business.service.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceBundleProvider {
	
	private static final Logger logger = LogManager.getLogger(ResourceBundleProvider.class);
	
	private static ResourceBundleProvider provider;
	private String bundleBaseName = "ApplicationMessages";
	private String country_language_default = "default";
	private Map<String, ResourceBundle> countryLanguage2ResourceBundleCache;
	
	private ResourceBundleProvider(){
		this.countryLanguage2ResourceBundleCache = new HashMap<String, ResourceBundle>();
		ResourceBundle bundle = ResourceBundle.getBundle(bundleBaseName);
		countryLanguage2ResourceBundleCache.put(country_language_default, bundle);
	}
	
	public static ResourceBundleProvider getInstance(){
		if(provider==null){
			provider = new ResourceBundleProvider();
		}
		
		return provider;
	}
	
	public ResourceBundle getResourceBundle(){
		return countryLanguage2ResourceBundleCache.get(country_language_default);
	}
	
	public ResourceBundle getResourceBundle(Locale locale){
		if(locale==null){
			locale = Locale.getDefault();
			logger.error("Locale non trovato, utilizzo quello di default");
		}
		return this.getResourceBundle(locale.getCountry(), locale.getLanguage());
	}
	
	public ResourceBundle getResourceBundle(String country, String language){
		StringBuilder country_language = new StringBuilder();
		if(!"".equals(country)){
			country_language.append(country);
		}
		if(!"".equals(language)){
			if(!"".equals(country_language.toString())){
				country_language.append("_");
			}
			country_language.append(language);
		}
		
		ResourceBundle resourceBundle = countryLanguage2ResourceBundleCache.get(country_language.toString());
		if(resourceBundle==null){//Se il resourceBundle non è nella cache map
			try{//Provo a recuperarlo da file system
				Locale locale = new Locale(language, country);
				resourceBundle = ResourceBundle.getBundle(bundleBaseName,locale);
				if(resourceBundle!=null){ //Se esiste su file system lo carico in cache
					countryLanguage2ResourceBundleCache.put(country_language.toString(), resourceBundle);
				}
				else{//Altrimenti ritorno il resource bundle precaricato di default
					resourceBundle = getResourceBundle();
				}
			}
			catch(MissingResourceException e){
				logger.error(e);
				return countryLanguage2ResourceBundleCache.get(country_language_default);
			}
		}
		
		return resourceBundle;
	}

}
