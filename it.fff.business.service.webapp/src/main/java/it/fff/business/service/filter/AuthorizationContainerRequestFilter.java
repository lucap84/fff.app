package it.fff.business.service.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.fff.business.service.wsrest.SecurityService;
import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.fff.clientserver.common.secure.DHSecureConfiguration;


//Pre-matching filters are request filters that are executed before the request matching is started
@PreMatching
public class AuthorizationContainerRequestFilter implements ContainerRequestFilter {
	
	@Autowired
	private DHSecureConfiguration secureConfiguration;
	
	private static final Logger logger = LogManager.getLogger(AuthorizationContainerRequestFilter.class);
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		boolean authorized = false;
		String requestPath = requestContext.getUriInfo().getPath();
		if(DHSecureConfiguration.SECURITY_ACTIVATED && !requestPath.matches("^security/.*")){
			logger.debug("request path to verify: "+requestPath);
			String authHeader = requestContext.getHeaders().getFirst("Authorization");
			String dateHeader = requestContext.getHeaders().getFirst("Date");
			String method = requestContext.getMethod();
			String userId = authHeader.split(":")[1];
			String nonce = authHeader.split(":")[2];
			String sharedKey = secureConfiguration.retrieveSharedKey(userId);
			String rebuiltAuthHeader = "";
			if(sharedKey!=null && !"".equals(sharedKey)){
				rebuiltAuthHeader = AuthenticationUtil.generateHMACAuthorizationHeader(sharedKey, userId, method, requestPath, dateHeader, nonce);
			}
			authorized = rebuiltAuthHeader.equals(authHeader);
			
			if(!authorized){
				logger.debug("request not authorized!");
				requestContext.abortWith(Response
						.status(Response.Status.UNAUTHORIZED)
						.entity("User cannot access the resource.")
						.build());
			}
			else{
				logger.debug("request successfully verified!");
			}
		}
		

	}

}
