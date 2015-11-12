package it.fff.business.service.wsrest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.clientserver.common.dto.SubscriptionDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

@Component("premiumService")
@Path("/premium")
public class PremiumService extends ApplicationService {
	
	private static final Logger logger = LogManager.getLogger(PremiumService.class);

	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	
	public PremiumService() {
		logger.debug("Service created");
	}	
	
	@POST
	@Path("subscriptions/{userId}/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO upgradeToPremiumJSON(@Context HttpServletRequest request, @PathParam("userId") String userId, SubscriptionDTO subscription) throws BusinessException {
		return upgradeToPremium(request, userId, subscription);
	}
	@POST
	@Path("subscriptions/{userId}/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO upgradeToPremiumXML(@Context HttpServletRequest request, @PathParam("userId") String userId, SubscriptionDTO subscription) throws BusinessException {
		return upgradeToPremium(request, userId, subscription);
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
	
	private WriteResultDTO upgradeToPremium(HttpServletRequest request, String userId, SubscriptionDTO subscription) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.upgradeToPremium(userId, subscription);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
}
