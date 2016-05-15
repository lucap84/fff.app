package it.fff.business.service.wsrest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.clientserver.common.dto.*;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.enums.UserSexEnum;
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
									@QueryParam("token") String token) 
												 throws BusinessException {
		return getFacebookUserData(request, headers, token);
	}
	@GET
	@Path("fb/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public UserDTO getFacebookUserDataXML(@Context HttpServletRequest request,
												@Context HttpHeaders headers,
												@QueryParam("token") String token)   
												throws BusinessException {
		return getFacebookUserData(request, headers, token);
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
	
	private WriteResultDTO setCurrentPosition(HttpServletRequest request, String userId, String eventId, PlaceDTO place) {
		WriteResultDTO result;
		try {
			result = businessServiceFacade.setCurrentPosition(userId, eventId, place);
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
		imgDTOinput.setType(fileDetail.getType());

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
	
	private UserDTO getFacebookUserData(HttpServletRequest request, HttpHeaders headers, String token){
		UserDTO user = null;
		
        String graph = null;
        try {
            String uri = "https://graph.facebook.com/me?access_token=" + token;
            String fields = "&fields=id,name,first_name,last_name,age_range,link,gender,locale,picture,timezone,updated_time,verified,birthday";
            URL u = new URL(uri+fields);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");            
            in.close();
            graph = b.toString();
        } catch (Exception e) {
            logger.error("error during facebook graph call");
            return user;
        } 
        
        String facebookId = null;
        String firstName = null;
        String middleNames = null;
        String lastName = null;
        String email = null;
        String userBirthday = null;
        UserSexEnum gender = UserSexEnum.UNKNOWN;
        try {
            JSONObject json = new JSONObject(graph);
            String[] names = JSONObject.getNames(json);json.getString("updated_time");
            
            for (int i = 0; i < names.length; i++) {
				switch(names[i]){
					case "id": facebookId = json.getString("id"); break;
					case "first_name": firstName = json.getString("first_name"); break;
					case "middle_name": middleNames = json.getString("middle_name"); break;
					case "last_name": lastName = json.getString("last_name"); break;
					case "email": email = json.getString("email"); break;
					case "gender": {
						String g = json.getString("gender");
		                if (g.equalsIgnoreCase("female"))
		                    gender = UserSexEnum.F;
		                else if (g.equalsIgnoreCase("male"))
		                    gender = UserSexEnum.M;
		                else
		                    gender = UserSexEnum.UNKNOWN;
		                break;
					}
					case "birthday": userBirthday = json.getString("birthday"); break;
				}
			}
        } catch (JSONException e) {
            logger.error("invalid JSON structure");
            return user;
        }        
        
        user = new UserDTO();
        user.setFacebookId(Long.valueOf(facebookId));
        user.setNome(firstName);
        user.setCognome(lastName);
        user.setSesso(gender);
        user.setDataNascita(userBirthday);
        
        AccountDTO account = new AccountDTO();
        account.setEmail(email);
        account.setFlgValidita(true);
        
        user.setAccount(account);
        
		return user;
	}
	
	private List<FeedbackEnum> getUserFeedbacks(HttpServletRequest request, String userId) {
		List<FeedbackEnum> result = null;
		try {
			result = businessServiceFacade.getUserFeedbacks(userId);
		} catch (BusinessException e) {
			result = new ArrayList<FeedbackEnum>();
			logger.error(LogUtils.stackTrace2String(e));
		}
		return result;
	}	
	
}
