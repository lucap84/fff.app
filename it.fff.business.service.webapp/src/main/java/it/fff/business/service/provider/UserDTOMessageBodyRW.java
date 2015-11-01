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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.service.util.ServiceUtils;

@Provider
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class UserDTOMessageBodyRW implements MessageBodyWriter<UserDTO>, MessageBodyReader<UserDTO>{
	
	private static final Logger logger = LogManager.getLogger(UserDTOMessageBodyRW.class);

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
	public void writeTo(	UserDTO userDTO, 
							Class<?> classType, 
							Type genericType, 
							Annotation[] annotations, 
							MediaType mediaType,
							MultivaluedMap<String, Object> httpHeaders, 
							OutputStream entityStream)
					throws IOException, WebApplicationException {

		try {
			String typeSubtype = ServiceUtils.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
			switch (typeSubtype) {
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

	
	/*
	 * MessageBodyReader methods
	 */
	
	@Override
	public boolean isReadable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==UserDTO.class;
	}

	@Override
	public UserDTO readFrom(	Class<UserDTO> classType, 
								Type type, 
								Annotation[] annotations, 
								MediaType mediaType,
								MultivaluedMap<String, String> httpHeaders, 
								InputStream inputStream) throws IOException, WebApplicationException {
		UserDTO userDTO = null;
		try {
			String typeSubtype = ServiceUtils.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
			switch (typeSubtype) {
			case MediaType.APPLICATION_XML:
				userDTO = fromXML(inputStream);				
				break;

			case MediaType.APPLICATION_JSON:
				userDTO = fromJSON(inputStream);
				break;
			default:
				userDTO = fromXML(inputStream);
				
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return userDTO;
	}
	
/*
 *  utility methods	
 */
	
	private void toJSON(UserDTO user, OutputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		objectMapper.writeValue(entityStream, user);
	}
	
	private UserDTO fromJSON(InputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		UserDTO readValue = objectMapper.readValue(entityStream, UserDTO.class);
		return readValue;
	}	

	private void toXML(UserDTO user, OutputStream entityStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(EventDTO.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(user, entityStream);
	}
	
	private UserDTO fromXML(InputStream entityStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(UserDTO.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		UserDTO myBean = (UserDTO)unmarshaller.unmarshal(entityStream);
        return myBean;
	}	
}
