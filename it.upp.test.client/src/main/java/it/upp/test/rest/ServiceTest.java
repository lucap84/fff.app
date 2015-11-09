package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;

import it.fff.clientserver.common.dto.WriteResultDTO;


public class ServiceTest{
	
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
	
	
	protected void checkEntityWriteResultJSON(Response responseJSON){
		assertEquals(200,responseJSON.getStatus());
		assertEquals(MediaType.APPLICATION_JSON.toString(), responseJSON.getMediaType().toString());
		final WriteResultDTO writeResultFromJSON = (WriteResultDTO)responseJSON.readEntity(WriteResultDTO.class);
		assertEquals(writeResultFromJSON.isOk(), true);
		assertNotEquals("-1", writeResultFromJSON.getIdentifier());		
	}
	
	protected void checkEntityWriteResultXML(Response responseXML){
		assertEquals(200, responseXML.getStatus());
		assertEquals(MediaType.APPLICATION_XML.toString(), responseXML.getMediaType().toString());
		final WriteResultDTO writeResultFromXML = (WriteResultDTO)responseXML.readEntity(WriteResultDTO.class);
		assertEquals(writeResultFromXML.isOk(), true);
		assertNotEquals("-1", writeResultFromXML.getIdentifier());			
	}	
	
	protected static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
	}	

}
