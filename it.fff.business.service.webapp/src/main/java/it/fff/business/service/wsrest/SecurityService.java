package it.fff.business.service.wsrest;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;

@Component("securityService")
@Path("/security")
public class SecurityService extends ApplicationService {
	
	private static final Logger logger = LogManager.getLogger(SecurityService.class);
	
	@Autowired
	private DHSecureConfiguration secureConfiguration;
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;	
	
	public SecurityService() {
		logger.debug("Service created");
	}
	
	@POST
	@Path("registration/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RegistrationDataResultDTO registerUserJSON( @Context HttpServletRequest request,
											@Context HttpHeaders headers,
											RegistrationDataDTO registrationDataDTO) throws BusinessException {
		return registerUser(request, headers, registrationDataDTO);
	}
	@POST
	@Path("registration/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public RegistrationDataResultDTO registerUserXML( @Context HttpServletRequest request,
										   @Context HttpHeaders headers,
										   RegistrationDataDTO registrationDataDTO) throws BusinessException {
		return registerUser(request, headers, registrationDataDTO);
	}
	
	@POST
	@Path("{userId}/logout/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO logoutJSON(@Context HttpServletRequest request,
									 @Context HttpHeaders headers,
									 @PathParam("userId") String userId) throws BusinessException {
		return logout(request, headers, userId);
	}
	@POST
	@Path("{userId}/logout/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO logoutXML(@Context HttpServletRequest request,
			 						@Context HttpHeaders headers,
			 						@PathParam("userId") String userId) throws BusinessException {
		return logout(request, headers, userId);
}
	
	
	@PUT
	@Path("{email}/password/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO updatePasswordJSON(	@Context HttpServletRequest request,
												@PathParam("email") String email, String encodedPassword) throws BusinessException {
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
	
	private RegistrationDataResultDTO registerUser(HttpServletRequest request, HttpHeaders headers, RegistrationDataDTO registrationDataDTO) {
		RegistrationDataResultDTO resultDTO = null;
		resultDTO = new RegistrationDataResultDTO();

		String deviceId = headers.getRequestHeader("Device").get(0);
		String bobPublicKey = (String)request.getAttribute("bobPubKeyEncStrB64");
		String sharedSecretHEX = (String)request.getAttribute("sharedSecretHEX");

		try {
			resultDTO = businessServiceFacade.createUser(registrationDataDTO);
		} catch (BusinessException e) {
			resultDTO = new RegistrationDataResultDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));			
		}
		
		if(resultDTO.isOk()){
			resultDTO.setServerPublicKey(bobPublicKey);
			secureConfiguration.storeSharedKey(resultDTO.getUserId(), deviceId, sharedSecretHEX);
		}
        
		return resultDTO;
	}	
	
	private WriteResultDTO logout(HttpServletRequest request, HttpHeaders headers, String userId) {
		WriteResultDTO resultDTO;
		String deviceId = headers.getRequestHeader("Device").get(0);
		try {
			resultDTO = businessServiceFacade.logout(userId);
		} catch (BusinessException e) {
			resultDTO = new WriteResultDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		
		if(resultDTO.isOk()){
			secureConfiguration.removeSharedKey(resultDTO.getIdentifier(), deviceId);
		}		
		
		return resultDTO;
	}	
	
	private WriteResultDTO updatePassword(HttpServletRequest request, String email, String encodedPassword) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.updatePassword(email, encodedPassword);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
	private WriteResultDTO checkVerificationCode(HttpServletRequest request, String email, String verificationcode) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.checkVerificationCode(email, verificationcode);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private WriteResultDTO sendVerificationCode(HttpServletRequest request, String email) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.sendVerificationCode(email);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
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
