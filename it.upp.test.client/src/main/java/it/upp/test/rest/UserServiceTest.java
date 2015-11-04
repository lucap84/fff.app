package it.upp.test.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;


import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.DataTransferObject;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.dto.WriteResultDTO;
import it.fff.business.service.provider.CreateUserDTOMessageBodyRW;
import it.fff.business.service.provider.UserDTOMessageBodyRW;
import it.fff.business.service.provider.WriteResultDTOMessageBodyRW;

public class UserServiceTest {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newBuilder().build();
		client.register(CreateUserDTOMessageBodyRW.class);
		client.register(UserDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		final CreateUserDTO createUserDTO = getCreateUSerDTO();


		WebTarget targetCreateUserJSON = client.target(getBaseURI());
		Response responseCreateUserJSON = targetCreateUserJSON.path("users").path("json").
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(createUserDTO, MediaType.APPLICATION_JSON));
//		final UserDTO responseCreateUserJSONEntity = responseCreateUserJSON.readEntity(UserDTO.class);
		final WriteResultDTO responseWriteResultDTOJSONEntity = (WriteResultDTO)responseCreateUserJSON.readEntity(WriteResultDTO.class);
		System.out.println("*************");
		System.out.println(responseCreateUserJSON.getStatus());
		System.out.println(responseCreateUserJSON.getMediaType());
		System.out.println(responseWriteResultDTOJSONEntity);
		
		WebTarget targetCreateUserXML = client.target(getBaseURI());
		Response responseCreateUserXML = targetCreateUserXML.path("users").path("xml").
				request(MediaType.APPLICATION_XML).
				post(Entity.entity(createUserDTO, MediaType.APPLICATION_XML));
		final WriteResultDTO responseWriteResultDTOXMLEntity = (WriteResultDTO)responseCreateUserXML.readEntity(WriteResultDTO.class);
		System.out.println("*************");
		System.out.println(responseCreateUserXML.getStatus());
		System.out.println(responseCreateUserXML.getMediaType());
		System.out.println(responseWriteResultDTOXMLEntity);
	}

	public static CreateUserDTO getCreateUSerDTO(){
		CreateUserDTO myBean = new CreateUserDTO();
		myBean.setNome("Luca");
		myBean.setCognome("Pelosi");
		myBean.setEmail("lucap84@gmail.com");
		return myBean;
	}
	
	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
		  }
}
