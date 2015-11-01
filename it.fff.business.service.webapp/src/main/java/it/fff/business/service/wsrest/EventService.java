package it.fff.business.service.wsrest;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	
	
//	@GET
//	@Produces({MediaType.APPLICATION_JSON})
//	public EventDTO getEvent(@QueryParam("json") String json) throws BusinessException {
//		logger.info("Receiving getEvent request with param: {}",json);
//		IdentifierDTO inputDTO = (IdentifierDTO)UtilDTO.encodedJSONString2DTO(json, IdentifierDTO.class.getName());
//		EventDTO outputDTO = null;
//		try {
//			outputDTO = businessServiceFacade.getEvent(inputDTO);
//		} catch (BusinessException e) {
//			logger.error(Util.stackTrace2String(e));
//		}
//		if(outputDTO!=null){
//			logger.info("Sending back the event retrieved");
//		}
//		return outputDTO;
//	}

	public BusinessServiceFacade getBusinessServiceFacade() {
		return businessServiceFacade;
	}

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

}
