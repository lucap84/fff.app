package it.fff.business.service.wsrest;

import java.io.InputStream;

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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;


import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.ProfileImageDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.dto.WriteResultDTO;
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
	 *	Delegating methods 
	 */
	
	private WriteResultDTO createUser(HttpServletRequest request, CreateUserDTO createUserDTO){
		logger.info("Receiving createUser request");
		WriteResultDTO writeResultDTO = new WriteResultDTO();
		
		try {
			UserDTO userdto = businessServiceFacade.createUser(createUserDTO);
			if(userdto!=null && userdto.getId()>0){
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
