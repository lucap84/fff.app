package it.fff.business.service.wsrest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.IdentifierDTO;
import it.fff.business.common.util.Util;
import it.fff.business.common.util.UtilDTO;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;	

@Component("eventService")
@Path("/events")
public class EventService {
	
	private static final Logger logger = LogManager.getLogger(EventService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	
	public EventService() {
		logger.debug("Service created");
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public EventDTO getEvent(@QueryParam("json") String json) throws BusinessException {
		logger.info("Receiving getEvent request with param: {}",json);
		IdentifierDTO inputDTO = (IdentifierDTO)UtilDTO.encodedJSONString2DTO(json, IdentifierDTO.class.getName());
		EventDTO outputDTO = null;
		try {
			outputDTO = businessServiceFacade.getEvent(inputDTO);
		} catch (BusinessException e) {
			logger.error(Util.stackTrace2String(e));
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
