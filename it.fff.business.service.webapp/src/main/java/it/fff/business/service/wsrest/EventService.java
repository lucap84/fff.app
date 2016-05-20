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
import it.fff.clientserver.common.enums.FeedbackEnum;
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
	public List<MessageDTO> getEventMessagesJSON(@Context HttpServletRequest request, 
												 @PathParam("eventId") String eventId) throws BusinessException {
		return this.getEventMessages(request, eventId);
	}
	@GET
	@Path("{eventId}/messages/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<MessageDTO> getEventMessagesXML(@Context HttpServletRequest request, 
												@PathParam("eventId") String eventId) throws BusinessException {
		return this.getEventMessages(request, eventId);
	}	
	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/standard/{sdtMsgId}/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO postEventStandardMessageJSON(@Context HttpServletRequest request,@PathParam("attendanceId") String attendanceId, @PathParam("sdtMsgId") String sdtMsgId) throws BusinessException {
		return postEventStandardMessage(request, attendanceId, sdtMsgId);
	}	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/standard/{sdtMsgId}/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO postEventStandardMessageXML(	@Context HttpServletRequest request,
														@PathParam("attendanceId") String attendanceId, 
														@PathParam("sdtMsgId") String sdtMsgId) throws BusinessException {
		return postEventStandardMessage(request, attendanceId, sdtMsgId);
	}	
	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO postEventMessageJSON( @Context HttpServletRequest request, 
												@PathParam("attendanceId") String attendanceId, 
												String message) throws BusinessException {
		return postEventMessage(request, attendanceId, message);
	}	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/messages/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO postEventMessageXML(	@Context HttpServletRequest request,
												@PathParam("attendanceId") String attendanceId, 
												String message) throws BusinessException {
		return postEventMessage(request, attendanceId, message);
	}	
	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/feedback/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO addFeedbackJSON(	@Context HttpServletRequest request, 
											@PathParam("eventId") String eventId,
											@PathParam("attendanceId") String attendanceId,
											FeedbackEnum feedback) throws BusinessException {
		return addFeedback(request, eventId, attendanceId, feedback);
	}	
	@POST
	@Path("{eventId}/attendances/{attendanceId}/feedback/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO addFeedbackXML(	@Context HttpServletRequest request, 
											@PathParam("eventId") String eventId,
											@PathParam("attendanceId") String attendanceId,
											FeedbackEnum feedback) throws BusinessException {
		return addFeedback(request, eventId, attendanceId, feedback);
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
	public WriteResultDTO cancelEventJSON(	@Context HttpServletRequest request, 
											@PathParam("eventId") String eventId,
											@QueryParam("organizerId") String organizerId) throws BusinessException {
		return cancelEvent(request, eventId, organizerId);
	}	
	@DELETE
	@Path("{eventId}/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO cancelEventXML(	@Context HttpServletRequest request, 
											@PathParam("eventId") String eventId,
											@QueryParam("organizerId") String organizerId) throws BusinessException {
		return cancelEvent(request, eventId, organizerId);
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
	@Path("{eventId}/attendances/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AttendanceDTO> getAttendancesByEventJSON(@Context HttpServletRequest request, 
														 @PathParam("eventId") String eventId) throws BusinessException {
		return this.getAttendancesByEvent(request, eventId);
	}
	@GET
	@Path("{eventId}/attendances/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<AttendanceDTO> getAttendancesByEventXML(@Context HttpServletRequest request, 
														@PathParam("eventId") String eventId) throws BusinessException {
		return this.getAttendancesByEvent(request, eventId);
	}	

	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDTO> searchEventsJSON(	@Context HttpServletRequest request, 
											@QueryParam("userGpsLat") String userGpsLat,
											@QueryParam("userGpsLong") String userGpsLong,
											@QueryParam("radiusKM") String radius,
											@QueryParam("desideredGpsLat") String desideredGpsLat,
											@QueryParam("desideredGpsLong") String desideredGpsLong,
											@QueryParam("idCategoria") String idCategoria,
											@QueryParam("partecipanti") String partecipanti) throws BusinessException {
		return this.searchEvents(request, userGpsLat, userGpsLong, radius, desideredGpsLat, desideredGpsLong, idCategoria,partecipanti);
	}
	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventDTO> searchEventsXML(	@Context HttpServletRequest request, 
											@QueryParam("userGpsLat") String userGpsLat,
											@QueryParam("userGpsLong") String userGpsLong,
											@QueryParam("radiusKM") String radius,
											@QueryParam("desideredGpsLat") String desideredGpsLat,
											@QueryParam("desideredGpsLong") String desideredGpsLong,
											@QueryParam("idCategoria") String idCategoria,
											@QueryParam("partecipanti") String partecipanti) throws BusinessException {
		return this.searchEvents(request, userGpsLat, userGpsLong, radius, desideredGpsLat, desideredGpsLong, idCategoria,partecipanti);
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
		List<MessageDTO> messages;
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
	
	private WriteResultDTO addFeedback(HttpServletRequest request, String eventId, String attendanceId, FeedbackEnum feedback) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.addFeedback(attendanceId, feedback);
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
	
	private WriteResultDTO cancelEvent(HttpServletRequest request, String eventId, String organizerId) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.cancelEvent(eventId, organizerId);
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
	
	private List<EventDTO> searchEvents(HttpServletRequest request, 
										String userGpsLat, 
										String userGpsLong, 
										String radiusKm,
										String desideredGpsLat, 
										String desideredGpsLong, 
										String idCategoria, 
										String partecipanti) {
		List<EventDTO> events = null;
		try {
			events = businessServiceFacade.searchEvents(userGpsLat, userGpsLong, radiusKm, desideredGpsLat, desideredGpsLong, idCategoria, partecipanti);
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
