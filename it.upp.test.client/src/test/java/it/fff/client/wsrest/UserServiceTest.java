package it.fff.client.wsrest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import it.fff.client.secure.ClientSecureConfiguration;
import it.fff.client.stub.SecurityServiceStub;
import it.fff.client.stub.UserServiceStub;
import it.fff.clientserver.common.dto.*;

public class UserServiceTest extends WebServiceRestTest{


	public UserServiceTest(){
	}	
	

	@Test
	public void modifyUserDataShouldReturnConfirm(){
		
		UserDTO  user = new UserDTO();
		user.setId("1");
		user.setNome("Nome mod");
		user.setCognome("cognome mod");
		
		WriteResultDTO result = null;

		{//Test JSON
			UserServiceStub stub = new UserServiceStub();
			result = stub.modifyUserData(user, MediaType.APPLICATION_JSON);
			assertNotNull(result);
			assertTrue(result.isOk());
			assertTrue(result.getAffectedRecords()>0);
			assertNotNull(result.getIdentifier());
			assertFalse(result.getIdentifier().isEmpty());
		}		
		
	}	
	
	@Test
	public void setCurrentPositionShouldReturnConfirm(){
		
		String userId = "1";
		String eventId = "1";
		PlaceDTO currentPlace = new PlaceDTO();
		currentPlace.setGpsLat("1001");
		currentPlace.setGpsLong("2001");
		
		WriteResultDTO result = null;

		{//Test JSON
			UserServiceStub stub = new UserServiceStub();
			result = stub.setCurrentPosition(userId, eventId, currentPlace, MediaType.APPLICATION_JSON);
			assertNotNull(result);
			assertTrue(result.isOk());
			assertTrue(result.getAffectedRecords()>0);
			assertNotNull(result.getIdentifier());
			assertFalse(result.getIdentifier().isEmpty());
		}	
	}	
	
	@Test
	public void getEventsByUserShouldReturnAtLeastOneEvent(){
		
		String userId = "1";

		List<EventDTO> result = null;

		{//Test JSON
			UserServiceStub stub = new UserServiceStub();
			result = stub.getEventsByUser(userId, MediaType.APPLICATION_JSON);
			assertNotNull(result);
			assertTrue(result.size()>0);
			assertNotNull(result.get(0));
			assertNotNull(result.get(0).getId());
		}
	}
	
	@Test
	public void getUserShouldReturnOneUser(){

		String userId = "1";

		UserDTO result = null;

		{//Test JSON
			UserServiceStub stub = new UserServiceStub();
			result = stub.getUser(userId, MediaType.APPLICATION_JSON);
			assertNotNull(result);
			assertTrue(result.isOk());
			assertNotNull(result.getId());
		}
	}
	
	@Test
	public void updateProfileImageShouldReturnConfirm(){
		
		String imageLocation ="C:\\Users\\lpelosi\\imagetest.jpg";
		String userId = "1";
		
		WriteResultDTO result = null;

		{//Test JSON
			UserServiceStub stub = new UserServiceStub();
			result = stub.updateProfileImage(userId, imageLocation, MediaType.APPLICATION_JSON);
			assertNotNull(result);
			assertTrue(result.isOk());
			assertTrue(result.getAffectedRecords()>0);
			assertNotNull(result.getIdentifier());
			assertFalse(result.getIdentifier().isEmpty());
		}	

	}
	
	public static void main(String[] args) {
//		UserServiceTest test = new UserServiceTest("1","99");
//		test.modifyUserDataShouldReturnConfirm();
	}
	
}
