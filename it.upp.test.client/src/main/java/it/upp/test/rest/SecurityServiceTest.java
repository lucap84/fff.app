package it.upp.test.rest;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.sun.research.ws.wadl.HTTPMethods;

import it.fff.clientserver.common.dto.RegistrationDataDTO;
import it.fff.clientserver.common.dto.RegistrationDataResultDTO;
import it.fff.clientserver.common.dto.UserDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.upp.test.secure.ClientDHSecureConfiguration;


public class SecurityServiceTest extends WebServiceRestTest{
	
	public SecurityServiceTest(String userExecutorId, ClientDHSecureConfiguration secureConf) {
		super(userExecutorId, secureConf);
	}	
	
	public SecurityServiceTest(String userExecutorId) {
		super(userExecutorId, new ClientDHSecureConfiguration());
	}
	
	@Test
	public String registerUserShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		RegistrationDataDTO dtoInput = new RegistrationDataDTO();
		dtoInput.setNome("Luca");
		dtoInput.setCognome("Pelosi");
		dtoInput.setSesso("M");
		dtoInput.setDataNascita("1984-02-09");
		dtoInput.setEmail("lucap84@gmail.com");
		dtoInput.setEncodedPassword("encodedpsw");

		RegistrationDataResultDTO resultDTO = null;

		String restPath="security/registration";
		{//Test JSON
			try{
				String restPathJSON=restPath+"/json";
				Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
				
				KeyAgreement aliceKeyAgree = null;
				if(DHSecureConfiguration.SECURITY_ACTIVATED){
					String alicePpublicKey = "";
					byte[] alicePubKeyEnc = null;
				
					AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
					paramGen.init(512);
					AlgorithmParameters params = paramGen.generateParameters();
					DHParameterSpec dhSkipParamSpec = (DHParameterSpec)params.getParameterSpec(DHParameterSpec.class);
				    		
				    /*
			         * Alice creates her own DH key pair, using the DH parameters from
			         * above
			         */
			        System.out.println("ALICE: Generate DH keypair ...");
			        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
			        aliceKpairGen.initialize(dhSkipParamSpec);
			        KeyPair aliceKpair = aliceKpairGen.generateKeyPair();
			
			        // Alice creates and initializes her DH KeyAgreement object
			        System.out.println("ALICE: Initialization ...");
			        aliceKeyAgree = KeyAgreement.getInstance("DH");
			        aliceKeyAgree.init(aliceKpair.getPrivate());
			
			        // Alice encodes her public key, and sends it over to Bob.
			        alicePubKeyEnc = aliceKpair.getPublic().getEncoded();
					
			        alicePpublicKey = Base64.encodeBase64String(alicePubKeyEnc);
		        
			        requestBuilderJSON = requestBuilderJSON.header("dh", alicePpublicKey);
				}
			
			
//			requestBuilderJSON = super.addDiffieHellmanHeaders(requestBuilderJSON, dtoInput.getEmail(),dtoInput.getEncodedPassword());
			Response responseJSON = requestBuilderJSON.post(Entity.entity(dtoInput, MediaType.APPLICATION_JSON));
			resultDTO = (RegistrationDataResultDTO)responseJSON.readEntity(RegistrationDataResultDTO.class);
			
			if(DHSecureConfiguration.SECURITY_ACTIVATED){
				byte[] bobPublicKey =  Base64.decodeBase64(resultDTO.getPublicKey());
				
		        /*
		         * Alice uses Bob's public key for the first (and only) phase
		         * of her version of the DH
		         * protocol.
		         * Before she can do so, she has to instantiate a DH public key
		         * from Bob's encoded key material.
		         */
		        KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
		        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(bobPublicKey);
		        PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
		        System.out.println("ALICE: Execute PHASE1 ...");
		        aliceKeyAgree.doPhase(bobPubKey, true);			
		        
		        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
		        int aliceLen = aliceSharedSecret.length;
		        String hexString = toHexString(aliceSharedSecret);
		        System.out.println(hexString);
		        
		        //Salvo sul client la chiave segreta condivisa con il server
		        super.getSecureConfiguration().storeSharedKey(resultDTO.getUserId(), hexString);
			}
			
//			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
	        
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
		
		return resultDTO.getUserId();
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
	
	@Test
	public void logoutShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		String restPath="security/"+userId+"/logout";
		
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
	
	@Test
	public void loginShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String username = "lucap84@gmail.com";
		String password = "mypassword";
		
		String restPath="security/login";
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).
					queryParam("username", username).
					queryParam("password", password).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(null);
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML	
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).
					queryParam("username", username).
					queryParam("password", password).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(null);
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	public static void main(String[] args) {
		new SecurityServiceTest("1").registerUserShouldReturnConfirm();
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
