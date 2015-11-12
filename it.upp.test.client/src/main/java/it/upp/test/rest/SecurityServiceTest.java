package it.upp.test.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class SecurityServiceTest extends WebServiceRestTest{
	
	@Test
	public void updatePasswordShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String enodedPassword = "a1x2c3v4b56n7h8jjj9j0";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("security").path(email).path("password").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).put(Entity.entity(enodedPassword,MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("security").path(email).path("password").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).put(Entity.entity(enodedPassword,MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void checkVerificationCodeShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String verificationCode = "1234567890";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("security").path(email).path("verificationcode").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).put(Entity.entity(verificationCode,MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("security").path(email).path("verificationcode").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).put(Entity.entity(verificationCode,MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void sendVerificationCodeShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("security").path(email).path("verificationcode").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(null);
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("security").path(email).path("verificationcode").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(null);
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void logoutShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("security").path(userId).path("logout").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(null);
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("security").path(userId).path("logout").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(null);
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void loginShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String username = "lucap84@gmail.com";
		String password = "mypassword";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("security").path("login").path("json").
				queryParam("username", username).
				queryParam("password", password);
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(null);
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("security").path("login").path("xml").
				queryParam("username", username).
				queryParam("password", password);
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(null);
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	

}
