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
import it.fff.business.common.dto.UserDTO;
import it.fff.business.service.provider.CreateUserDTOMessageBodyRW;
import it.fff.business.service.provider.UserDTOMessageBodyRW;

public class UserServiceTest {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newBuilder().build();
		client.register(CreateUserDTOMessageBodyRW.class);
		client.register(UserDTOMessageBodyRW.class);
		
		final CreateUserDTO createUserDTO = getCreateUSerDTO();

		WebTarget targetCreateUserJSON = client.target(getBaseURI());
		Response responseCreateUserJSON = targetCreateUserJSON.path("users").path("json").
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(createUserDTO, MediaType.APPLICATION_JSON));
		final UserDTO responseCreateUserJSONEntity = responseCreateUserJSON.readEntity(UserDTO.class);
		System.out.println("*************");
		System.out.println(responseCreateUserJSON.getStatus());
		System.out.println(responseCreateUserJSON.getMediaType());
		System.out.println(responseCreateUserJSONEntity);
		
		WebTarget targetCreateUserXML = client.target(getBaseURI());
		Response responseCreateUserXML = targetCreateUserXML.path("users").path("xml").
				request(MediaType.APPLICATION_XML).
				post(Entity.entity(createUserDTO, MediaType.APPLICATION_XML));
		final UserDTO responseCreateUserXMLEntity = responseCreateUserXML.readEntity(UserDTO.class);
		System.out.println("*************");
		System.out.println(responseCreateUserXML.getStatus());
		System.out.println(responseCreateUserXML.getMediaType());
		System.out.println(responseCreateUserXMLEntity);		
		
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
