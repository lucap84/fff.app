package it.fff.client.stub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import it.fff.client.wsrest.WebServiceRestTest;
import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.dto.PlaceDTO;
import it.fff.clientserver.common.dto.UserDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class UserServiceStub  extends StubService{

	public WriteResultDTO modifyUserData(UserDTO  userToUpdate, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath="users/"+userToUpdate.getId()+"/"+mediaType.toLowerCase().substring("application/".length());
		
		Builder requestBuilder = client.target(getBaseURI()).path(restPath).request(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.put(Entity.entity(userToUpdate, MediaType.APPLICATION_JSON));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		
		return writeResult;
	}	
	
	public WriteResultDTO setCurrentPosition(String userId, String eventId, PlaceDTO currentPlace, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath="users/"+userId+"/events/"+eventId+"/position/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder = client.target(getBaseURI()).path(restPath).request(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.post(Entity.entity(currentPlace, MediaType.APPLICATION_JSON));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		
		return writeResult;
	}	
	
	public List<EventDTO> getEventsByUser(String userId, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath="users/"+userId+"/events/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder = client.target(getBaseURI()).path(restPath).request(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		final List<EventDTO> entity = response.readEntity(new GenericType<List<EventDTO>>(){});
		
		return entity;
	}
	
	public UserDTO getUser(String userId, String mediaType){
		Client client = super.getClientInstance();

		String restPath="users/"+userId+"/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder = client.target(getBaseURI()).path(restPath).request(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		UserDTO entity = response.readEntity(UserDTO.class);
		
		return entity;
	}
	
	public WriteResultDTO updateProfileImage(String userId, String imageLocation, String mediaType){
		Client client = super.getClientInstance();
		
		File f = new File(imageLocation);
		FileDataBodyPart uploadFilePart = new FileDataBodyPart("file",f);
		final FormDataMultiPart multipart = new FormDataMultiPart();
		multipart.bodyPart(uploadFilePart);

		String restPath="users/"+userId+"/images/"+mediaType.toLowerCase().substring("application/".length());
		Builder requestBuilder = client.target(getBaseURI()).path(restPath).request();
		Response response = requestBuilder.post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);	

		return writeResult;
	}
}
