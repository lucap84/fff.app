package it.upp.test.usecases.rest;

import it.upp.test.rest.EventServiceTest;
import it.upp.test.rest.SecurityServiceTest;
import it.upp.test.secure.ClientDHSecureConfiguration;

public class UC1Test {
	
	public static void main(String[] args) {
		
		ClientDHSecureConfiguration secureConfiguration = new ClientDHSecureConfiguration();
		SecurityServiceTest securityServiceTest = new SecurityServiceTest(null,secureConfiguration);
		String userId = securityServiceTest.registerUserShouldReturnConfirm();
		securityServiceTest.setExecutorId(userId);
		
		EventServiceTest eventServiceTest = new EventServiceTest();
		eventServiceTest.setExecutorId(userId);
		eventServiceTest.setSecureConfiguration(secureConfiguration);
		
		eventServiceTest.getEventShouldReturnOneEvent();
	}

}
