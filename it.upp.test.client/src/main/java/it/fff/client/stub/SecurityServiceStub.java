package it.fff.client.stub;


import javax.crypto.KeyAgreement;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import it.fff.client.secure.ClientSecureConfiguration;
import it.fff.client.util.DHUtils;
import it.fff.client.wsrest.WebServiceRestTest;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.LoginDataRequestDTO;
import it.fff.clientserver.common.dto.RegistrationDataRequestDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class SecurityServiceStub extends StubService{
	
	public SecurityServiceStub(){
		super();
	}
	
	public SecurityServiceStub(ClientSecureConfiguration secureConfiguration){
		super(secureConfiguration);
	}
	
	public AuthDataResponseDTO registerUser(RegistrationDataRequestDTO dtoInput, String mediaType){
		Client client = super.getClientInstance();
		
		String deviceId = "android-mobile-0001";
		super.getSecureConfiguration().setDeviceId(deviceId);
		
		AuthDataResponseDTO resultDTO = null;

		String restPath="security/registration/"+mediaType.toLowerCase().substring("application/".length());
		try{
			Builder requestBuilder = client.target(getBaseURI()).path(restPath).request(mediaType);
			
			KeyAgreement clientKeyAgree = KeyAgreement.getInstance("DH");
			DHUtils dhUtil = new DHUtils();
			String clientPpublicKey = dhUtil.generateClientPublicKey(clientKeyAgree);
			requestBuilder = requestBuilder.header("dh", clientPpublicKey);
			
			Response response = requestBuilder.post(Entity.entity(dtoInput, mediaType));
			
			resultDTO = (AuthDataResponseDTO)response.readEntity(AuthDataResponseDTO.class);
			byte[] serverPublicKey =  Base64.decodeBase64(resultDTO.getServerPublicKey());
			String sharedSecret = dhUtil.generateSharedSecret(clientKeyAgree, serverPublicKey);			
	        //Salvo sul client la chiave segreta condivisa con il server

			super.getSecureConfiguration().storeSharedKey(resultDTO.getUserId(), deviceId, sharedSecret);
        
		}
		catch(Exception e){
			e.printStackTrace();
		}		        
		
		return resultDTO;
	}

	public WriteResultDTO logout(String mediaType){
		
		Client client = super.getClientInstance();
		
		String userId = super.getSecureConfiguration().getUserId();
		String deviceId = super.getSecureConfiguration().getDeviceId();
		
		WriteResultDTO result = null;	
		String restPath="security/"+userId+"/logout/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		
		Response response = requestBuilder.post(null);
		
		result = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		super.getSecureConfiguration().removeSharedKey(userId, deviceId);
		
		return result;
	}
	
	public AuthDataResponseDTO login(LoginDataRequestDTO dtoInput, String mediaType){
		Client client = super.getClientInstance();
		
		AuthDataResponseDTO resultDTO = null;
		
		String restPath="security/login/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		try{
			KeyAgreement clientKeyAgree = KeyAgreement.getInstance("DH");
			DHUtils dhUtil = new DHUtils();
			String clientPpublicKey = dhUtil.generateClientPublicKey(clientKeyAgree);
			requestBuilder = requestBuilder.header("dh", clientPpublicKey);
			
			Response responseJSON = requestBuilder.post(Entity.entity(dtoInput, mediaType));
			
			resultDTO = (AuthDataResponseDTO)responseJSON.readEntity(AuthDataResponseDTO.class);
			
			byte[] serverPublicKey =  Base64.decodeBase64(resultDTO.getServerPublicKey());
			String sharedSecret = dhUtil.generateSharedSecret(clientKeyAgree, serverPublicKey);			
			String deviceId = super.getSecureConfiguration().getDeviceId();
			//Salvo sul client la chiave segreta condivisa con il server
			super.getSecureConfiguration().storeSharedKey(resultDTO.getUserId(), deviceId, sharedSecret);				
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
		return resultDTO;
	}
	
	public WriteResultDTO updatePassword(String email, String oldPassword,String newPassword, String mediaType){
		Client client = super.getClientInstance();
		
		String enodedOldPassword = DigestUtils.md5Hex(oldPassword);
		String enodedNewPassword = DigestUtils.md5Hex(newPassword);
		
		WriteResultDTO writeResult = null;
		
		String restPath="security/"+email+"/password/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.put(Entity.entity(enodedNewPassword,mediaType));
		writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		
		return writeResult;
	}
	
	public WriteResultDTO checkVerificationCode(String email, String verificationCode, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath="security/"+email+"/verificationcode/"+mediaType.toLowerCase().substring("application/".length());
		
		WriteResultDTO writeResult = null;
		
		Builder requestBuilder  =  client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.put(Entity.entity(verificationCode,mediaType));
		writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		
		return writeResult;		
	}
	
	public WriteResultDTO sendVerificationCode(String email, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath="security/"+email+"/verificationcode/"+mediaType.toLowerCase().substring("application/".length());
		WriteResultDTO writeResult = null;
		
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(null);
		writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		
		return writeResult;
	}
}