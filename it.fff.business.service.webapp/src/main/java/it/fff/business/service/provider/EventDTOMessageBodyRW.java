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
import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.UserDTO;

@Provider
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class EventDTOMessageBodyRW extends ApplicationProvider implements MessageBodyWriter<EventDTO>, MessageBodyReader<EventDTO>{

	private static final Logger logger = LogManager.getLogger(EventDTOMessageBodyRW.class);
	
	/*
	 * MessageBodyWriter methods
	 */
	
	@Override
	public long getSize(EventDTO arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return 0;// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
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
		logger.debug("writeTo <---- "+classType);
		super.writeToStream(mediaType, event, entityStream);
	}

	
	/*
	 * MessageBodyReader methods
	 */
	
	@Override
	public boolean isReadable(Class<?> classType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return classType==EventDTO.class;
	}

	@Override
	public EventDTO readFrom(	Class<EventDTO> classType, 
								Type genericType, 
								Annotation[] annotations, 
								MediaType mediaType,
								MultivaluedMap<String, String> httpHeaders, 
								InputStream inputStream)
					throws IOException, WebApplicationException {
		logger.debug("readFrom ----> "+classType);
		return (EventDTO) super.readFromStream(mediaType, inputStream, classType);
	}

}
