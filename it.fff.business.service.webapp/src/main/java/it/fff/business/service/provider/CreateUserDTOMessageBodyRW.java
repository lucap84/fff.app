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

import it.fff.business.common.dto.CreateUserDTO;
import it.fff.business.common.dto.UserDTO;
import it.fff.business.service.util.ServiceUtils;

@Provider
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CreateUserDTOMessageBodyRW implements MessageBodyWriter<CreateUserDTO>, MessageBodyReader<CreateUserDTO>{
	
	private static final Logger logger = LogManager.getLogger(CreateUserDTOMessageBodyRW.class);

	@Override
	public boolean isWriteable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==CreateUserDTO.class;
	}

	@Override
	public void writeTo(	CreateUserDTO createUserDTO, 
							Class<?> classType, 
							Type type, 
							Annotation[] annotations, 
							MediaType mediaType,
							MultivaluedMap<String, Object> httpHeaders, 
							OutputStream entityStream) throws IOException, WebApplicationException {
		String typeSubtype = ServiceUtils.mediaType2String(mediaType);
		logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
		try {
			switch (typeSubtype.toString()) {
			case MediaType.APPLICATION_XML:
				toXML(createUserDTO, entityStream);				
				break;

			case MediaType.APPLICATION_JSON:
				toJSON(createUserDTO, entityStream);
				break;
			default:
				toXML(createUserDTO, entityStream);
				
			}
		} catch (JAXBException e) {
			logger.error("Errore durante la conversione di CreateUserDTO in {}; Message: ",typeSubtype,e.getMessage());
			e.printStackTrace();
		}
		
	}

	/*
	 * MessageBodyReader methods
	 */
	
	@Override
	public boolean isReadable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==CreateUserDTO.class;
	}

	@Override
	public CreateUserDTO readFrom(	Class<CreateUserDTO> classType, 
									Type type, 
									Annotation[] annotations, 
									MediaType mediaType,
									MultivaluedMap<String, String> httpHeaders, 
									InputStream inputStream) throws IOException, WebApplicationException {
		CreateUserDTO createUserDTO = null;
		try {
			String typeSubtype = ServiceUtils.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
			switch (typeSubtype) {
			case MediaType.APPLICATION_XML:
				createUserDTO = fromXML(inputStream);				
				break;

			case MediaType.APPLICATION_JSON:
				createUserDTO = fromJSON(inputStream);
				break;
			default:
				createUserDTO = fromXML(inputStream);
				
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return createUserDTO;
	}

	@Override
	public long getSize(CreateUserDTO arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 *  utility methods	
	 */
		
		private void toJSON(CreateUserDTO user, OutputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			objectMapper.writeValue(entityStream, user);
		}
		
		private CreateUserDTO fromJSON(InputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			CreateUserDTO readValue = objectMapper.readValue(entityStream, CreateUserDTO.class);
			return readValue;
		}	

		private void toXML(CreateUserDTO user, OutputStream entityStream) throws JAXBException {
			JAXBContext jaxbContext = JAXBContext.newInstance(CreateUserDTO.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(user, entityStream);
		}
		
		private CreateUserDTO fromXML(InputStream entityStream) throws JAXBException {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			CreateUserDTO myBean = (CreateUserDTO)unmarshaller.unmarshal(entityStream);
	        return myBean;
		}	
}
