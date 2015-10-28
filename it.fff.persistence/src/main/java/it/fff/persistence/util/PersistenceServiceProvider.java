package it.fff.persistence.util;

import it.fff.business.common.util.ApplicationContextProvider;
import it.fff.persistence.service.PersistenceService;

public class PersistenceServiceProvider {

	public static PersistenceService getBusinessService(String beanName) {
		PersistenceService persistenceService = (PersistenceService)ApplicationContextProvider.
				getApplicationContext().
				getBean(beanName);
		
		return persistenceService;
		
	}
	
}
