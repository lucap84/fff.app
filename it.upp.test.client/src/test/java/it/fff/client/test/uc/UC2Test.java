package it.fff.client.test.uc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import it.fff.client.stub.EventServiceStub;
import it.fff.client.stub.SecurityServiceStub;
import it.fff.client.stub.UserServiceStub;
import it.fff.clientserver.common.dto.AttendanceDTO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.dto.LoginDataRequestDTO;
import it.fff.clientserver.common.dto.UserDTO;

public class UC2Test {

	
	@Test
	public void testUC2(){ //Ricerca evento
		/*
		 * Preconditions: Register OR Login 
		 * Login
		 * SearchEvents
		 * getEvent
		 * getAttendancesByEvent
		 * getUser()
		 */
		EventServiceStub eventService = new EventServiceStub();
		UserServiceStub userService = new UserServiceStub();

		/*
		 * Register
		 */
		UC1Test registerTest = new UC1Test();
		registerTest.testUC1();
		
		/*
		 * Login
		 */
//		UC9Test loginTest = new UC9Test();
//		loginTest.testUC9();
		
		/*
		 * SearchEvents
		 */
		String gpsLat = "1.1234";
		String gpsLong = "2.4567";
		String idCategoria = "1";
		String partecipanti = "3";
		
		List<EventDTO> searchEventsOutput = eventService.searchEvents(gpsLat, gpsLong, idCategoria, partecipanti, MediaType.APPLICATION_JSON);
		assertNotNull(searchEventsOutput);
		assertTrue(searchEventsOutput.size()>0);
		assertNotNull(searchEventsOutput.get(0));
		assertNotNull(searchEventsOutput.get(0).getId());
		
		/*
		 * getEvent
		 */
		String eventId = searchEventsOutput.get(0).getId();
		EventDTO getEventOutput = eventService.getEvent(eventId, MediaType.APPLICATION_JSON);
		assertNotNull(getEventOutput);
		assertNotNull(getEventOutput.getId());
		assertNotNull(getEventOutput.getDescrizione());
		/*
		 * getAttendancesByEvent
		 */
		String eventId2 = getEventOutput.getId();
		List<AttendanceDTO> getAttendacesByEventOutput = eventService.getAttendacesByEvent(eventId2, MediaType.APPLICATION_JSON);
		assertNotNull(getAttendacesByEventOutput);
		assertTrue(getAttendacesByEventOutput.size()>0);
		assertNotNull(getAttendacesByEventOutput.get(0));
		assertNotNull(getAttendacesByEventOutput.get(0).getId());
		
		/*
		 * getUser
		 */
		String userId = getAttendacesByEventOutput.get(0).getUser().getId();
		UserDTO getUserOutput = userService.getUser(userId, MediaType.APPLICATION_JSON);
		assertNotNull(getUserOutput);
		assertTrue(getUserOutput.isOk());
		assertNotNull(getUserOutput.getId());
	}
}
