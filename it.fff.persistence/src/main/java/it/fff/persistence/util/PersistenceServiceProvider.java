package it.fff.persistence.util;


import it.fff.business.common.util.ApplicationContextProvider;
import it.fff.persistence.service.PersistenceService;

public class PersistenceServiceProvider {

	public static PersistenceService getPersistenceService(String beanName) {
		PersistenceService service = (PersistenceService)ApplicationContextProvider.
				getApplicationContext().
				getBean(beanName);
		return service;
	}
	
}
