package it.fff.business.service.util;

import javax.ws.rs.core.MediaType;

public class ServiceUtils {
	
	public static String mediaType2String(MediaType mediaType){
		StringBuilder typeSubtype = new StringBuilder();
		typeSubtype.append(mediaType.getType()).append("/").append(mediaType.getSubtype());
		
		return typeSubtype.toString();
	}

}
