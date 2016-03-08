package it.fff.business.service.wsrest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.clientserver.common.dto.*;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;

@Component("placeService")
@Path("/places")
public class PlaceService extends ApplicationService{

	private static final Logger logger = LogManager.getLogger(PlaceService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	
	public PlaceService() {
		logger.debug("Service created");
	}
	
	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlaceDTO> getPlacesByDescriptionJSON(@Context HttpServletRequest request, 
											  		 @QueryParam("description") String description,
											  		@QueryParam("userGpsLat") String userGpsLat,
													@QueryParam("userGpsLong") String userGpsLong) throws BusinessException {
		return this.getPlacesByDescription(request, description, userGpsLat, userGpsLong);
	}
	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<PlaceDTO> getPlacesByDescriptionXML(@Context HttpServletRequest request, 
													@QueryParam("description") String description,
											  		@QueryParam("userGpsLat") String userGpsLat,
													@QueryParam("userGpsLong") String userGpsLong) throws BusinessException {
		return this.getPlacesByDescription(request, description, userGpsLat, userGpsLong);
	}
	
	@GET
	@Path("cities/{cityName}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public CityDTO getCityByNameJSON(@Context HttpServletRequest request,
													 @PathParam("cityName") String cityName,
											  		 @QueryParam("nationCode") String nationCode)
											  		 throws BusinessException {
		return this.getCityByName(request, cityName, nationCode);
	}
	@GET
	@Path("cities/{cityName}/xml")
	@Produces(MediaType.APPLICATION_XML)
	public CityDTO getCityByNameXML(@Context HttpServletRequest request,
													 @PathParam("cityName") String cityName,
											  		 @QueryParam("nationKey") String nationKey)
											  		 throws BusinessException {
		return this.getCityByName(request, cityName, nationKey);
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
	
	private List<PlaceDTO> getPlacesByDescription(HttpServletRequest request, String description, String userGpsLat, String userGpsLong) {
		List<PlaceDTO> places;
		try {
			places = businessServiceFacade.getPlacesByDescription(description, userGpsLat, userGpsLong);
		} catch (BusinessException e) {
			places = new ArrayList<PlaceDTO>();
			logger.error(LogUtils.stackTrace2String(e));
		}
		return places;
	}
	
	private CityDTO getCityByName(HttpServletRequest request, String cityName, String nationCode) {
		CityDTO city = null;
		try {
			city = businessServiceFacade.getCityByName(cityName, nationCode);
		} catch (BusinessException e) {
			city = new CityDTO();
			logger.error(LogUtils.stackTrace2String(e));
		}
		return city;
	}	
}
