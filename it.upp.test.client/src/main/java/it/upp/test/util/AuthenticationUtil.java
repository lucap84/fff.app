package it.upp.test.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class AuthenticationUtil {
	
	public static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";
	public static final String ENCODING_UTF8 = "UTF-8";
	
	public static byte[] digestHMAC(String hashAlgorithm, String key, String encoding, String data) {
		Mac sha256_HMAC = null;  
		byte[] digest = null;
		try{
			  sha256_HMAC = Mac.getInstance(hashAlgorithm);
			  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(encoding), hashAlgorithm);
			  sha256_HMAC.init(secret_key);
			  digest = sha256_HMAC.doFinal(data.getBytes(encoding));
		  }
		  catch(InvalidKeyException e){
			  e.printStackTrace();
		  } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		  return digest;
	}
	
	public static String digestHMACbase64(String hashAlgorithm, String key, String encoding, String data)  {
		byte[] digestHMAC = digestHMAC(hashAlgorithm, key, encoding, data);
		return Base64.encodeBase64String(digestHMAC);
	}
	
	public static String digestHMACbase64(String key, String data)  {
		return digestHMACbase64(ALGORITHM_HMAC_SHA256, key, ENCODING_UTF8, data);
	}

	public static void main(String [] args) throws Exception {
		long start = System.currentTimeMillis();
		System.out.println(digestHMACbase64(ALGORITHM_HMAC_SHA256, "mykey", ENCODING_UTF8,  "The quick brown fox jumps over the lazy dog"));
		long end = System.currentTimeMillis();
		
		System.out.println("timing millisec: "+(end-start));
	}
}
