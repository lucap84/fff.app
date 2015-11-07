package it.upp.test.rest;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.service.provider.CreateUserDTOMessageBodyRW;
import it.fff.business.service.provider.WriteResultDTOMessageBodyRW;

public class UserServiceTest extends ServiceTest{
	
	@Test
	public void updateProfileImageShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(WriteResultDTOMessageBodyRW.class);
		client.register(MultiPartFeature.class);
		
		File f = new File("D:\\Users\\lpelosi\\Desktop\\imagetest.jpg");
		FileDataBodyPart uploadFilePart = new FileDataBodyPart("file",f);
		final FormDataMultiPart multipart = new FormDataMultiPart();
		multipart.bodyPart(uploadFilePart);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path("1").path("images").path("json");
		Response responseJSON = targetJSON.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		checkWriteResultJSON(responseJSON);	
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path("1").path("images").path("xml");
		Response responseXML = targetXML.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		checkWriteResultXML(responseXML);	

	}
	
	@Test
	public void createUserShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(CreateUserDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setNome("Luca");
		createUserDTO.setCognome("Pelosi");
		createUserDTO.setEmail("lucap84@gmail.com");
		
		WebTarget targetJSON = client.target(getBaseURI()).path("users").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(createUserDTO, MediaType.APPLICATION_JSON));
		checkWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("users").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(createUserDTO, MediaType.APPLICATION_XML));
		checkWriteResultXML(responseXML);	
	}
	
}
