package it.fff.business.service.filter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.fff.business.service.secure.ServerSecureConfiguration;
import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.fff.clientserver.common.secure.DHSecureConfiguration;


//Pre-matching filters are request filters that are executed before the request matching is started
@PreMatching
public class AuthorizationContainerRequestFilter implements ContainerRequestFilter {
	
	@Autowired
	private DHSecureConfiguration secureConfiguration;
	
	private static final Logger logger = LogManager.getLogger(AuthorizationContainerRequestFilter.class);
	private static final int MAX_REQUEST_DELAY_SEC = 60;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.debug(">AuthorizationContainerRequestFilter");
		boolean authorized = true;
		String requestPath = requestContext.getUriInfo().getPath();
		
		if(!requestPath.matches("^security/.*")){ //tutti i path tranne quelli di security vanno controllati
			logger.debug("request path to verify: "+requestPath);
			String authHeader = requestContext.getHeaders().getFirst("Authorization");
			String dateHeader = requestContext.getHeaders().getFirst("Date");
			String deviceHeader = requestContext.getHeaders().getFirst("Device");
			if(authHeader!=null && dateHeader!=null && deviceHeader!=null){
				
				if(!isRequestTimingAcceptable(dateHeader)){
					authorized &= false; //se la richiesta e' troppo vecchia non si viene autorizzati
				}
				
				String method = requestContext.getMethod();
				String userId = authHeader.split(":")[1];
				String nonce = authHeader.split(":")[2];
				String sharedKey = secureConfiguration.retrieveSharedKey(userId, deviceHeader);
				String rebuiltAuthHeader = "";
				if(sharedKey!=null && !"".equals(sharedKey)){
					rebuiltAuthHeader = AuthenticationUtil.generateHMACAuthorizationHeader(sharedKey, userId, method, requestPath, dateHeader, nonce);
				}
				authorized &= rebuiltAuthHeader.equals(authHeader);	//se il digest HMAC ricalcolato sul server non corrisponde al client, non si viene autorizzati
			}
			else{
				authorized &= false; // se non sono presenti gli header HMAC non si viene autorizzati
			}

		}
		
		if(!authorized){
			logger.error("request not authorized!");
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
		}
		else{
			logger.debug("request successfully verified!");
		}		
		logger.debug("<AuthorizationContainerRequestFilter");
	}

	private boolean isRequestTimingAcceptable(String dateHeader) {
		Date clientDate = null;
		try {
			clientDate = DHSecureConfiguration.DATE_FORMATTER.parse(dateHeader);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date serverDate = new Date();
		long diff = serverDate.getTime() - clientDate.getTime();
		long diffSeconds = diff / 1000 % 60;
		
		return diffSeconds<MAX_REQUEST_DELAY_SEC;
	}

}