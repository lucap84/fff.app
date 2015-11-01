package it.fff.business.service.wsrest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;


@Path("/users")
public class UserService extends ApplicationService{
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;	
	
	@POST
	@Path("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO createUserJSON(@Context HttpServletRequest request, CreateUserDTO createUserDTO) throws BusinessException {
		return createUser(request, createUserDTO);
	}
	
	@POST
	@Path("xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public UserDTO createUserXML(@Context HttpServletRequest request, CreateUserDTO createUserDTO) throws BusinessException {
		return createUser(request, createUserDTO);
	}	
	
	private UserDTO createUser(HttpServletRequest request, CreateUserDTO createUserDTO){
		logger.info("Receiving createUser request");
		UserDTO outputDTO = null;
		try {
			outputDTO = businessServiceFacade.createUser(createUserDTO);
		} catch (BusinessException e) {
			outputDTO = new UserDTO();
			super.manageErrors(e, outputDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));			
		}
		if(outputDTO!=null){
			logger.info("Sending back the user retrieved");
		}
		return outputDTO;		
	}
	
//	@Path("{userId}")  
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public UserDTO getUser(@PathParam("userId") int userId) {
//	  UserDTO user = new UserDTO();
//	  user.setUserId(userId);
//	 
//	  return user;
//	}

}
