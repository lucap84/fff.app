package it.fff.business.strategy.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.strategy.ImageValidationStrategy;
import it.fff.clientserver.common.util.Constants;

public class ImageSizeValidationStrategy implements ImageValidationStrategy {
	
	private static final Logger logger = LogManager.getLogger(ImageSizeValidationStrategy.class);
	private double imageMaxSide;
	private int imageMaxSize;
	
	public ImageSizeValidationStrategy() {
		ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
		this.imageMaxSize = Integer.valueOf(configurationProvider.getImageConfigProperty(Constants.PROP_IMAGE_MAXSIZE));
		this.imageMaxSide = Double.valueOf(configurationProvider.getImageConfigProperty(Constants.PROP_IMAGE_MAXSIDE));
	}
	
	@Override
	public boolean isValid(ProfileImageBO imageBO) {
		boolean isValid = false;
		
		InputStream imageInputStream = imageBO.getImageInputStream();
		if(imageInputStream==null){
			return false;
		}
		
		if(imageBO.getSize()<=imageMaxSize){
			isValid = true;
		}
		logger.info("Validazione dimensione immagine: "+isValid);
		return isValid;
	}

	@Override
	public ProfileImageBO validate(ProfileImageBO imageBO) {
		InputStream imageInputStream = imageBO.getImageInputStream();

		try {
			BufferedImage bi = ImageIO.read(imageInputStream);
			int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();
			
			BufferedImage resizeImageJpg = resizeImage(bi, type);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(resizeImageJpg, imageBO.getExtension(), os);
			
			byte[] byteArray = os.toByteArray();
			InputStream resizedImageInputStream = new ByteArrayInputStream(byteArray);
			
			imageBO.setImageInputStream(resizedImageInputStream);
			imageBO.setSize(os.size());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return imageBO;
	}
	
//	private long countBytes(InputStream inputstream){
//		long count = -1;
//		
//		try{
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			org.apache.commons.io.IOUtils.copy(inputstream, baos);
//			byte[] bytes = baos.toByteArray();
//			count = bytes.length;
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		return count;
//	}
	
	private BufferedImage resizeImage(BufferedImage originalImage, int type){
		int originalWidth = originalImage.getWidth();
		int originalHeight = originalImage.getHeight();
		
		double greatestSide = originalWidth>=originalHeight?originalWidth:originalHeight;
		
		double reducingFactor = this.imageMaxSide / greatestSide;
		
		int reducedWidth  = (int)Math.floor(originalWidth*reducingFactor);
		int reducedHeigth = (int)Math.floor(originalHeight*reducingFactor);
				
		BufferedImage resizedImage = new BufferedImage(reducedWidth, reducedHeigth, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, reducedWidth, reducedHeigth, null);
		g.dispose();
			
		return resizedImage;
	   
	}	

}
