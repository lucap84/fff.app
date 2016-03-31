package it.fff.business.service.wsrest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.enums.UserSexEnum;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.Constants;
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
	public AuthDataResponseDTO registerUserJSON( @Context HttpServletRequest request,
											@Context HttpHeaders headers,
											RegistrationInputDTO registrationInputDTO) throws BusinessException {
		return registerUser(request, headers, registrationInputDTO);
	}
	@POST
	@Path("registration/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public AuthDataResponseDTO registerUserXML( @Context HttpServletRequest request,
										   @Context HttpHeaders headers,
										   RegistrationInputDTO registrationInputDTO) throws BusinessException {
		return registerUser(request, headers, registrationInputDTO);
	}
	@POST
	@Path("login/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AuthDataResponseDTO loginJSON(@Context HttpServletRequest request,
									@Context HttpHeaders headers,						
									LoginInputDTO loginInfo) throws BusinessException {
		return login(request, headers, loginInfo);
	}
	@POST
	@Path("login/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public AuthDataResponseDTO loginXML( @Context HttpServletRequest request,
									@Context HttpHeaders headers,						
									LoginInputDTO loginInfo) throws BusinessException {
		return login(request, headers, loginInfo);
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
												UpdatePasswordDTO updatePasswordDTO) throws BusinessException {
		return updatePassword(request,updatePasswordDTO);
	}
	@PUT
	@Path("{email}/password/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO updatePasswordCodeXML(@Context HttpServletRequest request,
												UpdatePasswordDTO updatePasswordDTO) throws BusinessException {
		return updatePassword(request,updatePasswordDTO);
	}	

	@PUT
	@Path("{email}/password/reset/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO resetPasswordJSON(@Context HttpServletRequest request,
											ResetPasswordDTO resetPasswordDTO) throws BusinessException {
		return resetPassword(request,resetPasswordDTO);
	}
	@PUT
	@Path("{email}/password/reset/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO resetPasswordCodeXML(@Context HttpServletRequest request,
			ResetPasswordDTO resetPasswordDTO) throws BusinessException {
		return resetPassword(request,resetPasswordDTO);
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
	
	@GET
	@Path("fb/login/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginFacebookJSON(@Context HttpServletRequest request,
												 @Context HttpHeaders headers) 
												 throws BusinessException {
		return loginFacebook(request, headers);
	}
	@GET
	@Path("fb/login/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String loginFacebookXML(@Context HttpServletRequest request,
												@Context HttpHeaders headers) 
												throws BusinessException {
		return loginFacebook(request, headers);
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
	

	private AuthDataResponseDTO registerUser(HttpServletRequest request, HttpHeaders headers, RegistrationInputDTO registrationInputDTO) {
		AuthDataResponseDTO resultDTO = null;
		resultDTO = new AuthDataResponseDTO();

		String deviceId = null;
		List<String> devicesHeader = headers.getRequestHeader("Device");
		if(devicesHeader!=null && devicesHeader.size()>0){
			deviceId = devicesHeader.get(0);
		}
		String serverPublicKey = (String)request.getAttribute("serverPubKeyEncStrB64");
		String sharedSecretHEX = (String)request.getAttribute("sharedSecretHEX");
		
		registrationInputDTO.setDeviceId(deviceId);
		registrationInputDTO.setSharedKey(sharedSecretHEX);
		try {
			resultDTO = businessServiceFacade.createUser(registrationInputDTO);
		} catch (BusinessException e) {
			resultDTO = new AuthDataResponseDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));			
		}
		
		if(resultDTO.isOk()){
			resultDTO.setServerPublicKey(serverPublicKey);
			Integer userId = Integer.valueOf(resultDTO.getUserId());
			secureConfiguration.storeSharedKey(userId, deviceId, sharedSecretHEX);
		}
        
		return resultDTO;
	}
	
	private AuthDataResponseDTO login(HttpServletRequest request, HttpHeaders headers, LoginInputDTO loginInfo) {
		AuthDataResponseDTO resultDTO;
		String deviceId = null;
		List<String> devicesHeader = headers.getRequestHeader("Device");
		if(devicesHeader!=null && devicesHeader.size()>0){
			deviceId = devicesHeader.get(0);
		}
		String serverPublicKey = (String)request.getAttribute("serverPubKeyEncStrB64");
		String sharedSecretHEX = (String)request.getAttribute("sharedSecretHEX");
		
		loginInfo.setDeviceId(deviceId);
		
		try {
			resultDTO = businessServiceFacade.login(loginInfo, sharedSecretHEX);
		} catch (BusinessException e) {
			resultDTO = new AuthDataResponseDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		
		if(resultDTO.isOk()){
			resultDTO.setServerPublicKey(serverPublicKey);
			Integer userId = Integer.valueOf(resultDTO.getUserId());
			secureConfiguration.storeSharedKey(userId, deviceId, sharedSecretHEX);
		}		
		
		return resultDTO;
	}	
	
	private WriteResultDTO logout(HttpServletRequest request, HttpHeaders headers, String userId) {
		WriteResultDTO resultDTO;
		String deviceId = null;
		List<String> devicesHeader = headers.getRequestHeader("Device");
		if(devicesHeader!=null && devicesHeader.size()>0){
			deviceId = devicesHeader.get(0);
		}
		try {
			resultDTO = businessServiceFacade.logout(userId, deviceId);
		} catch (BusinessException e) {
			resultDTO = new WriteResultDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		
		if(resultDTO.isOk()){
			Integer userIdInt = Integer.valueOf(userId);
			secureConfiguration.removeSharedKey(userIdInt, deviceId);
		}		
		
		return resultDTO;
	}
	
	
	private WriteResultDTO updatePassword(HttpServletRequest request, UpdatePasswordDTO updatePasswordDTO) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.updatePassword(updatePasswordDTO);
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
	
	private WriteResultDTO resetPassword(HttpServletRequest request, ResetPasswordDTO resetPasswordDTO) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.resetPassword(resetPasswordDTO);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	
	private String loginFacebook(HttpServletRequest request, HttpHeaders headers) {
		String loginToken = null;
		
		String code = request.getParameter("code");
        if (code == null || code.equals("")) {
            logger.error("'code' not present in the request");
            return loginToken;
        }
        
        try {
        	ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
        	String myAppId = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_APP_ID);
        	String myAppSecret = confProvider.getFacebookConfigProperty(Constants.PROP_FACEBOOK_APP_SECRET);
            String g = "https://graph.facebook.com/oauth/access_token?client_id="+myAppId+"&redirect_uri=" + URLEncoder.encode("http://localhost:8080/it.fff.business.service.webapp/restapi/security/fb/login/json", "UTF-8") + "&client_secret="+myAppSecret+"&code=" + code;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");            
            in.close();
            loginToken = b.toString();
            if (loginToken.startsWith("{"))
                throw new Exception("error on requesting token: " + loginToken + " with code: " + code);
        } catch (Exception e) {
            logger.error("invalid 'token'");
            return loginToken;
        } 
        
        return loginToken;
	}
	

	
	
}
