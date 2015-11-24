package it.fff.client.stub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import it.fff.client.test.stub.WebServiceRestTest;
import it.fff.clientserver.common.dto.AttendanceDTO;
import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.dto.FeedbackDTO;
import it.fff.clientserver.common.dto.MessageDTO;
import it.fff.clientserver.common.dto.UserDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class EventServiceStub  extends StubService{

	public List<MessageDTO> getEventMessages(String eventId, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_getEventMessages, eventId);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.get();
		List<MessageDTO> entity = response.readEntity(new GenericType<List<MessageDTO>>(){});
		return entity;
	}	
	
	
	public WriteResultDTO postEventStandardMessage(String eventId,String attendanceId,String sdtMsgId, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_postEventStandardMessage, eventId,attendanceId,sdtMsgId);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(Entity.entity(null, mediaType));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}
	
	
	public WriteResultDTO postEventMessage(String eventId,String attendanceId,String message,String mediaType){
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_postEventMessage, eventId,attendanceId);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(Entity.entity(message, mediaType));
		assertEquals(200, response.getStatus());
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}	
	
	
	public WriteResultDTO cancelAttendance(String eventId, String attendanceToDel, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_cancelAttendance, eventId,attendanceToDel);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.delete();
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}	
	
	
	public WriteResultDTO addFeedback(AttendanceDTO attendanceToAddFeedback, String mediaType){
		Client client = super.getClientInstance();
		
		String eventId = attendanceToAddFeedback.getEvent().getId();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_addFeedback, eventId,attendanceToAddFeedback.getId());
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(Entity.entity(attendanceToAddFeedback, mediaType));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}	
	
	
	public WriteResultDTO joinEvent(AttendanceDTO attendanceToCreate, String mediaType){
		Client client = super.getClientInstance();
		
		String eventId = attendanceToCreate.getEvent().getId();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_joinEvent, eventId);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(Entity.entity(attendanceToCreate, mediaType));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}	
	
	
	public WriteResultDTO cancelEvent(String eventToCancel, String mediaType){ //annulla evento da parte dell'organizzatore
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_cancelEvent, eventToCancel);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.delete();
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}	
	
	
	public WriteResultDTO createEvent(EventDTO event, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_createEvent);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(Entity.entity(event, mediaType));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		return writeResult;
	}	
	
	
	public List<AttendanceDTO> getAttendacesByEvent(String eventId, String mediaType){
		Client client = super.getClientInstance();

		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_getAttendacesByEvent,eventId);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.get();
		List<AttendanceDTO> entity = response.readEntity(new GenericType<List<AttendanceDTO>>(){});
		return entity;
	}
	
	
	public EventDTO getEvent(String eventId,String mediaType){
		Client client = super.getClientInstance();

		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_getEvent,eventId);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.get();
		EventDTO entity = response.readEntity(EventDTO.class);
		return entity;
	}
	
	
	public List<EventDTO> searchEvents(String gpsLat, String gpsLong, String idCategoria, String partecipanti, String mediaType){
		Client client = super.getClientInstance();
		
		String restPath = super.getWsRspath(mediaType, StubService.WSRS_PATH_searchEvents);
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).
				queryParam("gpsLat", gpsLat).
				queryParam("gpsLong", gpsLong).
				queryParam("idCategoria", idCategoria).
				queryParam("partecipanti", partecipanti).request(mediaType);
		Response response = requestBuilder.get();
		List<EventDTO> entity = response.readEntity(new GenericType<List<EventDTO>>(){});
		return entity;
	}	
}
