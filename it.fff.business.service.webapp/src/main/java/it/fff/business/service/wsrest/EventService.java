package it.fff.business.service.wsrest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.GetEventInputDTO;
import it.fff.business.common.util.UtilDTO;
import it.fff.business.facade.service.BusinessServiceFacade;

@Component("eventService")
@Path("/events")
public class EventService {
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	
	public EventService() {
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public EventDTO getEvent(@QueryParam("json") String json) {
		GetEventInputDTO dto = (GetEventInputDTO)UtilDTO.encodedJSONString2DTO(json, GetEventInputDTO.class.getName());
		return businessServiceFacade.getEvent(dto);
	}

	public BusinessServiceFacade getBusinessServiceFacade() {
		return businessServiceFacade;
	}

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

}
