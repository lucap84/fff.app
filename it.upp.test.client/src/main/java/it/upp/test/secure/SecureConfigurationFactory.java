package it.upp.test.secure;

public class SecureConfigurationFactory {

	private static ClientSecureConfiguration secureConfiguration;
	
	public static ClientSecureConfiguration getSecureConfigurationInstance() {
		if(secureConfiguration==null){
			secureConfiguration = new ClientSecureConfiguration();
		}
		return secureConfiguration;
	}
	

	
}
