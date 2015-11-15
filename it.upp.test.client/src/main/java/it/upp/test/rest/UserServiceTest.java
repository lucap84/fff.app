package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import it.fff.clientserver.common.dto.*;

public class UserServiceTest extends WebServiceRestTest{

	
	@Test
	public void modifyUserDataShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		UserDTO  user = new UserDTO();
		user.setId("1");
		user.setNome("Nome mod");
		user.setCognome("cognome mod");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path(user.getId()).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).put(Entity.entity(user, MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path(user.getId()).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).put(Entity.entity(user, MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void setCurrentPositionShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		String eventId = "1";
		PlaceDTO currentPlace = new PlaceDTO();
		currentPlace.setGpsLat("1001");
		currentPlace.setGpsLong("2001");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path(userId).path("events").path(eventId).path("position").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(currentPlace, MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path(userId).path("events").path(eventId).path("position").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(currentPlace, MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void getEventsByUserShouldReturnAtLeastOneEvent(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path(userId).path("events").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		final List<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<EventDTO>>(){});
		assertNotNull(entityFromJSON);
		assertTrue(entityFromJSON.size()>0);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path(userId).path("events").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		List<EventDTO> entityFromXML = responseXML.readEntity(new GenericType<List<EventDTO>>(){});
		assertNotNull(entityFromXML);
		assertTrue(entityFromXML.size()>0);
	}
	
	@Test
	public void getUserShouldReturnOneUser(){
		Client client = WebServiceRestTest.getClientInstance();

		String requestedUserId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path(requestedUserId).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		UserDTO entityFromJSON = responseJSON.readEntity(UserDTO.class);
		assertNotNull(entityFromJSON);
		assertEquals(String.valueOf(entityFromJSON.getId()), requestedUserId);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path(requestedUserId).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		UserDTO entityFromXML = responseXML.readEntity(UserDTO.class);
		assertNotNull(entityFromXML);
		assertEquals(String.valueOf(entityFromXML.getId()), requestedUserId);
	}
	
	@Test
	public void updateProfileImageShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		URL url = getClass().getResource("imagetest.jpg");
		File f = null;;
		f = new File(url.getPath());
		FileDataBodyPart uploadFilePart = new FileDataBodyPart("file",f);
		final FormDataMultiPart multipart = new FormDataMultiPart();
		multipart.bodyPart(uploadFilePart);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path("1").path("images").path("json");
		Response responseJSON = targetJSON.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);	
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path("1").path("images").path("xml");
		Response responseXML = targetXML.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);

	}
	
	@Test
	public void createUserShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setNome("Luca");
		userDTO.setCognome("Pelosi");
		userDTO.setEmail("lucap84@gmail.com");
		userDTO.setSesso("M");
		userDTO.setDataNascita("1984-02-09");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(userDTO, MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(userDTO, MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
	}
	
	public static void main(String[] args) {
		UserServiceTest test = new UserServiceTest();
		test.modifyUserDataShouldReturnConfirm();
	}
	
}
