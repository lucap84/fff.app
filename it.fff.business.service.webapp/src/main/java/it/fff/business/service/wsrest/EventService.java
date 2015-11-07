package it.fff.business.service.wsrest;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.dto.EventDTO;
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
	
	private List<EventDTO> searchEvents(HttpServletRequest request, String posizione, String categoria, int partecipanti) {
		//		TODO
		ArrayList<EventDTO> arrayList = new ArrayList<EventDTO>();
		EventDTO e1 = new EventDTO();
		e1.setEventId("999");
		arrayList.add(e1);
		return arrayList;
	}	
	
	private EventDTO getEvent(HttpServletRequest request, String eventId){
		logger.info("Receiving getEvent request with param: {}",eventId);
		EventDTO outputDTO = null;
		try {
		int eventIdInt = Integer.valueOf(eventId);
			outputDTO = businessServiceFacade.getEvent(eventIdInt);
		} catch (BusinessException e) {
			outputDTO = new EventDTO();
			super.manageErrors(e, outputDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		catch (NumberFormatException e){
			logger.error(LogUtils.stackTrace2String(e));
		}
		if(outputDTO!=null){
			logger.info("Sending back the event retrieved");
		}
		return outputDTO;		
	}
	

	public BusinessServiceFacade getBusinessServiceFacade() {
		return businessServiceFacade;
	}

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

}
