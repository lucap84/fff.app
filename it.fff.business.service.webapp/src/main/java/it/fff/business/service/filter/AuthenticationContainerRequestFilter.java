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
public class AuthenticationContainerRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = LogManager.getLogger(AuthenticationContainerRequestFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String requestPath = requestContext.getUriInfo().getPath();
		String method = requestContext.getMethod();
		
		if(isToAuthenticate(requestPath)){
			logger.debug("AuthenticationContainerRequestFilter > "+method+": "+requestPath);
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
		        KeyFactory serverKeyFac = KeyFactory.getInstance("DH");
		        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(clientPubKeyEnc);
		        PublicKey clientPubKey = serverKeyFac.generatePublic(x509KeySpec);
		        
		        /*
		         * Il server estrae i parametri Diffie-Hellman dalla chiave pubblica del client.
		         * Il server usera' questi parametri per generare i propri parametri Diffie-Hellman
		         */
		        DHParameterSpec dhParamSpec = ((DHPublicKey)clientPubKey).getParams();
		        
		        logger.debug("SERVER: Generate DH keypair ...");
		        KeyPairGenerator serverKpairGen = KeyPairGenerator.getInstance("DH");
		        serverKpairGen.initialize(dhParamSpec);
		        KeyPair serverKpair = serverKpairGen.generateKeyPair();
		        
		        // Il server crea ed inizializza il proprio oggetto KeyAgreement
		        logger.debug("SERVER: Initialization ...");
		        KeyAgreement serverKeyAgree = KeyAgreement.getInstance("DH");
		        serverKeyAgree.init(serverKpair.getPrivate());
		        
		        logger.debug("SERVER: Execute PHASE1 ...");
		        serverKeyAgree.doPhase(clientPubKey, true);
	
		        // Il server codifica la propria chiave pubblica e la converte in base64 per inviarla al server
		        byte[] serverPubKeyEnc = serverKpair.getPublic().getEncoded();
		        String serverPubKeyEncStrB64 = Base64.encodeBase64String(serverPubKeyEnc);
		        
		        //Il server ricava la chiave segreta condivisa con il client
		        byte[] serverSharedSecret = new byte[64];
		        int serverLen = serverKeyAgree.generateSecret(serverSharedSecret, 0);
		        String sharedSecretHEX = AuthenticationUtil.toHexString(serverSharedSecret);

		        //Metto negli attributes della richiesta la chiave pubblica (che sarà ritornata poi al client) e il segreto condiviso, da associare al client e salvare localmente
		        requestContext.setProperty("serverPubKeyEncStrB64", serverPubKeyEncStrB64);
				requestContext.setProperty("sharedSecretHEX", sharedSecretHEX);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}				
			logger.debug("< AuthenticationContainerRequestFilter");
		}
		
		
	}

	private boolean isToAuthenticate(String requestPath) {
		boolean isToAuthenticate = false;
		//Per registrarsi o per fare login bisogna procedere con l'autenticazione client
		isToAuthenticate |= requestPath.matches("^security/registration.*");
		isToAuthenticate |= requestPath.matches("^security/login.*");
		
		return isToAuthenticate;
	}
	
	

}
