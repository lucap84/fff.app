package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import it.fff.business.common.dto.WriteResultDTO;

public class ServiceTest{
	
	
	protected void checkWriteResultJSON(Response responseJSON){
		assertEquals(200,responseJSON.getStatus());
		assertEquals(MediaType.APPLICATION_JSON.toString(), responseJSON.getMediaType().toString());
		final WriteResultDTO writeResultFromJSON = (WriteResultDTO)responseJSON.readEntity(WriteResultDTO.class);
		assertEquals(writeResultFromJSON.isOk(), false);
		assertNotEquals("-1", writeResultFromJSON.getIdentifier());		
	}
	
	protected void checkWriteResultXML(Response responseXML){
		assertEquals(200, responseXML.getStatus());
		assertEquals(MediaType.APPLICATION_XML.toString(), responseXML.getMediaType().toString());
		final WriteResultDTO writeResultFromXML = (WriteResultDTO)responseXML.readEntity(WriteResultDTO.class);
		assertEquals(writeResultFromXML.isOk(), false);
		assertNotEquals("-1", writeResultFromXML.getIdentifier());			
	}	
	
	protected static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
	}	

}
