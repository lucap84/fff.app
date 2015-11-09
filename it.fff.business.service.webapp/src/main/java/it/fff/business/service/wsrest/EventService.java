package it.fff.business.service.wsrest;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.clientserver.common.dto.*;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;	

@Component("eventService")
@Path("/events")
public class EventService extends ApplicationService{
	
	private static final Logger logger = LogManager.getLogger(EventService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	
	public EventService() {
		logger.debug("Service created");
	}
	
	@POST
	@Path("{eventID}/attendances/{attendanceId}/feedback/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO addFeedbackJSON(@Context HttpServletRequest request, AttendanceDTO attendance) throws BusinessException {
		return addFeedback(request, attendance);
	}	
	@POST
	@Path("{eventID}/attendances/{attendanceId}/feedback/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO addFeedbackXML(@Context HttpServletRequest request, AttendanceDTO attendance) throws BusinessException {
		return addFeedback(request, attendance);
	}	
	
	@POST
	@Path("{eventId}/attendances/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AttendanceDTO joinEventJSON(@Context HttpServletRequest request, AttendanceDTO attendanceToCreate) throws BusinessException {
		return joinEvent(request, attendanceToCreate);
	}	
	@POST
	@Path("{eventId}/attendances/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public AttendanceDTO joinEventXML(@Context HttpServletRequest request, AttendanceDTO attendanceToCreate) throws BusinessException {
		return joinEvent(request, attendanceToCreate);
	}	
	
	@DELETE
	@Path("{eventId}/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO cancelEventJSON(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return cancelEvent(request, eventId);
	}	
	@DELETE
	@Path("{eventId}/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO cancelEventXML(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return cancelEvent(request, eventId);
	}	
	
	@POST
	@Path("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO createEventJSON(@Context HttpServletRequest request, EventDTO eventToCreate) throws BusinessException {
		return createEvent(request, eventToCreate);
	}	
	@POST
	@Path("xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO createEventXML(@Context HttpServletRequest request, EventDTO eventToCreate) throws BusinessException {
		return createEvent(request, eventToCreate);
	}	
	
	@GET
	@Path("{eventId}/attendaces/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AttendanceDTO> getAttendancesByEventJSON(@Context HttpServletRequest request, 
														 @PathParam("eventId") String eventId) throws BusinessException {
		return this.getAttendancesByEvent(request, eventId);
	}
	
	@GET
	@Path("{eventId}/attendaces/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<AttendanceDTO> getAttendancesByEventXML(@Context HttpServletRequest request, 
														@PathParam("eventId") String eventId) throws BusinessException {
		return this.getAttendancesByEvent(request, eventId);
	}	


	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDTO> searchEventsJSON(	@Context HttpServletRequest request, 
											@QueryParam("posizione") String posizione,
											@QueryParam("categoria") String categoria,
											@QueryParam("partecipanti") int partecipanti) throws BusinessException {
		return this.searchEvents(request, posizione, categoria, partecipanti);
	}
	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventDTO> searchEventsXML(	@Context HttpServletRequest request, 
											@QueryParam("posizione") String posizione,
											@QueryParam("categoria") String categoria,
											@QueryParam("partecipanti") int partecipanti) throws BusinessException {
		return this.searchEvents(request, posizione, categoria, partecipanti);
	}	
	
	
	@GET
	@Path("{eventId}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public ReadResultDTO<EventDTO> getEventJSON(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return this.getEvent(request, eventId);
	}
	
	@GET
	@Path("{eventId}/xml")
	@Produces( MediaType.APPLICATION_XML)
	public ReadResultDTO<EventDTO> getEventXML(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return this.getEvent(request, eventId);
	}	
	
	
	/*
	 *	
	 *
	 *
	 *
	 *		Delegating methods 
	 *
	 *
	 *
	 *
	 *
	 */
	
	private WriteResultDTO addFeedback(HttpServletRequest request, AttendanceDTO attendance) {
		//feedback saved, return confirm
		
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(String.valueOf(attendance.getId()));
		
		return result;
	}		
	
	private AttendanceDTO joinEvent(HttpServletRequest request, AttendanceDTO attendanceToCreate) {
		AttendanceDTO createdAttendance = new AttendanceDTO();
		createdAttendance.setEvent(attendanceToCreate.getEvent());
		createdAttendance.setUser(attendanceToCreate.getUser());
		createdAttendance.setValid(true);
		
		return createdAttendance;
	}
	
	private WriteResultDTO cancelEvent(HttpServletRequest request, String eventId) {
		EventDTO deletedEvent = new EventDTO();
		deletedEvent.setEventId("1");
		
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(deletedEvent.getEventId());
		return result;
	}	
	
	private WriteResultDTO createEvent(HttpServletRequest request, EventDTO eventToCreate) {
		
		EventDTO e1 = new EventDTO();
		e1.setEventId("1");
		
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(e1.getEventId());
		return result;
	}
	
	private List<AttendanceDTO> getAttendancesByEvent(HttpServletRequest request, String eventId) {
		ArrayList<AttendanceDTO> arrayList = new ArrayList<AttendanceDTO>();
		AttendanceDTO a1 = new AttendanceDTO();
		a1.setValid(true);
		a1.setNumPartecipanti(100);
		arrayList.add(a1);
		return arrayList;
	}	
	
	private List<EventDTO> searchEvents(HttpServletRequest request, String posizione, String categoria, int partecipanti) {
		//		TODO
		ArrayList<EventDTO> arrayList = new ArrayList<EventDTO>();
		EventDTO e1 = new EventDTO();
		e1.setEventId("999");
		arrayList.add(e1);
		return arrayList;
	}	
	
	private ReadResultDTO<EventDTO> getEvent(HttpServletRequest request, String eventId){
		logger.info("Receiving getEvent request with param: {}",eventId);
		ReadResultDTO<EventDTO> resultDto = new ReadResultDTO<EventDTO>();
		EventDTO eventDTO = null;
		try {
		int eventIdInt = Integer.valueOf(eventId);
			eventDTO = businessServiceFacade.getEvent(eventIdInt);
			resultDto.setDto(eventDTO);
		} catch (BusinessException e) {
			eventDTO = new EventDTO();
			super.manageErrors(e, resultDto, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		catch (NumberFormatException e){
			logger.error(LogUtils.stackTrace2String(e));
		}
		if(eventDTO!=null){
			logger.info("Sending back the event retrieved");
		}
		return resultDto;		
	}
	

	public BusinessServiceFacade getBusinessServiceFacade() {
		return businessServiceFacade;
	}

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

}
