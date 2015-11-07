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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.dto.AttendanceDTO;


@Provider
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class AttendanceDTOMessageBodyRW extends ApplicationProvider implements MessageBodyWriter<AttendanceDTO>, MessageBodyReader<AttendanceDTO>{

	private static final Logger logger = LogManager.getLogger(AttendanceDTOMessageBodyRW.class);
	
	/*
	 * MessageBodyWriter methods
	 */
	
	@Override
	public long getSize(AttendanceDTO arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return 0;// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==AttendanceDTO.class;
	}

	@Override
	public void writeTo(AttendanceDTO attendanceDTO, 
						Class<?> classType, 
						Type genericType, 
						Annotation[] annotations, 
						MediaType mediaType,
						MultivaluedMap<String, Object> httpHeaders, 
						OutputStream entityStream)
				throws IOException, WebApplicationException {
		logger.debug("writeTo <---- "+classType);
		super.writeToStream(mediaType, attendanceDTO, entityStream);
	}
	
	/*
	 * MessageBodyReader methods
	 */
	
	@Override
	public boolean isReadable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==AttendanceDTO.class;
	}

	@Override
	public AttendanceDTO readFrom(Class<AttendanceDTO> classType, 
									Type type, 
									Annotation[] annotations, 
									MediaType mediaType,
									MultivaluedMap<String, String> httpHeaders, 
									InputStream inputStream) throws IOException, WebApplicationException {
		logger.debug("readFrom ----> "+classType);
		return (AttendanceDTO) super.readFromStream(mediaType, inputStream, classType);
	}
	

}
