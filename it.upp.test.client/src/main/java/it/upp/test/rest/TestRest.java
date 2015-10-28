package it.upp.test.rest;

import java.net.URI;
import it.fff.business.common.dto.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class TestRest {
	
	public static void main(String[] args) {
		final Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(getBaseURI());
		
//		String response1 = target.path("users").
//	              path("1").
//	              request().
//	              accept(MediaType.APPLICATION_JSON).
//	              get(String.class);
//		System.out.println(response1);
		
		
		Response response = target.path("users").path("1")
                .request().
                get();
		System.out.println(response.getStatus());
		String myBeanXml = response.readEntity(String.class);
		System.out.println(myBeanXml);	
		
		
		
//		EventDTO response2 = target.path("events").
//	              path("1").
//	              request().
//	              accept(MediaType.APPLICATION_JSON).
//	              get(EventDTO.class);	
//		System.out.println(response2);
		
		//	    final Order input = new Order();
//	    input.setId("1");
//	    input.setDescription("description");
//	    final Order output = client.resource(
//	"http://localhost:8080/orders/writeAndIncrementOrder").
//	            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
//	            entity(input).post(Order.class);		
	}

	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
		  }
}
