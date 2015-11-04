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
import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.dto.WriteResultDTO;

@Provider
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class WriteResultDTOMessageBodyRW extends ApplicationProvider implements MessageBodyWriter<WriteResultDTO>, MessageBodyReader<WriteResultDTO>{

	private static final Logger logger = LogManager.getLogger(WriteResultDTOMessageBodyRW.class);
	
	@Override
	public boolean isReadable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==WriteResultDTO.class;
	}

	@Override
	public WriteResultDTO readFrom(Class<WriteResultDTO> classType, 
									Type type, 
									Annotation[] annotations, 
									MediaType mediaType,
									MultivaluedMap<String, String> httpHeaders, 
									InputStream inputStream) throws IOException, WebApplicationException {
		WriteResultDTO writeResultDTO = null;
		try {
			String typeSubtype = super.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
			switch (typeSubtype) {
			case MediaType.APPLICATION_XML:
				writeResultDTO = (WriteResultDTO)fromXML(inputStream,classType);				
				break;

			case MediaType.APPLICATION_JSON:
				writeResultDTO = (WriteResultDTO)fromJSON(inputStream,classType);
				break;
			default:
				writeResultDTO = (WriteResultDTO)fromXML(inputStream,classType);
				
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return writeResultDTO;
	}

	@Override
	public long getSize(WriteResultDTO arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type arg1, Annotation[] arg2, MediaType arg3) {
		return classType==WriteResultDTO.class;
	}

	@Override
	public void writeTo(WriteResultDTO writeResultDTO, 
						Class<?> classType, 
						Type genericType, 
						Annotation[] annotations, 
						MediaType mediaType,
						MultivaluedMap<String, Object> httpHeaders, 
						OutputStream entityStream)
				throws IOException, WebApplicationException {
		try{
			String typeSubtype = super.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
			switch (typeSubtype) {
			case MediaType.APPLICATION_XML:
				toXML(writeResultDTO, entityStream);				
				break;
	
			case MediaType.APPLICATION_JSON:
				toJSON(writeResultDTO, entityStream);
				break;
			default:
				toXML(writeResultDTO, entityStream);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

}
