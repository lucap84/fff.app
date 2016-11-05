package it.fff.business.service.wsrest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.enums.FeedbackEnum;
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
	
	@PUT
	@Path("{userId}/position/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO setCurrentPositionJSON(@Context HttpServletRequest request,
												 @PathParam("userId") String userId,
												 PlaceDTO currentPosition) throws BusinessException {
		return setCurrentPosition(request, userId, currentPosition);
	}
	@PUT
	@Path("{userId}/position/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO setCurrentPositionXML(@Context HttpServletRequest request,
												 @PathParam("userId") String userId,
												 PlaceDTO currentPosition) throws BusinessException {
		return setCurrentPosition(request, userId, currentPosition);
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
	
	@GET
	@Path("{userId}/images/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileImageDTO getProfileImageJSON(	@Context HttpServletRequest request,
							 					@PathParam("userId") String userId) throws BusinessException {
		return this.getProfileImage(request,userId);
	}
	@GET
	@Path("{userId}/images/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ProfileImageDTO getProfileImageXML(	@Context HttpServletRequest request,
							 					@PathParam("userId") String userId) throws BusinessException {
		return this.getProfileImage(request,userId);
	}	
	
	@DELETE
	@Path("{userId}/events/{eventId}/attendances/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WriteResultDTO cancelAttendanceJSON(@Context HttpServletRequest request, @PathParam("userId") String userId, @PathParam("eventId") String eventId) throws BusinessException {
		return cancelAttendance(request, eventId, userId);
	}	
	@DELETE
	@Path("{userId}/events/{eventId}/attendances/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public WriteResultDTO cancelAttendanceXML(@Context HttpServletRequest request, @PathParam("userId") String userId, @PathParam("eventId") String eventId) throws BusinessException {
		return cancelAttendance(request, eventId, userId);
	}
	
	@GET
	@Path("emails/{email}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public EmailInfoDTO isExistingEmailSON(@Context HttpServletRequest request, @PathParam("email") String email) throws BusinessException {
		return isExistingEmail(request, email);
	}
	@GET
	@Path("emails/{email}/xml")
	@Produces(MediaType.APPLICATION_XML)
	public EmailInfoDTO isExistingEmailXML(@Context HttpServletRequest request, @PathParam("email") String email) throws BusinessException {
		return isExistingEmail(request, email);
	}
	
	@GET
	@Path("fb/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO getFacebookUserDataJSON(@Context HttpServletRequest request,
										@Context HttpHeaders headers,
										@QueryParam("token") String socialToken,
										@QueryParam("expires") int socialTokenExprires)
										throws BusinessException {
return getFacebookUserData(request, headers, socialToken, socialTokenExprires);
	}
	@GET
	@Path("fb/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public UserDTO getFacebookUserDataXML(@Context HttpServletRequest request,
												@Context HttpHeaders headers,
												@QueryParam("token") String socialToken,
												@QueryParam("expires") int socialTokenExprires)
												throws BusinessException {
		return getFacebookUserData(request, headers, socialToken, socialTokenExprires);
	}	
	
	@GET
	@Path("{userId}/feedbacks/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FeedbackEnum> getUserFeedbacksJSON(@Context HttpServletRequest request, 
											  @PathParam("userId") String userId) throws BusinessException {
		return this.getUserFeedbacks(request, userId);
	}
	@GET
	@Path("{userId}/feedbacks/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<FeedbackEnum> getUserFeedbacksXML(@Context HttpServletRequest request, 
											  @PathParam("userId") String userId) throws BusinessException {
		return this.getUserFeedbacks(request, userId);
	}
	
	@GET
	@Path("{userId}/attendances/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AttendanceDTO> getAttendancesByUserJSON(@Context HttpServletRequest request, 
											  @PathParam("userId") String userId) throws BusinessException {
		return this.getAttendancesByUser(request, userId);
	}
	@GET
	@Path("{userId}/attendances/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<AttendanceDTO> getAttendancesByUserXML(@Context HttpServletRequest request, 
											  @PathParam("userId") String userId) throws BusinessException {
		return this.getAttendancesByUser(request, userId);
	}
	
	@GET
	@Path("fb/{facebookId}/account/json")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountDTO getUserAccountByFacebookIdJSON(@Context HttpServletRequest request, @PathParam("facebookId") String facebookId) throws BusinessException {
		return getUserAccountByFacebookId(request, facebookId);
	}
	@GET
	@Path("fb/{facebookId}/account/xml")
	@Produces(MediaType.APPLICATION_XML)
	public AccountDTO getUserAccountByFacebookIdXML(@Context HttpServletRequest request, @PathParam("facebookId") String facebookId) throws BusinessException {
		return getUserAccountByFacebookId(request, facebookId);
	}
	
	@GET
	@Path("emails/{email}/account/json")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountDTO getUserAccountByEmailJSON(@Context HttpServletRequest request, @PathParam("email") String email) throws BusinessException {
		return getUserAccountByEmail(request, email);
	}
	@GET
	@Path("emails/{email}/account/xml")
	@Produces(MediaType.APPLICATION_XML)
	public AccountDTO getUserAccountByEmailXML(@Context HttpServletRequest request, @PathParam("email") String email) throws BusinessException {
		return getUserAccountByEmail(request, email);
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
		WriteResultDTO result;
		try {
			result = businessServiceFacade.updateUserData(user);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private WriteResultDTO setCurrentPosition(HttpServletRequest request, String userId, PlaceDTO currentPosition) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.setCurrentPosition(userId, currentPosition);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private List<EventDTO> getEventsByUser(HttpServletRequest request, String userId) {
		List<EventDTO> events = null;
		try {
			events = businessServiceFacade.getEventsByUser(userId);
		} catch (BusinessException e) {
			events = new ArrayList<EventDTO>();
			events.add(new EventDTO());
			super.manageErrors(e, events.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return events;
	}	
	
	private UserDTO getUser(HttpServletRequest request, int userId) {
		UserDTO dto = null;
		try {
			dto = businessServiceFacade.getUser(userId);
		} catch (BusinessException e) {
			dto = new UserDTO();
			super.manageErrors(e, dto, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return dto;
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
		
		String ext = "";
		if(fileDetail.getFileName()!=null && !"".equals(fileDetail.getFileName())){
			String[] split = fileDetail.getFileName().split("\\.");
			if(split.length>1){
				ext = split[1];
			}
		}
		imgDTOinput.setExtension(ext);
//		imgDTOinput.setType(fileDetail.getType());

		WriteResultDTO resultDTO = null;
		try {
			resultDTO = businessServiceFacade.updateProfileImage(imgDTOinput);
		} catch (BusinessException e) {
			resultDTO = new WriteResultDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		
		return resultDTO;
	}
	
	private ProfileImageDTO getProfileImage(HttpServletRequest request, String userId) {
		ProfileImageDTO resultDTO = null;
		try {
			resultDTO = businessServiceFacade.readProfileImage(userId);
		} catch (BusinessException e) {
			resultDTO = new ProfileImageDTO();
			super.manageErrors(e, resultDTO, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		
		return resultDTO;
	}
	
	private WriteResultDTO cancelAttendance(HttpServletRequest request, String eventId, String userId) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.cancelAttendance(eventId, userId);
		} catch (BusinessException e) {
			result = new WriteResultDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private EmailInfoDTO isExistingEmail(HttpServletRequest request, String email) {
		EmailInfoDTO result = null;
		try {
			result = businessServiceFacade.isExistingEmail(email);
		} catch (BusinessException e) {
			result = new EmailInfoDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private UserDTO getFacebookUserData(HttpServletRequest request, HttpHeaders headers, String socialToken, int socialTokenExpires){
		UserDTO result = null;
		
		String deviceId = null;
		List<String> devicesHeader = headers.getRequestHeader("Device");
		if(devicesHeader!=null && devicesHeader.size()>0){
			deviceId = devicesHeader.get(0);
		}
		
		try {
			result = businessServiceFacade.getFacebookUserData(socialToken, socialTokenExpires, deviceId);
		} catch (BusinessException e) {
			result = new UserDTO();
			logger.error(LogUtils.stackTrace2String(e));
		}		
		return result;
	}
	
	private List<FeedbackEnum> getUserFeedbacks(HttpServletRequest request, String userId) {
		List<FeedbackEnum> feedbacks = null;
		try {
			feedbacks = businessServiceFacade.getUserFeedbacks(userId);
		} catch (BusinessException e) {
			feedbacks = new ArrayList<FeedbackEnum>();
			//TODO Come gestire errore su enum???
			logger.error(LogUtils.stackTrace2String(e));
		}
		return feedbacks;
	}
	
	private List<AttendanceDTO> getAttendancesByUser(HttpServletRequest request, String userId) {
		List<AttendanceDTO> attendances;
		try {
			attendances = businessServiceFacade.getAttendancesByUser(userId);
		} catch (BusinessException e) {
			attendances = new ArrayList<AttendanceDTO>();
			attendances.add(new AttendanceDTO());
			super.manageErrors(e, attendances.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return attendances;
	}
	
	private AccountDTO getUserAccountByEmail(HttpServletRequest request, String email) {
		AccountDTO result = null;
		try {
			result = businessServiceFacade.getUserAccountByEmail(email);
		} catch (BusinessException e) {
			result = new AccountDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}
	
	private AccountDTO getUserAccountByFacebookId(HttpServletRequest request, String facebookId) {
		AccountDTO result = null;
		try {
			result = businessServiceFacade.getUserAccountByFacebookId(facebookId);
		} catch (BusinessException e) {
			result = new AccountDTO();
			super.manageErrors(e, result, request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
}
