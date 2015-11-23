package it.fff.client.stub;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import it.fff.client.wsrest.WebServiceRestTest;
import it.fff.clientserver.common.dto.SubscriptionDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class PremiumServiceStub  extends StubService{

	public WriteResultDTO upgradeToPremium(SubscriptionDTO subscription, String mediaType){
		Client client = super.getClientInstance();
		
		String userId = super.getSecureConfiguration().getUserId();

		String restPath="premium/subscriptions/"+userId+"/"+mediaType.toLowerCase().substring("application/".length());
		
		Builder requestBuilder  = client.target(getBaseURI()).path(restPath).request(mediaType);
		Response response = requestBuilder.post(Entity.entity(subscription, mediaType));
		WriteResultDTO writeResult = (WriteResultDTO)response.readEntity(WriteResultDTO.class);
		
		return writeResult;
	}
}
