package it.upp.test.rest;

import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.secure.DHSecureConfiguration;
import it.upp.test.secure.ClientDHSecureConfiguration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.sun.research.ws.wadl.HTTPMethods;

import static org.junit.Assert.*;

import java.util.List;

public class EventServiceTest extends WebServiceRestTest{

	public EventServiceTest() {
		if(DHSecureConfiguration.SECURITY_ACTIVATED){
			ClientDHSecureConfiguration secureConfiguration = new ClientDHSecureConfiguration();
			SecurityServiceTest securityServiceTest = new SecurityServiceTest(null,secureConfiguration);
			String userId = securityServiceTest.registerUserShouldReturnConfirm();
			securityServiceTest.setExecutorId(userId);
			
			super.setExecutorId(userId);
			super.setSecureConfiguration(secureConfiguration);
		}
	}	
	
	@Test
	public void getEventMessagesShouldReturnAtLeastOneMessaget(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String restPath="events/"+eventId+"/messages";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.GET.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
			final List<MessageDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<MessageDTO>>(){});
			assertNotNull(entityFromJSON);
			assertTrue(entityFromJSON.size()>0);
		}
		
		{//Test XML			
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.GET.name(), restPathXML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
			List<MessageDTO> entityFromXML = responseXML.readEntity(new GenericType<List<MessageDTO>>(){});
			assertNotNull(entityFromXML);
			assertTrue(entityFromXML.size()>0);
		}
	}	
	
	@Test
	public void postEventStandardMessageShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String attendanceId = "1";
		String sdtMsgId = "1";
		String restPath="events/"+eventId+"/attendances/"+attendanceId+"/messages/standard";
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON)
					.queryParam("sdtMsgId", sdtMsgId).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.POST.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(null, MediaType.APPLICATION_JSON));
			assertEquals(200, responseJSON.getStatus());
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML			
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML)
						.queryParam("sdtMsgId", sdtMsgId).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.POST.name(), restPathXML);
			Response responseXML = requestBuilderXML.post(Entity.entity(null, MediaType.APPLICATION_XML));
			assertEquals(200, responseXML.getStatus());
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}
	
	@Test
	public void postEventMessageShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String attendanceId = "1";
		String message = "Very long message posted to server Very long message posted to server Very long message posted to server Very long message posted to server...";
		String restPath="events/"+eventId+"/attendances/"+attendanceId+"/messages";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.POST.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(message, MediaType.APPLICATION_JSON));
			assertEquals(200, responseJSON.getStatus());
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML		
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.POST.name(), restPathXML);
			Response responseXML = requestBuilderXML.post(Entity.entity(message, MediaType.APPLICATION_XML));
			assertEquals(200, responseXML.getStatus());
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}
	}	
	
	@Test
	public void cancelAttendanceShouldReturnConfirm(){//annulla partecipazione da parte di uno partecipante
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String attendanceToDel = "1";
		String restPath="events/"+eventId+"/attendances/"+attendanceToDel;
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.DELETE.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.delete();
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.DELETE.name(), restPathXML);
			Response responseXML = requestBuilderXML.delete();
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void addFeedbackShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		
		
		EventDTO event = new EventDTO();
		event.setId("1");
		UserDTO attendee = new UserDTO();
		attendee.setId("1");
		FeedbackDTO feedback = new FeedbackDTO();
		feedback.setPositiveFeedback(true);
		AttendanceDTO attendanceToAddFeedback = new AttendanceDTO();
		attendanceToAddFeedback.setId("1");
		attendanceToAddFeedback.setEvent(event);
		attendanceToAddFeedback.setUser(attendee);
		attendanceToAddFeedback.setOrganizer(false);
		attendanceToAddFeedback.setNumPartecipanti(22);
		attendanceToAddFeedback.setFeedback(feedback);
		
		String restPath="events/"+event.getId()+"/attendances/"+attendanceToAddFeedback.getId()+"/feedback";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.POST.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(attendanceToAddFeedback, MediaType.APPLICATION_JSON));
			assertEquals(200, responseJSON.getStatus());
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.POST.name(), restPathXML);
			Response responseXML = requestBuilderXML.post(Entity.entity(attendanceToAddFeedback, MediaType.APPLICATION_XML));
			assertEquals(200, responseXML.getStatus());
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}
	}	
	
	@Test
	public void joinEventShouldReturnAnAttendance(){
		Client client = WebServiceRestTest.getClientInstance();
		
		
		
		EventDTO event = new EventDTO();
		event.setId("1");
		UserDTO attendee = new UserDTO();
		attendee.setId("1");
		
		FeedbackDTO feedback = new FeedbackDTO();
		feedback.setPositiveFeedback(true);

		AttendanceDTO attendanceToCreate = new AttendanceDTO();
		attendanceToCreate.setId("1");
		attendanceToCreate.setEvent(event);
		attendanceToCreate.setUser(attendee);
		attendanceToCreate.setOrganizer(false);
		attendanceToCreate.setNumPartecipanti(22);
		attendanceToCreate.setFeedback(feedback);
		
		String restPath="events/"+event.getId()+"/attendances";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.POST.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(attendanceToCreate, MediaType.APPLICATION_JSON));
			assertEquals(200, responseJSON.getStatus());
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.POST.name(), restPathXML);
			Response responseXML = requestBuilderXML.post(Entity.entity(attendanceToCreate, MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void cancelEventShouldReturnConfirm(){ //annulla evento da parte dell'organizzatore
		Client client = WebServiceRestTest.getClientInstance();
		
		
		
		String eventToDelete = "1";
		String restPath="events/"+eventToDelete;
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.DELETE.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.delete();
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.DELETE.name(), restPathXML);
			Response responseXML = requestBuilderXML.delete();
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void createEventShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		
		
		UserDTO organizer = new UserDTO();
		organizer.setId("1");
		organizer.setNome("Nome organizer");
		organizer.setCognome("Cognome organizer");
		
		EventDTO dto = new EventDTO();
		dto.setNome("nuovo evento");
		dto.setUserOrganizer(organizer);
		
		String restPath="events";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.POST.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(dto, MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.POST.name(), restPathXML);
			Response responseXML = requestBuilderXML.post(Entity.entity(dto, MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}
	}	
	
	@Test
	public void getAttendacesByEventShouldReturnAtLeastOneAttendance(){
		Client client = WebServiceRestTest.getClientInstance();

		
		
		String requestedEventId = "1";
		
		String restPath="events/"+requestedEventId+"/attendaces";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.GET.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
			List<AttendanceDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<AttendanceDTO>>(){});
			assertNotNull(entityFromJSON);
			assertTrue(entityFromJSON.size()>0);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.GET.name(), restPathXML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
			List<AttendanceDTO> entityFromXML = responseXML.readEntity(new GenericType<List<AttendanceDTO>>(){});
			assertNotNull(entityFromXML);
			assertTrue(entityFromXML.size()>0);
		}
	}
	
	@Test
	public void getEventShouldReturnOneEvent(){
		Client client = WebServiceRestTest.getClientInstance();

		
		String requestedEventId = "1";
		
		String restPath="events/"+requestedEventId;
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.GET.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
	//		final ReadResultDTO<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<ReadResultDTO<EventDTO>>(){});
			final EventDTO entityFromJSON = responseJSON.readEntity(EventDTO.class);
			assertNotNull(entityFromJSON);
			assertEquals(entityFromJSON.getId(), requestedEventId);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.GET.name(), restPathXML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
	//		final ReadResultDTO<EventDTO> entityFromXML = responseXML.readEntity(new GenericType<ReadResultDTO<EventDTO>>(){});
			final EventDTO entityFromXML = responseXML.readEntity(EventDTO.class);
			assertNotNull(entityFromXML);
			assertEquals(entityFromXML.getId(), requestedEventId);
		}
	}
	
	@Test
	public void searchEventsShouldReturnAtLeastOneEvent(){
		Client client = WebServiceRestTest.getClientInstance();
		
		
		String gpsLat = "1.1234";
		String gpsLong = "2.4567";
		String idCategoria = "1";
		String partecipanti = "3";
		
		String restPath="events";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).
					queryParam("gpsLat", gpsLat).
					queryParam("gpsLong", gpsLong).
					queryParam("idCategoria", idCategoria).
					queryParam("partecipanti", partecipanti).request(MediaType.APPLICATION_JSON);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.GET.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
			final List<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<EventDTO>>(){});
			assertNotNull(entityFromJSON);
			assertTrue(entityFromJSON.size()>0);
		}
		
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).
					queryParam("gpsLat", gpsLat).
					queryParam("gpsLong", gpsLong).
					queryParam("idCategoria", idCategoria).
					queryParam("partecipanti", partecipanti).request(MediaType.APPLICATION_XML);
			if(DHSecureConfiguration.SECURITY_ACTIVATED) requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.GET.name(), restPathXML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
			List<EventDTO> entityFromXML = responseXML.readEntity(new GenericType<List<EventDTO>>(){});
			assertNotNull(entityFromXML);
			assertTrue(entityFromXML.size()>0);
		}
	}	
	
	public static void main(String[] args) {
//		EventServiceTest eventServiceTest = new EventServiceTest("1");
//		eventServiceTest.joinEventShouldReturnAnAttendance();
//		eventServiceTest.getEventShouldReturnOneEvent();
	}
	
}
