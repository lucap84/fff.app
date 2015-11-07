package it.fff.business.service.wsrest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.dto.AttendanceDTO;
import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.FeedbackDTO;
import it.fff.business.common.dto.PlaceDTO;
import it.fff.business.common.dto.ProfileImageDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.dto.WriteResultDTO;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;

@Component("userService")
@Path("/users")
public class UserService extends ApplicationService{
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;	
	
	public UserService() {
		logger.debug("Service created");
	}
	
	@PUT
	@Path("{userId}/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO updateUserDataJSON(@Context HttpServletRequest request, UserDTO user) throws BusinessException {
		return updateUserData(request, user);
	}
	@PUT
	@Path("{userId}/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO updateUserDataXML(@Context HttpServletRequest request,UserDTO user) throws BusinessException {
		return updateUserData(request, user);
	}	
	
	@POST
	@Path("{userId}/events/{eventId}/position/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO setCurrentPositionJSON(@Context HttpServletRequest request,
												 @PathParam("userId") String userId,
												 @PathParam("eventId") String eventId,
												 PlaceDTO place) throws BusinessException {
		return setCurrentPosition(request, userId, eventId, place);
	}
	@POST
	@Path("{userId}/events/{eventId}/position/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO setCurrentPositionXML(@Context HttpServletRequest request,
			 							@PathParam("userId") String userId,
			 							@PathParam("eventId") String eventId,
			 							PlaceDTO place) throws BusinessException {
		return setCurrentPosition(request, userId, eventId, place);
	}	
	

	@GET
	@Path("{userId}/events/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDTO> getEventsByUserJSON(@Context HttpServletRequest request, 
											  @PathParam("userId") String userId) throws BusinessException {
		return this.getEventsByUser(request, userId);
	}
	@GET
	@Path("{userId}/events/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventDTO> getEventsByUserXML(@Context HttpServletRequest request, 
			  								 @PathParam("userId") String userId) throws BusinessException {
		return this.getEventsByUser(request, userId);
	}	
	
	@GET
	@Path("{userId}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO getUserJSON(@Context HttpServletRequest request, @PathParam("userId") int userId) throws BusinessException {
		return getUser(request, userId);
	}
	@GET
	@Path("{userId}/xml")
	@Produces(MediaType.APPLICATION_XML)
	public UserDTO getUserXML(@Context HttpServletRequest request, @PathParam("userId") int userId) throws BusinessException {
		return getUser(request, userId);
	}	
	
	@POST
	@Path("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO createUserJSON(@Context HttpServletRequest request, CreateUserDTO createUserDTO) throws BusinessException {
		return createUser(request, createUserDTO);
	}
	@POST
	@Path("xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO createUserXML(@Context HttpServletRequest request, CreateUserDTO createUserDTO) throws BusinessException {
		return createUser(request, createUserDTO);
	}	
	
	@POST
	@Path("{userId}/images/json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO updateProfileImageJSON(	@Context HttpServletRequest request,
													@FormDataParam("file") InputStream uploadedInputStream, 
							 						@FormDataParam("file") FormDataContentDisposition fileDetail,
							 						@PathParam("userId") String userId) throws BusinessException {
		return this.updateProfileImage(request,uploadedInputStream,fileDetail,userId);
	}
	@POST
	@Path("{userId}/images/xml")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO updateProfileImageXML(	@Context HttpServletRequest request,
													@FormDataParam("file") InputStream uploadedInputStream, 
							 						@FormDataParam("file") FormDataContentDisposition fileDetail,
							 						@PathParam("userId") String userId) throws BusinessException {
		return this.updateProfileImage(request,uploadedInputStream,fileDetail,userId);
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
	
	private WriteResultDTO updateUserData(HttpServletRequest request, UserDTO user) {
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(user.getId());
		return result;
	}
	
	private WriteResultDTO setCurrentPosition(HttpServletRequest request, String userId, String eventId, PlaceDTO place) {
		WriteResultDTO result = new WriteResultDTO();
		result.setOk(true);
		result.setIdentifier(userId);
		return result;
	}
	
	private List<EventDTO> getEventsByUser(HttpServletRequest request, String userId) {
		ArrayList<EventDTO> arrayList = new ArrayList<EventDTO>();
		EventDTO e1 = new EventDTO();
		e1.setEventId("1");
		arrayList.add(e1);
		return arrayList;
	}	
	
	private UserDTO getUser(HttpServletRequest request, int userId) {
		UserDTO dto = new UserDTO();
		dto.setId("1");
		return dto;
	}
	
	private WriteResultDTO createUser(HttpServletRequest request, CreateUserDTO createUserDTO){
		logger.info("Receiving createUser request");
		WriteResultDTO writeResultDTO = new WriteResultDTO();
		
		try {
			UserDTO userdto = businessServiceFacade.createUser(createUserDTO);
			Integer id = Integer.valueOf(userdto.getId());
			if(userdto!=null && id>0){
				writeResultDTO.setOk(true);
				writeResultDTO.setAffectedRecords(1);
				writeResultDTO.setIdentifier(String.valueOf(userdto.getId()));
			}
			
		} catch (BusinessException e) {
			super.manageErrors(e, writeResultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));			
		}
		logger.info("Sending back the result");
		return writeResultDTO;		
	}
	
	private WriteResultDTO updateProfileImage(	HttpServletRequest request,
												InputStream uploadedInputStream, 
												FormDataContentDisposition fileDetail,
												String userId
												) {
		ProfileImageDTO imgDTOinput = new ProfileImageDTO();
		imgDTOinput.setImageInputStream(uploadedInputStream);
		imgDTOinput.setUserId(Integer.valueOf(userId));
		imgDTOinput.setFileName(fileDetail.getFileName());
		imgDTOinput.setName(fileDetail.getName());
		imgDTOinput.setParameters(fileDetail.getParameters());
		imgDTOinput.setSize(fileDetail.getSize());
		imgDTOinput.setType(fileDetail.getType());

		WriteResultDTO resultDTO = new WriteResultDTO();
		try {
			resultDTO = businessServiceFacade.updateProfileImage(imgDTOinput);
		} catch (BusinessException e) {
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		
		return resultDTO;
	}	
	
}
