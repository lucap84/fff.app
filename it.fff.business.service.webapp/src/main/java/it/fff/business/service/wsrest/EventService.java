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
	
	@GET
	@Path("{eventId}/messages/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MessageDTO> getEventMessagesJSON(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return this.getEventMessages(request, eventId);
	}
	@GET
	@Path("{eventId}/messages/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<MessageDTO> getEventMessagesXML(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return this.getEventMessages(request, eventId);
	}	
	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/standard/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO postEventStandardMessageJSON(@Context HttpServletRequest request,@PathParam("attendanceId") String attendanceId, @QueryParam("sdtMsgId") String sdtMsgId) throws BusinessException {
		return postEventStandardMessage(request, attendanceId, sdtMsgId);
	}	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/standard/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO postEventStandardMessageXML(@Context HttpServletRequest request,@PathParam("attendanceId") String attendanceId, @QueryParam("sdtMsgId") String sdtMsgId) throws BusinessException {
		return postEventStandardMessage(request, attendanceId, sdtMsgId);
	}	
	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO postEventMessageJSON(@Context HttpServletRequest request, @PathParam("attendanceId") String attendanceId, String message) throws BusinessException {
		return postEventMessage(request, attendanceId, message);
	}	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO postEventMessageXML(@Context HttpServletRequest request, @PathParam("attendanceId") String attendanceId, String message) throws BusinessException {
		return postEventMessage(request, attendanceId, message);
	}	
	
	@DELETE
	@Path("{eventId}/attendances/{attendanceId}/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO cancelAttendanceJSON(@Context HttpServletRequest request, @PathParam("eventId") String eventId, @PathParam("attendanceId") String attendanceId) throws BusinessException {
		return cancelAttendance(request, eventId, attendanceId);
	}	
	@DELETE
	@Path("{eventId}/attendances/{attendanceId}/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO cancelAttendanceXML(@Context HttpServletRequest request, @PathParam("eventId") String eventId, @PathParam("attendanceId") String attendanceId) throws BusinessException {
		return cancelAttendance(request, eventId, attendanceId);
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
	public WriteResultDTO joinEventJSON(@Context HttpServletRequest request, AttendanceDTO attendanceToCreate) throws BusinessException {
		return joinEvent(request, attendanceToCreate);
	}	
	@POST
	@Path("{eventId}/attendances/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO joinEventXML(@Context HttpServletRequest request, AttendanceDTO attendanceToCreate) throws BusinessException {
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
	public EventDTO getEventJSON(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
		return this.getEvent(request, eventId);
	}
	
	@GET
	@Path("{eventId}/xml")
	@Produces( MediaType.APPLICATION_XML)
	public EventDTO getEventXML(@Context HttpServletRequest request, @PathParam("eventId") String eventId) throws BusinessException {
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
	
	private List<MessageDTO> getEventMessages(HttpServletRequest request, String eventId) {
		ArrayList<MessageDTO> messages;
		try {
			messages = businessServiceFacade.getEventMessages(eventId);
		} catch (BusinessException e) {
			messages = new ArrayList<MessageDTO>();
			logger.error(LogUtils.stackTrace2String(e));
		}
		return messages;
	}	
	
	private WriteResultDTO postEventStandardMessage(HttpServletRequest request, String attendanceId,	String stdMsgId) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.postStandardEventMessage(attendanceId, stdMsgId);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
	private WriteResultDTO postEventMessage(HttpServletRequest request, String attendanceId, String message) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.postEventMessage(attendanceId, message);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
	private WriteResultDTO cancelAttendance(HttpServletRequest request, String eventId, String attendanceId) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.cancelAttendance(eventId, attendanceId);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
	private WriteResultDTO addFeedback(HttpServletRequest request, AttendanceDTO attendance) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.addFeedback(attendance);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}		
	
	private WriteResultDTO joinEvent(HttpServletRequest request, AttendanceDTO attendanceToCreate) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.joinEvent(attendanceToCreate);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private WriteResultDTO cancelEvent(HttpServletRequest request, String eventId) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.cancelEvent(eventId);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
	private WriteResultDTO createEvent(HttpServletRequest request, EventDTO eventToCreate) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.createEvent(eventToCreate);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private List<AttendanceDTO> getAttendancesByEvent(HttpServletRequest request, String eventId) {
		List<AttendanceDTO> attendances;
		try {
			attendances = businessServiceFacade.getAttendancesByEvent(eventId);
		} catch (BusinessException e) {
			attendances = new ArrayList<AttendanceDTO>();
			logger.error(LogUtils.stackTrace2String(e));
		}
		return attendances;
	}	
	
	private List<EventDTO> searchEvents(HttpServletRequest request, String posizione, String categoria, int partecipanti) {
		ArrayList<EventDTO> events = null;
		try {
			events = businessServiceFacade.searchEvents(posizione, categoria, partecipanti);
		} catch (BusinessException e) {
			events = new ArrayList<EventDTO>();
			logger.error(LogUtils.stackTrace2String(e));
		}
		return events;
	}	
	
	private EventDTO getEvent(HttpServletRequest request, String eventId){
		logger.info("Receiving getEvent request with param: {}",eventId);
		EventDTO eventDTO = null;
		try {
		int eventIdInt = Integer.valueOf(eventId);
			eventDTO = businessServiceFacade.getEvent(eventIdInt);
		} catch (BusinessException e) {
			eventDTO = new EventDTO();
			super.manageErrors(e, eventDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		catch (NumberFormatException e){
			logger.error(LogUtils.stackTrace2String(e));
		}
		if(eventDTO!=null){
			logger.info("Sending back the event retrieved");
		}
		return eventDTO;		
	}
	

	public BusinessServiceFacade getBusinessServiceFacade() {
		return businessServiceFacade;
	}

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

}
