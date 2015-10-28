package it.fff.business.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.fff.business.common.dto.DataTransferObject;

public class UtilDTO {
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	public static String dto2JSONString(DataTransferObject dto){
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			System.out.println("JsonProcessingException!");
			return "";
		}
		return jsonString;
	}
	
	public static String dto2EncodedJSONString(DataTransferObject dto){
		String jsonString = dto2JSONString(dto);
		String encodedJSONString = "";
		if(jsonString!=null){
			try {
				encodedJSONString = URLEncoder.encode(jsonString, StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				System.out.println("UnsupportedEncodingException!");
				encodedJSONString = "";
			}
		}
		return encodedJSONString;
	}
	
	public static DataTransferObject jsonString2DTO(String jsonString, String className){
		DataTransferObject dto;
		try {
			dto = (DataTransferObject)mapper.readValue(jsonString, Class.forName(className));
		} catch (IOException e) {
			System.out.println("IOException!");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("IOException!");
			return null;
		}
		return dto;
	}
	
	public static DataTransferObject encodedJSONString2DTO(String encodedJsonString, String className){
		DataTransferObject dto = null;
		try {
			String decodedJsonString=URLDecoder.decode(encodedJsonString,  StandardCharsets.UTF_8.name());
			dto = UtilDTO.jsonString2DTO(decodedJsonString, className);
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException!");
			return null;
		}
		return dto;
		
	}
	
	public static String inputStream2String(InputStream is){
		String theString = "";
		try {
			theString = IOUtils.toString(is, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			System.out.println("IOException!");
		} 
		return theString;
	}
}
