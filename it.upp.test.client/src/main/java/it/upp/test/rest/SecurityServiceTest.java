package it.upp.test.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.sun.research.ws.wadl.HTTPMethods;


public class SecurityServiceTest extends WebServiceRestTest{
	
	@Test
	public void updatePasswordShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String enodedPassword = "a1x2c3v4b56n7h8jjj9j0";
		
		String restPath="security/"+email+"/password";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			requestBuilderJSON = super.addSecurityHeaders(requestBuilderJSON, HTTPMethods.PUT.name(), restPathJSON);
			Response responseJSON = requestBuilderJSON.put(Entity.entity(enodedPassword,MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			requestBuilderXML = super.addSecurityHeaders(requestBuilderXML, HTTPMethods.PUT.name(), restPathXML);
			Response responseXML = requestBuilderXML.put(Entity.entity(enodedPassword,MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void checkVerificationCodeShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String verificationCode = "1234567890";
		
		String restPath="security/"+email+"/verificationcode";
		
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  =  client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.put(Entity.entity(verificationCode,MediaType.APPLICATION_JSON));
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML	
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.put(Entity.entity(verificationCode,MediaType.APPLICATION_XML));
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void sendVerificationCodeShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String email = "lucap84@gmail.com";
		String restPath="security/"+email+"/verificationcode";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(null);
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML		
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(null);
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void logoutShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String userId = "1";
		String restPath="security/"+userId+"/logout";
		
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(null);
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML	
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(null);
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	@Test
	public void loginShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String username = "lucap84@gmail.com";
		String password = "mypassword";
		
		String restPath="security/login";
		{//Test JSON	
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON  = client.target(getBaseURI()).path(restPathJSON).
					queryParam("username", username).
					queryParam("password", password).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.post(null);
			checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		}
		
		{//Test XML	
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).
					queryParam("username", username).
					queryParam("password", password).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.post(null);
			checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
		}
	}	
	
	public static void main(String[] args) {
		new SecurityServiceTest().updatePasswordShouldReturnConfirm();
	}

}
