package it.fff.business.util;

import it.fff.business.common.util.ApplicationContextProvider;
import it.fff.business.service.BusinessService;

public class BusinessServiceProvider {

	public static BusinessService getBusinessService(String beanName) {
		BusinessService businessService = (BusinessService)ApplicationContextProvider.
				getApplicationContext().
				getBean(beanName);
		
		return businessService;
		
	}
	
	

}
