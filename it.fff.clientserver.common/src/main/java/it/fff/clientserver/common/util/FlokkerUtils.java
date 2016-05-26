package it.fff.clientserver.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlokkerUtils {
	
	private static final Logger logger = LogManager.getLogger(FlokkerUtils.class);

	public static boolean createDirIfNotExists(String dirPath) {
		boolean exists = false;
		File dir = new File(dirPath);
		exists = dir.exists();
		if(!exists){
			try{
				exists = dir.mkdir();
			}
			catch(SecurityException se){
				exists = false;
			}
		}
		return exists;
	}	
	// save uploaded file to a defined location on the server
	public static boolean saveFile(InputStream uploadedInputStream,  String serverLocation) {
    	boolean saved = true;
    	OutputStream outpuStream = null;
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
 
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

        } catch (IOException e) {
        	saved = false;
            e.printStackTrace();
        }
        finally {
        	try {
                outpuStream.flush();
                outpuStream.close();
        		outpuStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return saved;
    }
    
	public static FileInputStream readFile(String serverLocation){
    	File file = new File(serverLocation);
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			logger.debug("Total file size to read (in bytes) : "+ fis.available());
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    	
		return fis;
    }

	public static String readFileAsBase64(String serverLocation){
    	String b64 = null;
    	File file = new File(serverLocation);
    	byte[] readFileToByteArray;
		try {
			readFileToByteArray = FileUtils.readFileToByteArray(file);
			b64 = Base64.encodeBase64String(readFileToByteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return b64;
    }
	public static void writeStringToFile(File file, String jsonString) {
		
		try {
			FileUtils.writeStringToFile(file, jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int generateRandomIntBetween(int minimum, int maximum){
		return minimum + (int)(Math.random() * maximum);
	}
    
}
