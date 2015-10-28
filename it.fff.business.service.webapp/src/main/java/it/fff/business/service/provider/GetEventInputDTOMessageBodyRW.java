package it.fff.business.service.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import it.fff.business.common.dto.GetEventInputDTO;

@Provider
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class GetEventInputDTOMessageBodyRW implements MessageBodyWriter< GetEventInputDTO>, MessageBodyReader< GetEventInputDTO>{

	@Override
	public boolean isReadable(Class<?> classType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return classType==GetEventInputDTO.class;
	}

	@Override
	public GetEventInputDTO readFrom(Class<GetEventInputDTO> classType, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {
//		String jsonString = UtilDTO.inputStream2String(entityStream);
//		GetEventInputDTO dto = (GetEventInputDTO)UtilDTO.jsonString2DTO(jsonString, classType.getName());
//		
//		return dto;
		
		//UNUSED
		return null;
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return classType==GetEventInputDTO.class;
	}

	@Override
	public long getSize(GetEventInputDTO t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(GetEventInputDTO t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {
		// UNUSED
	}

}
