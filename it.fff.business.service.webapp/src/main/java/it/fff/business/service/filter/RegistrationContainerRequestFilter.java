package it.fff.business.service.filter;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.fff.clientserver.common.secure.AuthenticationUtil;
import it.fff.clientserver.common.secure.DHSecureConfiguration;

//Pre-matching filters are request filters that are executed before the request matching is started
@PreMatching
public class RegistrationContainerRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = LogManager.getLogger(RegistrationContainerRequestFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String requestPath = requestContext.getUriInfo().getPath();
		String method = requestContext.getMethod();
		
		if(requestPath.matches("^security/registration.*") || requestPath.matches("^security/login.*")){
			logger.debug("RegistrationContainerRequestFilter > "+method+": "+requestPath);
			String dhHeader = requestContext.getHeaders().getFirst("dh");
			if(dhHeader==null || "".equals(dhHeader)){
				logger.error("Diffie-Hellman header not present!");
				requestContext.abortWith(Response
						.status(Response.Status.UNAUTHORIZED)
						.entity("User cannot access the resource.")
						.build());
			}
			byte[] decodeBase64 = Base64.decodeBase64(dhHeader);
			byte[] clientPubKeyEnc = decodeBase64;
			
	        /*
	         * Il server ha ricevuto la chiave pubblica del client criptata.
	         * Il server ricostruisce la chiave pubblica del client.
	         */
			try{
		        KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
		        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(clientPubKeyEnc);
		        PublicKey clientPubKey = bobKeyFac.generatePublic(x509KeySpec);
		        
		        /*
		         * Il server estrae i parametri Diffie-Hellman dalla chiave pubblica del client.
		         * Il server usera' questi parametri per generare i propri parametri Diffie-Hellman
		         */
		        DHParameterSpec dhParamSpec = ((DHPublicKey)clientPubKey).getParams();
		        
		        System.out.println("BOB: Generate DH keypair ...");
		        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
		        bobKpairGen.initialize(dhParamSpec);
		        KeyPair bobKpair = bobKpairGen.generateKeyPair();
		        
		        // Il server crea ed inizializza il proprio oggetto KeyAgreement
		        System.out.println("BOB: Initialization ...");
		        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
		        bobKeyAgree.init(bobKpair.getPrivate());
		        
		        System.out.println("BOB: Execute PHASE1 ...");
		        bobKeyAgree.doPhase(clientPubKey, true);
	
		        // Il server codifica la propria chiave pubblica e la converte in base64 per inviarla al server
		        byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();
		        String serverPubKeyEncStrB64 = Base64.encodeBase64String(bobPubKeyEnc);
		        
		        //Il server ricava la chiave segreta condivisa con il client
		        byte[] bobSharedSecret = new byte[64];
		        int bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 0);
		        String sharedSecretHEX = AuthenticationUtil.toHexString(bobSharedSecret);

		        //Metto negli attributes della richiesta la chiave pubblica (che sarà ritornata poi al client) e il segreto condiviso, da associare al client e salvare localmente
		        requestContext.setProperty("serverPubKeyEncStrB64", serverPubKeyEncStrB64);
				requestContext.setProperty("sharedSecretHEX", sharedSecretHEX);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}				
			logger.debug("< RegistrationContainerRequestFilter");
		}
		
		
	}
	
	

}
