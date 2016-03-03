package it.fff.external.util;

import it.fff.business.common.util.ApplicationContextProvider;
import it.fff.external.service.ExternalService;

public class ExternalServiceProvider {
	
	public static ExternalService getExternalService(String beanName) {
		ExternalService service = (ExternalService)ApplicationContextProvider.
				getApplicationContext().
				getBean(beanName);
		return service;
	}
}
