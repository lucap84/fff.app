package it.fff.business.service.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.UserDTO;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class UserDTOMessageBodyRW implements MessageBodyWriter<UserDTO>{

	@Override
	public boolean isWriteable(Class<?> classType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return classType==UserDTO.class;
	}

	@Override
	public long getSize(UserDTO t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeTo(UserDTO userDTO, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {

		try {
			StringBuilder typeSubtype = new StringBuilder();
			typeSubtype.append(mediaType.getType()).append("/").append(mediaType.getSubtype());
			switch (typeSubtype.toString()) {
			case MediaType.APPLICATION_XML:
				toXML(userDTO, entityStream);				
				break;

			case MediaType.APPLICATION_JSON:
				toJSON(userDTO, entityStream);
				break;
			default:
				toXML(userDTO, entityStream);
				
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	private void toJSON(UserDTO user, OutputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		objectMapper.writeValue(entityStream, user);
	}

	private void toXML(UserDTO user, OutputStream entityStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(EventDTO.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(user, entityStream);
	}
}
