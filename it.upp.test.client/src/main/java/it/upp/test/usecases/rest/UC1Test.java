package it.upp.test.usecases.rest;

import it.upp.test.rest.EventServiceTest;
import it.upp.test.rest.SecurityServiceTest;
import it.upp.test.secure.ClientSecureConfiguration;

public class UC1Test {
	
	public static void main(String[] args) {
		
//		ClientSecureConfiguration secureConfiguration = new ClientSecureConfiguration();
		SecurityServiceTest securityServiceTest = new SecurityServiceTest();
//		securityServiceTest.setSecureConfiguration(secureConfiguration);
		securityServiceTest.registerUserShouldReturnConfirm();
		
		EventServiceTest eventServiceTest = new EventServiceTest();
//		eventServiceTest.setSecureConfiguration(securityServiceTest.getSecureConfiguration());
		
		eventServiceTest.getEventShouldReturnOneEvent();
		
		securityServiceTest.logoutShouldReturnConfirm();
		
		eventServiceTest.getEventShouldReturnOneEvent();
	}

}
