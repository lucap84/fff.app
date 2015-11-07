package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.PlaceDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.service.provider.CreateUserDTOMessageBodyRW;
import it.fff.business.service.provider.EventDTOMessageBodyRW;
import it.fff.business.service.provider.PlaceDTOMessageBodyRW;
import it.fff.business.service.provider.UserDTOMessageBodyRW;
import it.fff.business.service.provider.WriteResultDTOMessageBodyRW;

public class UserServiceTest extends ServiceTest{

	
	@Test
	public void modifyUserDataShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(UserDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		UserDTO  user = new UserDTO();
		user.setId("1");
		user.setNome("Nome mod");
		user.setCognome("cognome mod");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path(user.getId()).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).put(Entity.entity(user, MediaType.APPLICATION_JSON));
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path(user.getId()).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).put(Entity.entity(user, MediaType.APPLICATION_XML));
		checkEntityWriteResultXML(responseXML);	
	}	
	
	@Test
	public void setCurrentPositionShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(PlaceDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		String userId = "1";
		String eventId = "1";
		PlaceDTO currentPlace = new PlaceDTO();
		currentPlace.setGpsLat("1001");
		currentPlace.setGpsLong("2001");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path(userId).path("events").path(eventId).path("position").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(currentPlace, MediaType.APPLICATION_JSON));
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path(userId).path("events").path(eventId).path("position").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(currentPlace, MediaType.APPLICATION_XML));
		checkEntityWriteResultXML(responseXML);	
	}	
	
	@Test
	public void getEventsByUserShouldReturnAtLeastOneEvent(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);
		
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
		Client client = ClientBuilder.newBuilder().build();
		client.register(UserDTOMessageBodyRW.class);

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
		Client client = ClientBuilder.newBuilder().build();
		client.register(WriteResultDTOMessageBodyRW.class);
		client.register(MultiPartFeature.class);
		
		URL url = getClass().getResource("imagetest.jpg");
		File f = null;;
		f = new File(url.getPath());
		FileDataBodyPart uploadFilePart = new FileDataBodyPart("file",f);
		final FormDataMultiPart multipart = new FormDataMultiPart();
		multipart.bodyPart(uploadFilePart);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path("1").path("images").path("json");
		Response responseJSON = targetJSON.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		checkEntityWriteResultJSON(responseJSON);	
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path("1").path("images").path("xml");
		Response responseXML = targetXML.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		checkEntityWriteResultXML(responseXML);	

	}
	
	@Test
	public void createUserShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(CreateUserDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setNome("Luca");
		createUserDTO.setCognome("Pelosi");
		createUserDTO.setEmail("lucap84@gmail.com");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(createUserDTO, MediaType.APPLICATION_JSON));
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(createUserDTO, MediaType.APPLICATION_XML));
		checkEntityWriteResultXML(responseXML);	
	}
	
	public static void main(String[] args) {
		UserServiceTest test = new UserServiceTest();
		test.modifyUserDataShouldReturnConfirm();
	}
	
}
