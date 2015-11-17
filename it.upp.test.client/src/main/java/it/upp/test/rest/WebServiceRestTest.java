package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ContextResolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;

import it.fff.business.service.impl.EventBusinessServiceImpl;
import it.fff.clientserver.common.dto.WriteResultDTO;
import it.upp.test.util.AuthenticationUtil;


public class WebServiceRestTest{
	private static final Logger logger = LogManager.getLogger(WebServiceRestTest.class);
	
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


	public Builder addSecurityHeaders(Builder requestBuilder, String httpMethod, String restPathJSON) {
		String secret = "mysecret";
		String hmacData = httpMethod+restPathJSON;
		return requestBuilder.header("Authorization", "hmac "+AuthenticationUtil.digestHMACbase64(secret,hmacData));
	}	

}
