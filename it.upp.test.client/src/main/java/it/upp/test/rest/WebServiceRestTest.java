package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ContextResolver;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;

import it.fff.business.service.impl.EventBusinessServiceImpl;
import it.fff.clientserver.common.dto.DataTransferObject;
import it.fff.clientserver.common.dto.WriteResultDTO;
import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.upp.test.filter.AuthorizationClientRequestFilter;
import it.upp.test.secure.ClientSecureConfiguration;
import it.upp.test.secure.SecureConfigurationFactory;


public class WebServiceRestTest{
	private static final Logger logger = LogManager.getLogger(WebServiceRestTest.class);

	private ClientSecureConfiguration secureConfiguration;

	public WebServiceRestTest(){
		secureConfiguration = SecureConfigurationFactory.getSecureConfigurationInstance();
	}
	
	private static Client client;
	
	public static Client getClientInstance(){
		if(client!=null){
			return client;
		}
		client = ClientBuilder.newBuilder().build();
		
//		client.register(AttendanceDTOMessageBodyRW.class);
//		client.register(CreateUserDTOMessageBodyRW.class);
//		client.register(EventDTOMessageBodyRW.class);
//		client.register(PlaceDTOMessageBodyRW.class);
//		client.register(UserDTOMessageBodyRW.class);
//		client.register(WriteResultDTOMessageBodyRW.class);
		
		//features
		client.register(MultiPartFeature.class);
		client.register(MoxyJsonFeature.class);
		client.register(MoxyXmlFeature.class);
		client.register(AuthorizationClientRequestFilter.class);
		ContextResolver<MoxyJsonConfig> jsonConfigResolver = getJsonConfigResolver();
		client.register(jsonConfigResolver);
		
		return client;
	}


	private static ContextResolver<MoxyJsonConfig> getJsonConfigResolver() {
		final Map<String, String> namespacePrefixMapper = new HashMap<String, String>();
		namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
		 
		final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig()
//		            .setNamespacePrefixMapper(namespacePrefixMapper)
//		            .setNamespaceSeparator(':')
		            ;
		 
		final ContextResolver<MoxyJsonConfig> jsonConfigResolver = moxyJsonConfig.resolver();
		
		return jsonConfigResolver;
	}

	protected void checkEntityWriteResult(Response response, String mediaType){
		assertEquals(200,response.getStatus());
		assertEquals(mediaType, response.getMediaType().toString());
		final WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		if(!writeResult.isOk()){
			for (Map.Entry<String, String> entry : writeResult.getErrorsMap().entrySet())			{
				logger.error(entry.getKey() + " -->" + entry.getValue());
			}
		}
		assertEquals(writeResult.isOk(), true);
		assertNotEquals("-1", writeResult.getIdentifier());
	}
	
	protected static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
	}


//	public Builder addSecurityHeaders(Builder requestBuilder, String httpMethod, String restPath) {
//		String formattedDate = ClientSecureConfiguration.DATE_FORMATTER.format(new Date()); 
//		String nonce = new BigInteger(32,ClientSecureConfiguration.SECURE_RANDOM).toString();
//		String deviceId = secureConfiguration.getDeviceId();
//		String userId = secureConfiguration.getUserId();
//		String authorizationHeader = AuthenticationUtil.generateHMACAuthorizationHeader(secureConfiguration.retrieveSharedKey(userId, deviceId), userId, httpMethod, restPath, formattedDate, nonce);
//		return requestBuilder.
//				header("Authorization", authorizationHeader).
//				header("Date", formattedDate).
//				header("Device", deviceId);
//	}



	public Builder addDiffieHellmanHeaders(Builder requestBuilder, String email, String encodedPassword)  {
		String alicePpublicKey = "";
		byte[] alicePubKeyEnc = null;
		try{
		
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
	        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
	        aliceKeyAgree.init(aliceKpair.getPrivate());
	
	        // Alice encodes her public key, and sends it over to Bob.
	        alicePubKeyEnc = aliceKpair.getPublic().getEncoded();
			
	        alicePpublicKey = Base64.encodeBase64String(alicePubKeyEnc);
        
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return requestBuilder.header("dh", alicePpublicKey);
	}
	
	
	public ClientSecureConfiguration getSecureConfiguration() {
		return secureConfiguration;
	}


	public void setSecureConfiguration(ClientSecureConfiguration secureConfiguration) {
		this.secureConfiguration = secureConfiguration;
	}


	public static void main(String[] args) {
		
		
	}
	
}
