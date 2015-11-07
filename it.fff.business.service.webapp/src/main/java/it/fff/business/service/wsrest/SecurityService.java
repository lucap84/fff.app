package it.fff.business.service.wsrest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.dto.WriteResultDTO;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;

@Component("securityService")
@Path("/security")
public class SecurityService extends ApplicationService {
	
	private static final Logger logger = LogManager.getLogger(SecurityService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;	
	
	public SecurityService() {
		logger.debug("Service created");
	}
	
	@POST
	@Path("login/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO setCurrentPositionJSON(@Context HttpServletRequest request,
												 @QueryParam("username") String username,
												 @QueryParam("password") String password) throws BusinessException {
		return login(request, username, password);
	}
	@POST
	@Path("login/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO setCurrentPositionXML(@Context HttpServletRequest request,
												@QueryParam("username") String username,
												@QueryParam("password") String password) throws BusinessException {
		return login(request, username, password);
	}
	
	/*
	 *	
	 *
	 *
	 *
	 *		Delegating methods 
	 *
	 *
	 *
	 *
	 *
	 */
	
	private WriteResultDTO login(HttpServletRequest request, String username, String password) {
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(String.valueOf(username.hashCode()+password.hashCode()));
		return result;
	}	
}
