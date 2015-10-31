package it.fff.business.service.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.service.util.ServiceUtils;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EventDTOMessageBodyRW implements MessageBodyWriter<EventDTO>, MessageBodyReader<EventDTO>{

	private static final Logger logger = LogManager.getLogger(EventDTOMessageBodyRW.class);
	/*
	 * MessageBodyWriter methods
	 */
	
	@Override
	public long getSize(EventDTO arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return classType==EventDTO.class;
	}

	@Override
	public void writeTo(EventDTO event, 
						Class<?> classType, 
						Type genericType, 
						Annotation[] annotations, 
						MediaType mediaType,
						MultivaluedMap<String, Object> httpHeaders, 
						OutputStream entityStream) throws IOException, WebApplicationException {

		String typeSubtype = ServiceUtils.mediaType2String(mediaType);
		logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
		try {
			switch (typeSubtype.toString()) {
			case MediaType.APPLICATION_XML:
				toXML(event, entityStream);				
				break;

			case MediaType.APPLICATION_JSON:
				toJSON(event, entityStream);
				break;
			default:
				toXML(event, entityStream);
				
			}
		} catch (JAXBException e) {
			logger.error("Errore durante la conversione di EventDTO in {}; Message: ",typeSubtype,e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void toJSON(EventDTO event, OutputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
		logger.debug("JSON conversion of DTO");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		objectMapper.writeValue(entityStream, event);
	}

	private void toXML(EventDTO event, OutputStream entityStream) throws JAXBException {
		logger.debug("XML conversion of DTO");
		JAXBContext jaxbContext = JAXBContext.newInstance(EventDTO.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(event, entityStream);
	}

	
	/*
	 * MessageBodyReader methods
	 */
	
	@Override
	public boolean isReadable(Class<?> classType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return classType==EventDTO.class;
	}

	@Override
	public EventDTO readFrom(	Class<EventDTO> type, 
								Type genericType, 
								Annotation[] annotations, 
								MediaType mediaType,
								MultivaluedMap<String, String> httpHeaders, 
								InputStream entityStream)
					throws IOException, WebApplicationException {
		System.out.println("MessageBodyReader.readFrom");
		return null;
	}

}
