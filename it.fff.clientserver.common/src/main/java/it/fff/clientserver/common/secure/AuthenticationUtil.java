package it.fff.clientserver.common.secure;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Mac;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
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
		byte[] digestHMAC = AuthenticationUtil.digestHMAC(hashAlgorithm, key, encoding, data);
		return Base64.encodeBase64String(digestHMAC);
	}
	
	public static String digestHMACbase64(String key, String data)  {
		return AuthenticationUtil.digestHMACbase64(ALGORITHM_HMAC_SHA256, key, ENCODING_UTF8, data);
	}
	
	/*
	 * Spiegazione utilita' HMAC
	 * http://restcookbook.com/Basics/loggingin/
	 */
	public static String generateHMACAuthorizationHeader(	String key,
														Integer userId, 
														String httpMethod, 
														String path, 
														String date,
														String nonce){
		
		StringBuilder dataDigest = new StringBuilder(httpMethod).append(path).append(date).append(nonce);  
		String digest = AuthenticationUtil.digestHMACbase64(key, dataDigest.toString());
		StringBuilder authorizationHeader = new StringBuilder("hmac:").append(userId).append(":").append(nonce).append(":").append("[").append(digest).append("]");
		return authorizationHeader.toString();
	}
	
	public static DHPublicKeySpec createSpecificKey(BigInteger p, BigInteger g)  {
		DHPublicKeySpec kspec = null;
		KeyPairGenerator kpg;
	    try {
			kpg = KeyPairGenerator.getInstance("DiffieHellman");

	    DHParameterSpec param = new DHParameterSpec(p, g);
	    kpg.initialize(param);
	    KeyPair kp = kpg.generateKeyPair();

	    KeyFactory kfactory = KeyFactory.getInstance("DiffieHellman");

	    kspec = (DHPublicKeySpec) kfactory.getKeySpec(kp.getPublic(), DHPublicKeySpec.class);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	    return kspec;
	  }	
	
	public static String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++) {
             byte2hex(block[i], buf);
             if (i < len-1) {
                 buf.append(":");
             }
        }
        return buf.toString();
    }
    
    public static void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                            '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }  	

	public static void main(String [] args) throws Exception {
		long start = System.currentTimeMillis();
		System.out.println(digestHMACbase64(ALGORITHM_HMAC_SHA256, "mykey", ENCODING_UTF8,  "The quick brown fox jumps over the lazy dog"));
		long end = System.currentTimeMillis();
		
		System.out.println("timing millisec: "+(end-start));
	}
}
