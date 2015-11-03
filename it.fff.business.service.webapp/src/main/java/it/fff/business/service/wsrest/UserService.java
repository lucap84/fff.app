package it.fff.business.service.wsrest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;


import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;


@Path("/users")
public class UserService extends ApplicationService{
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/lpelosi/Upload_Files/";
	
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
	
	
	@POST
	@Path("{userId}/images")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream, 
							 @FormDataParam("file") FormDataContentDisposition fileDetail,
							 @PathParam("userId") String userId) throws BusinessException {

		String filePath = SERVER_UPLOAD_LOCATION_FOLDER +userId+"/"+fileDetail.getFileName();
		saveFile(uploadedInputStream, filePath);
		String output = "File saved to server location : " + filePath;
//		return Response.status(200).entity(output).build();
		return output;
	}	
	
	
	
	/*
	 *	Delegating methods 
	 */
	
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
	
	// save uploaded file to a defined location on the server
	    private void saveFile(InputStream uploadedInputStream,
	            String serverLocation) {
	        try {
	            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
	            int read = 0;
	            byte[] bytes = new byte[1024];
	 
	            outpuStream = new FileOutputStream(new File(serverLocation));
	            while ((read = uploadedInputStream.read(bytes)) != -1) {
	                outpuStream.write(bytes, 0, read);
	            }
	            outpuStream.flush();
	            outpuStream.close();
	        } catch (IOException e) {
	 
	            e.printStackTrace();
	        }
	 
	    }
	

}
