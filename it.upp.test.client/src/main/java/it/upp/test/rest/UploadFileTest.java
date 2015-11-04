package it.upp.test.rest;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class UploadFileTest {
	

	  public static void main(String[] args) {
		  HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httpPost = new HttpPost("http://localhost:8080/it.fff.business.service.webapp/restapi/users/1/images/json");

		  File f = new File("D:\\Users\\lpelosi\\Desktop\\imagetest.jpg");
		  FileBody uploadFilePart = new FileBody(f);
		  MultipartEntity reqEntity = new MultipartEntity();
		  reqEntity.addPart("file", uploadFilePart);
		  httpPost.setEntity(reqEntity);

		  try {
			HttpResponse response = httpclient.execute(httpPost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	  }

}
