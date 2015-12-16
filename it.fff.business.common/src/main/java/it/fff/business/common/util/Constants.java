package it.fff.business.common.util;

public class Constants {
	
	/*
	 * Security constants
	 */
	public static final String AUTH_CONF_FILENAME 			= "authorization.properties";
	public static final String PROP_AUTH_DATE_EXPIRATION	= "auth.date.expiration";
	public static final String PROP_AUTH_NONCE_SETSIZE		= "auth.nonce.setsize";
	
	/*
	 * Mail constants
	 */
	public static final String MAIL_CONF_FILENAME			= "mail.properties";
	
	public static final String PROP_MAIL_SENDER_ENABLED 	= "mail.sender.enabled";
	public static final String PROP_MAIL_USERNAME			= "mail.account.username";
	public static final String PROP_MAIL_PASSWORD			= "mail.account.password";
	public static final String PROP_MAIL_AUTH 				= "mail.smtp.auth";
	public static final String PROP_MAIL_ENCRYPT 			= "mail.smtp.starttls.enable";
	public static final String PROP_MAIL_HOST 				= "mail.smtp.host";
	public static final String PROP_MAIL_PORT 				= "mail.smtp.port";	
	
	/*
	 * Persistence constants
	 */
	public static final String IMAGE_CONF_FILENAME 			= "images.properties";
	
	public static final String PROP_IMAGE_UPLOAD_LOCATION	= "server.filesystem.location.upload";
}