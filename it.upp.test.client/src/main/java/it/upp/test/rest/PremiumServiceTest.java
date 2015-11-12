package it.upp.test.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import it.fff.clientserver.common.dto.SubscriptionDTO;

public class PremiumServiceTest extends WebServiceRestTest{

	@Test
	public void upgradeToPremiumShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		SubscriptionDTO subscription = new SubscriptionDTO();
		subscription.setDurata("3");

		WebTarget targetJSON = client.target(getBaseURI()).path("premium").path("subscriptions").path(userId).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(subscription, MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("premium").path("subscriptions").path(userId).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(subscription, MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}
	
}
