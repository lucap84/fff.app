package it.upp.test.rest;

import static org.junit.Assert.assertEquals;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.sun.research.ws.wadl.HTTPMethods;

import it.fff.clientserver.common.dto.RegistrationDataRequestDTO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.LoginDataRequestDTO;
import it.fff.clientserver.common.dto.UserDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.upp.test.secure.ClientSecureConfiguration;
import it.upp.test.util.DHUtils;


public class SecurityServiceTest extends WebServiceRestTest{
	
	public SecurityServiceTest() {
	}

	@Test
	public void registerUserShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String deviceId = "android-mobile-0001";
		super.getSecureConfiguration().setDeviceId(deviceId);
		
		RegistrationDataRequestDTO dtoInput = new RegistrationDataRequestDTO();
		dtoInput.setNome("Luca");
		dtoInput.setCognome("Pelosi");
		dtoInput.setSesso("M");
		dtoInput.setDataNascita("1984-02-09");

		dtoInput.setEmail("lucap84@gmail.com");
		dtoInput.setEncodedPassword(DigestUtils.md5Hex("mypassword"));

		AuthDataResponseDTO resultDTO = null;

		String restPath="security/registration";
		{//Test JSON
			try{
				String restPathJSON=restPath+"/json";
				Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
				
				KeyAgreement clientKeyAgree = KeyAgreement.getInstance("DH");
				DHUtils dhUtil = new DHUtils();
				String clientPpublicKey = dhUtil.generateClientPublicKey(clientKeyAgree);
				
				requestBuilderJSON = requestBuilderJSON.header("dh", clientPpublicKey);
				
				Response responseJSON = requestBuilderJSON.post(Entity.entity(dtoInput, MediaType.APPLICATION_JSON));
				assertEquals(200,responseJSON.getStatus());
				assertEquals(MediaType.APPLICATION_JSON, responseJSON.getMediaType().toString());
				
				resultDTO = (AuthDataResponseDTO)responseJSON.readEntity(AuthDataResponseDTO.class);
			
				byte[] serverPublicKey =  Base64.decodeBase64(resultDTO.getServerPublicKey());
				String sharedSecret = dhUtil.generateSharedSecret(clientKeyAgree, serverPublicKey);			
		        //Salvo sul client la chiave segreta condivisa con il server

				super.getSecureConfiguration().storeSharedKey(resultDTO.getUserId(), deviceId, sharedSecret);
	        
			}
			catch(Exception e){
				e.printStackTrace();
			}		        
		}
//		{//Test XML
//			String restPathXML=restPath+"/xml";
//			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
//			Response responseXML = requestBuilderXML.post(Entity.entity(dtoInput, MediaType.APPLICATION_XML));
//			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
//		}
		
	}	
	
	
	@Test
	public void logoutShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = super.getSecureConfiguration().getUserId();
		String deviceId = super.getSecureConfiguration().getDeviceId();
		String restPath="security/"+userId+"/logout";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(null);
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
			super.getSecureConfiguration().removeSharedKey(userId, deviceId);
		}
		
//		{//Test XML	
//			String restPathXML=restPath+"/xml";
//			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
//			Response responseXML = requestBuilderXML.post(null);
//			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
//			super.getSecureConfiguration().removeSharedKey(userId, deviceId);
//		}
	}
	
	@Test
	public void loginShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		LoginDataRequestDTO dtoInput = new LoginDataRequestDTO();
		dtoInput.setEmail("lucap84@gmail.com");
		dtoInput.setEncodedPassword(DigestUtils.md5Hex("mypassword"));
		
		AuthDataResponseDTO resultDTO = null;
		
		String restPath="security/login";
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			try{
				KeyAgreement clientKeyAgree = KeyAgreement.getInstance("DH");
				DHUtils dhUtil = new DHUtils();
				String clientPpublicKey = dhUtil.generateClientPublicKey(clientKeyAgree);
				
				requestBuilderJSON = requestBuilderJSON.header("dh", clientPpublicKey);
				
				Response responseJSON = requestBuilderJSON.post(Entity.entity(dtoInput, MediaType.APPLICATION_JSON));
				assertEquals(200,responseJSON.getStatus());
				assertEquals(MediaType.APPLICATION_JSON, responseJSON.getMediaType().toString());
				
				resultDTO = (AuthDataResponseDTO)responseJSON.readEntity(AuthDataResponseDTO.class);
				
				byte[] serverPublicKey =  Base64.decodeBase64(resultDTO.getServerPublicKey());
				String sharedSecret = dhUtil.generateSharedSecret(clientKeyAgree, serverPublicKey);			
		        
				String deviceId = super.getSecureConfiguration().getDeviceId();
				
				//Salvo sul client la chiave segreta condivisa con il server
				super.getSecureConfiguration().storeSharedKey(resultDTO.getUserId(), deviceId, sharedSecret);				
				
			}
			catch(Exception e){
				
			}

		}
		
//		{//Test XML	
//			String restPathXML=restPath+"/xml";
//			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).
//					queryParam("username", username).
//					queryParam("password", password).request(MediaType.APPLICATION_XML);
//			Response responseXML = requestBuilderXML.post(null);
//			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
//		}
	}		

	@Test
	public void updatePasswordShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String enodedPassword = "a1x2c3v4b56n7h8jjj9j0";
		
		String restPath="security/"+email+"/password";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.put(Entity.entity(enodedPassword,MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.put(Entity.entity(enodedPassword,MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void checkVerificationCodeShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String verificationCode = "1234567890";
		
		String restPath="security/"+email+"/verificationcode";
		
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  =  client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.put(Entity.entity(verificationCode,MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML	
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.put(Entity.entity(verificationCode,MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void sendVerificationCodeShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String restPath="security/"+email+"/verificationcode";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(null);
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML		
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(null);
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	

	
	public static void main(String[] args) {
		SecurityServiceTest securityServiceTest = new SecurityServiceTest();
//		securityServiceTest.registerUserShouldReturnConfirm();
//		securityServiceTest.logoutShouldReturnConfirm();
		securityServiceTest.loginShouldReturnConfirm();
	}
	
   

}
