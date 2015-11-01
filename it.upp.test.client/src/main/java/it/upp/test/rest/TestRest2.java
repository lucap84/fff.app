package it.upp.test.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.IdentifierDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.common.util.DtoUtils;
import it.upp.test.provider.CreateUserDTOProvider;

public class TestRest2 {
	
	public static void main(String[] args) {
//		final Client client = ClientBuilder.newClient();
		Client client = ClientBuilder.newBuilder()
				.register(CreateUserDTOProvider.class).build();
		
//		WebTarget webTarget = client.target(getBaseURI());
		
//		IdentifierDTO identifierDTO = new IdentifierDTO();
//		identifierDTO.setId(1);
		
//		String dto2EncodedJSONString = DtoUtils.dto2EncodedJSONString(identifierDTO);
//		Response response = webTarget.path("events").queryParam("json", dto2EncodedJSONString)
//                .request().accept(MediaType.APPLICATION_JSON).get();
//		System.out.println(response.getStatus());
//		String myBeanXml = response.readEntity(String.class);
//		System.out.println(myBeanXml);
		
		
		final CreateUserDTO myBean = new CreateUserDTO();
		myBean.setNome("Luca");
		myBean.setCognome("Pelosi");
		myBean.setEmail("lucap84@gmail.com");

		Response response = client.target("http://localhost:8080/it.fff.business.service.webapp/restapi/users/json").
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(myBean, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		final UserDTO responseEntity = response.readEntity(UserDTO.class);
		System.out.println(responseEntity);	
		
//		Response response = webTarget.path("users").path("json").request("application/xml")
//		        .post(Entity.entity(myBean, "application/xml"));
//		 
//		System.out.println(response.getStatus());
//		final String responseEntity = response.readEntity(String.class);
//		System.out.println(responseEntity);		
	
	}

	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
		  }
}
