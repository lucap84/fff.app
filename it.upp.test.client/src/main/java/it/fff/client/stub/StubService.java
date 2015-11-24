package it.fff.client.stub;

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
import it.fff.client.filter.AuthorizationClientRequestFilter;
import it.fff.client.secure.ClientSecureConfiguration;
import it.fff.client.secure.SecureConfigurationFactory;
import it.fff.clientserver.common.dto.DataTransferObject;
import it.fff.clientserver.common.dto.WriteResultDTO;
import it.fff.clientserver.common.secure.AuthenticationUtil;


public class StubService{
	private static final Logger logger = LogManager.getLogger(StubService.class);


	public static final String WSRS_PATH_getEventMessages 			= "events/#/messages/";
	public static final String WSRS_PATH_postEventStandardMessage	= "events/#/attendances/#/messages/standard/#/";
	public static final String WSRS_PATH_postEventMessage 			= "events/#/attendances/#/messages/";
	public static final String WSRS_PATH_cancelAttendance 			= "events/#/attendances/#/";
	public static final String WSRS_PATH_addFeedback 				= "events/#/attendances/#/feedback/";
	public static final String WSRS_PATH_joinEvent 					= "events/#/attendances/";
	public static final String WSRS_PATH_cancelEvent 				= "events/#/";
	public static final String WSRS_PATH_createEvent 				= "events/";
	public static final String WSRS_PATH_getAttendacesByEvent 		= "events/#/attendaces/";
	public static final String WSRS_PATH_getEvent 					= "events/#/";
	public static final String WSRS_PATH_searchEvents 				= "events/";
	public static final String WSRS_PATH_getPlacesByDescription 	= "places/";
	public static final String WSRS_PATH_upgradeToPremium 			= "premium/subscriptions/#/";
	public static final String WSRS_PATH_registerUser 				= "security/registration/";
	public static final String WSRS_PATH_login 						= "security/login/";
	public static final String WSRS_PATH_logout 					= "security/#/logout/";
	public static final String WSRS_PATH_updatePassword 			= "security/#/password/";
	public static final String WSRS_PATH_checkVerificationCode 		= "security/#/verificationcode/";
	public static final String WSRS_PATH_sendVerificationCode 		= "security/#/verificationcode/";
	public static final String WSRS_PATH_modifyUserData 			= "users/#/";
	public static final String WSRS_PATH_setCurrentPosition 		= "users/#/events/#/position/";
	public static final String WSRS_PATH_getEventsByUser 			= "users/#/events/";
	public static final String WSRS_PATH_getUser 					= "users/#/";
	public static final String WSRS_PATH_updateProfileImage 		= "users/#/images/";


	private ClientSecureConfiguration secureConfiguration;

	public StubService(ClientSecureConfiguration secureConfiguration){
		this.secureConfiguration = secureConfiguration;
	}	
	
	public StubService(){
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

	
	public ClientSecureConfiguration getSecureConfiguration() {
		return secureConfiguration;
	}


	public void setSecureConfiguration(ClientSecureConfiguration secureConfiguration) {
		this.secureConfiguration = secureConfiguration;
	}

	public String getWsRspath(String mediaType, String servicePath, String... params){
		String path = servicePath;
		for (String param : params) {
			path = path.replaceFirst("#", param);
		}
		path += mediaType.toLowerCase().substring("application/".length());
		return path;
	}

}
