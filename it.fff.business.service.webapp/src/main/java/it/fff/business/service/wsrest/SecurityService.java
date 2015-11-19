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
	
	private RegistrationDataResultDTO registerUser(HttpServletRequest request, HttpHeaders headers, RegistrationDataDTO registrationDataDTO) {
		RegistrationDataResultDTO resultDTO = null;
		resultDTO = new RegistrationDataResultDTO();
		
		String hexString = null;
		if(DHSecureConfiguration.SECURITY_ACTIVATED){
			
			String dhHeader = headers.getRequestHeader("dh").get(0);
			if(dhHeader==null || "".equals(dhHeader)){
				return null;
			}
			byte[] decodeBase64 = Base64.decodeBase64(dhHeader);
			byte[] alicePubKeyEnc = decodeBase64;
			
	        /*
	         * Let's turn over to Bob. Bob has received Alice's public key
	         * in encoded format.
	         * He instantiates a DH public key from the encoded key material.
	         */
			try{
		        KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
		        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(alicePubKeyEnc);
		        PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);
		        
		        /*
		         * Bob gets the DH parameters associated with Alice's public key.
		         * He must use the same parameters when he generates his own key
		         * pair.
		         */
		        DHParameterSpec dhParamSpec = ((DHPublicKey)alicePubKey).getParams();
		        
		        System.out.println("BOB: Generate DH keypair ...");
		        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
		        bobKpairGen.initialize(dhParamSpec);
		        KeyPair bobKpair = bobKpairGen.generateKeyPair();
		        
		        // Bob creates and initializes his DH KeyAgreement object
		        System.out.println("BOB: Initialization ...");
		        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
		        bobKeyAgree.init(bobKpair.getPrivate());
		        
		        System.out.println("BOB: Execute PHASE1 ...");
		        bobKeyAgree.doPhase(alicePubKey, true);
	
		        // Bob encodes his public key, and sends it over to Alice.
		        byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();
		        String bobPubKeyEncStr = Base64.encodeBase64String(bobPubKeyEnc);
		        
		        resultDTO.setPublicKey(bobPubKeyEncStr);
		        
		        byte[] bobSharedSecret = new byte[64];
		        int bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 0);
		        hexString = toHexString(bobSharedSecret);
				System.out.println(hexString);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		
		}
			//TODO registra utente salvando anche bobSharedSecret e ottieni userId
		resultDTO.setOk(true);
		resultDTO.setUserId("1");
		
		if(DHSecureConfiguration.SECURITY_ACTIVATED){
	        secureConfiguration.storeSharedKey(resultDTO.getUserId(), hexString);
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
	
	private WriteResultDTO logout(HttpServletRequest request, String userId) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.logout(userId);
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
	
	
    private String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++) {
             byte2hex(block[i], buf);
             if (i < len-1) {
                 buf.append(":");
             }
        }
        return buf.toString();
    }
    
    private void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                            '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }  	
}
