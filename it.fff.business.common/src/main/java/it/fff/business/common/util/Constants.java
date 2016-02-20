package it.fff.business.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
	
	public final static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
			
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
	
	public static final String PROP_MAIL_TEMPLATE_REGISTRATIONCONFIRM 	= "mail.template.confirm.registration";
	public static final String PROP_MAIL_TEMPLATE_VERIFICATIONCODE 	= "mail.template.verificationcode";
	
	/*
	 * Persistence constants
	 */
	public static final String IMAGE_CONF_FILENAME 			= "images.properties";
	public static final String PROP_IMAGE_UPLOAD_LOCATION	= "server.filesystem.location.upload";
	public static final String PROP_IMAGE_EXTENSION			= "server.image.extension";
	public static final String PROP_IMAGE_MAXSIZE			= "server.image.maxsize";
	public static final String PROP_IMAGE_MAXSIDE			= "server.image.maxside";
	
	/*
	 * Named Queries
	 */
	public static final String QY_GET_ATTENDANCE_BY_EVENT_ORGANIZER	= "getAttendanceByEventAndOrganizer";
	public static final String QY_UPDATE_ACCOUNT_PSW				= "updateAccountPassword";
	public static final String QY_GET_INFO_BY_MAIL					= "geInfoByEmail";
}
