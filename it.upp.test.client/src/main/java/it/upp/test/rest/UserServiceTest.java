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
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import it.fff.clientserver.common.dto.*;
import it.upp.test.secure.ClientSecureConfiguration;

public class UserServiceTest extends WebServiceRestTest{


	public UserServiceTest(){
	}	
	

	@Test
	public void modifyUserDataShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		UserDTO  user = new UserDTO();
		user.setId("1");
		user.setNome("Nome mod");
		user.setCognome("cognome mod");
		String restPath="users/"+user.getId();
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.put(Entity.entity(user, MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.put(Entity.entity(user, MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}
	}	
	
	@Test
	public void setCurrentPositionShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		String eventId = "1";
		PlaceDTO currentPlace = new PlaceDTO();
		currentPlace.setGpsLat("1001");
		currentPlace.setGpsLong("2001");
		
		String restPath="users/"+userId+"/events/"+eventId+"/position";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(currentPlace, MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(Entity.entity(currentPlace, MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}
	}	
	
	@Test
	public void getEventsByUserShouldReturnAtLeastOneEvent(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";

		String restPath="users/"+userId+"/events";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
			final List<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<EventDTO>>(){});
			assertNotNull(entityFromJSON);
			assertTrue(entityFromJSON.size()>0);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
			List<EventDTO> entityFromXML = responseXML.readEntity(new GenericType<List<EventDTO>>(){});
			assertNotNull(entityFromXML);
			assertTrue(entityFromXML.size()>0);	
		}
	}
	
	@Test
	public void getUserShouldReturnOneUser(){
		Client client = WebServiceRestTest.getClientInstance();

		String requestedUserId = "1";

		String restPath="users/"+requestedUserId;
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
			UserDTO entityFromJSON = responseJSON.readEntity(UserDTO.class);
			assertNotNull(entityFromJSON);
			assertEquals(String.valueOf(entityFromJSON.getId()), requestedUserId);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
			UserDTO entityFromXML = responseXML.readEntity(UserDTO.class);
			assertNotNull(entityFromXML);
			assertEquals(String.valueOf(entityFromXML.getId()), requestedUserId);	
		}
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

		String userId = "1";
		String restPath="users/"+userId+"/images";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request();
			Response responseJSON = requestBuilderJSON.post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);	
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request();
			Response responseXML = requestBuilderXML.post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}

	}
	
//	@Test
//	public void createUserShouldReturnConfirm(){
//		Client client = WebServiceRestTest.getClientInstance();
//		
//		UserDTO userDTO = new UserDTO();
//		userDTO.setNome("Luca");
//		userDTO.setCognome("Pelosi");
//		userDTO.setEmail("lucap84@gmail.com");
//		userDTO.setSesso("M");
//		userDTO.setDataNascita("1984-02-09");
//
//		String restPath="users";
//		{//Test JSON
//			String restPathJSON=restPath+"/json";
//			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
//			Response responseJSON = requestBuilderJSON.post(Entity.entity(userDTO, MediaType.APPLICATION_JSON));
//			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
//		}
//		{//Test XML
//			String restPathXML=restPath+"/xml";
//			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
//			Response responseXML = requestBuilderXML.post(Entity.entity(userDTO, MediaType.APPLICATION_XML));
//			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
//		}
//	}
	
	public static void main(String[] args) {
//		UserServiceTest test = new UserServiceTest("1","99");
//		test.modifyUserDataShouldReturnConfirm();
	}
	
}
