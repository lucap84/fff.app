package it.fff.business.service.filter;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.fff.clientserver.common.secure.DHSecureConfiguration;


//Pre-matching filters are request filters that are executed before the request matching is started
@PreMatching
public class AuthorizationContainerRequestFilter implements ContainerRequestFilter {
	
	public static Set<Integer> NONCES_SET = new HashSet<Integer>();
	
	@Autowired
	private DHSecureConfiguration secureConfiguration;
	private int expirationDuration;
	private int noncesMaxSetSize;
	
	private static final Logger logger = LogManager.getLogger(AuthorizationContainerRequestFilter.class);
	
	public AuthorizationContainerRequestFilter() {
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		this.expirationDuration = Integer.valueOf(confProvider.getAuthProperty(Constants.PROP_AUTH_DATE_EXPIRATION));
		this.noncesMaxSetSize = Integer.valueOf(confProvider.getAuthProperty(Constants.PROP_AUTH_NONCE_SETSIZE));
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		boolean authorized = true;
		String requestPath = requestContext.getUriInfo().getPath();
		String method = requestContext.getMethod();
		logger.debug(method+": "+requestPath);
		
		String specificError = "";
		if(isToAuthorize(requestPath)){
			String authHeader = requestContext.getHeaders().getFirst("Authorization");
			String dateHeader = requestContext.getHeaders().getFirst("Date");
			String deviceHeader = requestContext.getHeaders().getFirst("Device");
			if(authHeader!=null && dateHeader!=null && deviceHeader!=null){
				
				if(!isRequestTimingAcceptable(dateHeader)){
					authorized &= false; //se la richiesta e' troppo vecchia non si viene autorizzati
					specificError += "Too old request; ";
				}
				
				String userId = authHeader.split(":")[1];
				String nonce = authHeader.split(":")[2];
				
				if(!isNewNonce(nonce)){
					authorized &= false; //se la richiesta e' duplicata non si viene autorizzati
					specificError += "Nonce duplicated; ";
				}				
				
				Integer UserIdInt = null;
				try{
					UserIdInt = Integer.valueOf(userId);
				}
				catch(NumberFormatException e){
					authorized &= false;
					specificError += "UserId not valid; ";
				}
				String sharedKey = secureConfiguration.retrieveSharedKey(UserIdInt, deviceHeader);
				String rebuiltAuthHeader = "";
				if(sharedKey!=null && !"".equals(sharedKey)){
					rebuiltAuthHeader = AuthenticationUtil.generateHMACAuthorizationHeader(sharedKey, UserIdInt, method, requestPath, dateHeader, nonce);
				}
				
				if(!rebuiltAuthHeader.equals(authHeader)){//se il digest HMAC ricalcolato sul server non corrisponde al client, non si viene autorizzati
					authorized &= false;
					specificError += "HMAC digest not valid; ";
				}
			}
			else{
				authorized &= false; // se non sono presenti gli header HMAC non si viene autorizzati
				specificError += "HMAC headers not present; ";
			}

		}
		
		if(!authorized){
			logger.error("NOT authorized: "+specificError);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
		}
		else{
			logger.debug("authorized");
		}		
	}

	private boolean isToAuthorize(String requestPath) {
		boolean isToAuthorize = true;
		//Bisogna sempre procedere con l'autorizzazione, tranne che per registrarsi, per fare login per recuperare la password
		isToAuthorize &= !requestPath.matches("^security/registration.*");
		isToAuthorize &= !requestPath.matches("^security/login.*");
		
		//servizi per reset password
		isToAuthorize &= !requestPath.matches("^security/.*/password/reset/.*");
		isToAuthorize &= !requestPath.matches("^security/.*/verificationcode/.*");
		
		return isToAuthorize;
	}

	private boolean isRequestTimingAcceptable(String dateHeader) {
		Date clientDate = null;
		try {
			clientDate = Constants.DATE_FORMATTER.parse(dateHeader);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date serverDate = new Date();
		long diff = serverDate.getTime() - clientDate.getTime();
		long diffSeconds = diff / 1000 % 60;
		
		return diffSeconds<this.expirationDuration;
	}
	
	private boolean isNewNonce(String nonce) {
		Integer newNonce = new BigInteger(nonce).intValue();

		boolean isSetChanged = AuthorizationContainerRequestFilter.NONCES_SET.add(newNonce);
		
		if(AuthorizationContainerRequestFilter.NONCES_SET.size()>=this.noncesMaxSetSize){
			AuthorizationContainerRequestFilter.NONCES_SET.clear();
		}
		
		return isSetChanged;
	}	

}
