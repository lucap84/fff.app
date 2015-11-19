package it.upp.test.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import it.fff.clientserver.common.dto.SubscriptionDTO;
import it.upp.test.secure.ClientDHSecureConfiguration;

public class PremiumServiceTest extends WebServiceRestTest{

	public PremiumServiceTest(String userExecutorId, ClientDHSecureConfiguration secureConf) {
		super(userExecutorId, secureConf);
	}	
	
	public PremiumServiceTest(String userExecutorId) {
		super(userExecutorId, new ClientDHSecureConfiguration());
	}

	@Test
	public void upgradeToPremiumShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		SubscriptionDTO subscription = new SubscriptionDTO();
		subscription.setDurata("3");

		String restPath="premium/subscriptions/"+userId;
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(Entity.entity(subscription, MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(Entity.entity(subscription, MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
		}
	}
	
	public static void main(String[] args) {
		new PremiumServiceTest("1").upgradeToPremiumShouldReturnConfirm();
	}
	
}
