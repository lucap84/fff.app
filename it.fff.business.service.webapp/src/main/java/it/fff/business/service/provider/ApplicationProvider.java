package it.fff.business.service.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.fff.business.common.dto.DataTransferObject;

public abstract class ApplicationProvider{

	
	/*
	 *  utility methods	
	 */
		protected String mediaType2String(MediaType mediaType){
			StringBuilder typeSubtype = new StringBuilder();
			typeSubtype.append(mediaType.getType()).append("/").append(mediaType.getSubtype());
			
			return typeSubtype.toString();
		}
		
		
		protected void toJSON(DataTransferObject dto, OutputStream entityStream) throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			objectMapper.writeValue(entityStream, dto);
		}
		
		protected DataTransferObject fromJSON(InputStream entityStream, Class classType) throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			DataTransferObject readValue = objectMapper.readValue(entityStream, classType);
			return readValue;
		}	

		protected void toXML(DataTransferObject dto, OutputStream entityStream) throws JAXBException {
			JAXBContext jaxbContext = JAXBContext.newInstance(dto.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(dto, entityStream);
		}
		
		protected DataTransferObject fromXML(InputStream entityStream, Class classType) throws JAXBException {
			JAXBContext jaxbContext = JAXBContext.newInstance(classType);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			DataTransferObject myBean = (DataTransferObject)unmarshaller.unmarshal(entityStream);
	        return myBean;
		}		
}
