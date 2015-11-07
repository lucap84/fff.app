package it.fff.business.strategy.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.strategy.ImageValidationStrategy;

public class ImageSizeValidationStrategy implements ImageValidationStrategy {
	
	private static final Logger logger = LogManager.getLogger(ImageSizeValidationStrategy.class);
	
	private final long UPLOAD_FILE_MAX_SIZE = 9999999;

	@Override
	public boolean isValid(ProfileImageBO image) {
		boolean isValid = false;
		if(image!=null && image.getSize()<UPLOAD_FILE_MAX_SIZE){
			isValid = true;
		}
		logger.info("Validazione dimensione immagine: "+isValid);
		return isValid;
	}

}
