package it.fff.business.service.wsrest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import it.fff.clientserver.common.dto.*;
import it.fff.business.common.util.LogUtils;
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
	
	@PUT
	@Path("{email}/password/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO updatePasswordJSON(@Context HttpServletRequest request, @PathParam("email") String email, String encodedPassword) throws BusinessException {
		return updatePassword(request, email, encodedPassword);
	}
	@PUT
	@Path("{email}/password/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO updatePasswordCodeXML(@Context HttpServletRequest request, @PathParam("email") String email, String encodedPassword) throws BusinessException {
		return updatePassword(request, email, encodedPassword);
	}	
	
	@PUT
	@Path("{email}/verificationcode/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO checkVerificationCodeJSON(@Context HttpServletRequest request, @PathParam("email") String email, String verificationcode) throws BusinessException {
		return checkVerificationCode(request, email, verificationcode);
	}
	@PUT
	@Path("{email}/verificationcode/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO checkVerificationCodeXML(@Context HttpServletRequest request, @PathParam("email") String email, String verificationcode) throws BusinessException {
		return checkVerificationCode(request, email, verificationcode);
	}	
	
	@POST
	@Path("{email}/verificationcode/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO sendVerificationCodeJSON(@Context HttpServletRequest request, @PathParam("email") String email) throws BusinessException {
		return sendVerificationCode(request, email);
	}
	@POST
	@Path("{email}/verificationcode/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO sendVerificationCodeXML(@Context HttpServletRequest request, @PathParam("email") String email) throws BusinessException {
		return sendVerificationCode(request, email);
	}	
	
	@POST
	@Path("{userId}/logout/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO logoutJSON(@Context HttpServletRequest request, @PathParam("userId") String userId) throws BusinessException {
		return logout(request, userId);
	}
	@POST
	@Path("{userId}/logout/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO logoutXML(@Context HttpServletRequest request, @PathParam("userId") String userId) throws BusinessException {
		return logout(request, userId);
	}	
	
	@POST
	@Path("login/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO loginJSON(@Context HttpServletRequest request,
												 @QueryParam("username") String username,
												 @QueryParam("password") String encodedPasw) throws BusinessException {
		return login(request, username, encodedPasw);
	}
	@POST
	@Path("login/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO loginXML(@Context HttpServletRequest request,
												@QueryParam("username") String username,
												@QueryParam("password") String encodedPasw) throws BusinessException {
		return login(request, username, encodedPasw);
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
	private WriteResultDTO updatePassword(HttpServletRequest request, String email, String encodedPassword) {
		WriteResultDTO result = businessServiceFacade.updatePassword(email, encodedPassword);
		return result;
	}	
	
	private WriteResultDTO checkVerificationCode(HttpServletRequest request, String email, String verificationcode) {
		WriteResultDTO result = businessServiceFacade.checkVerificationCode(email, verificationcode);
		return result;
	}
	
	private WriteResultDTO sendVerificationCode(HttpServletRequest request, String email) {
		WriteResultDTO result = businessServiceFacade.sendVerificationCode(email);
		return result;
	}	
	
	private WriteResultDTO logout(HttpServletRequest request, String userId) {
		WriteResultDTO result = businessServiceFacade.logout(userId);
		return result;
	}	
	
	private WriteResultDTO login(HttpServletRequest request, String username, String password) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.login(username, password);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
}
