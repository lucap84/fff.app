package it.fff.business.service.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.MediaType;
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

import it.fff.business.common.dto.DataTransferObject;
import it.fff.business.common.dto.UserDTO;

public abstract class ApplicationProvider{

	private static final Logger logger = LogManager.getLogger(ApplicationProvider.class);
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


		public void writeToStream(MediaType mediaType, DataTransferObject dto, OutputStream entityStream) {
			String typeSubtype = this.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",dto.getClass().getName(), typeSubtype);
			try {
				switch (typeSubtype) {
				case MediaType.APPLICATION_XML:
						toXML(dto, entityStream);
					break;
	
				case MediaType.APPLICATION_JSON:
					toJSON(dto, entityStream);
					break;
				default:
					toXML(dto, entityStream);
				}
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}


		public DataTransferObject readFromStream(MediaType mediaType, InputStream inputStream, Class classType) {
			DataTransferObject dto = null;
			String typeSubtype = this.mediaType2String(mediaType);
			logger.debug("Class {} conversion in {} ...",classType, typeSubtype);
			try {
				switch (typeSubtype) {
				case MediaType.APPLICATION_XML:
						dto = fromXML(inputStream,classType);
					break;
	
				case MediaType.APPLICATION_JSON:
					dto = fromJSON(inputStream,classType);
					break;
				default:
					dto = fromXML(inputStream,classType);
					
				}
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}				
			return dto;
		}		
}
