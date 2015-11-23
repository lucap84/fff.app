package it.fff.client.filter;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.service.filter.AuthorizationContainerRequestFilter;
import it.fff.client.secure.ClientSecureConfiguration;
import it.fff.client.secure.SecureConfigurationFactory;
import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.fff.clientserver.common.secure.DHSecureConfiguration;

public class AuthorizationClientRequestFilter implements ClientRequestFilter {

	private static final Logger logger = LogManager.getLogger(AuthorizationClientRequestFilter.class);
	
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {

		String requestPath = requestContext.getUri().getPath();
		int indexOf = requestPath.indexOf("restapi/");
		indexOf = indexOf +"restapi/".length();
		requestPath = requestPath.substring(indexOf);
		
		String httpMethod = requestContext.getMethod();
		
		String formattedDate = DHSecureConfiguration.DATE_FORMATTER.format(new Date()); 
		ClientSecureConfiguration secureConfigurationInstance = SecureConfigurationFactory.getSecureConfigurationInstance();
		String deviceId = secureConfigurationInstance.getDeviceId();
		
		requestContext.getHeaders().add("Date", formattedDate);
		requestContext.getHeaders().add("Device", deviceId);		
		
		if(!requestPath.matches("^security/.*")){ //tutti i servizi che  non sono security
			String nonce = new BigInteger(32,ClientSecureConfiguration.SECURE_RANDOM).toString();
			String userId = secureConfigurationInstance.getUserId();
			String authorizationHeader = AuthenticationUtil.generateHMACAuthorizationHeader(secureConfigurationInstance.retrieveSharedKey(userId, deviceId), userId, httpMethod, requestPath, formattedDate, nonce);
			requestContext.getHeaders().add("Authorization", authorizationHeader);
		}
		
	}
	

}
