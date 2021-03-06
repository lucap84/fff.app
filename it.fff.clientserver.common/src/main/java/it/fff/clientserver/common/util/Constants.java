package it.fff.clientserver.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
	
	/*
	 * Date formatter constants
	 */
	public final static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static DateFormat DATE_FORMATTER_FACEBOOK = new SimpleDateFormat("MM/dd/yyyy");
	public final static String DATE_DEFAULT_YYYY_MM_DD = "1970-01-01 00:00:00";
			
	/*
	 * Security constants
	 */
	public static final String FILENAME_CONFIG_SERVERSECURITY 			= "serversecurity.properties";
	public static final String PROP_AUTHENTICATION_ENABLED				= "server.security.authentication.enabled";
	public static final String PROP_AUTHORIZATION_ENABLED				= "server.security.authorization.enabled";
	public static final String PROP_AUTHORIZATION_DATE_EXPIRATION		= "server.security.authorization.date.expiration";
	public static final String PROP_AUTHORIZATION_NONCE_SETSIZE			= "server.security.authorization.nonce.setsize";
	
	/*
	 * Mail constants
	 */
	public static final String FILENAME_CONFIG_MAIL			= "mail.properties";
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
	public static final String FILENAME_CONFIG_IMAGE		= "images.properties";
	
	public static final String PROP_IMAGE_UPLOAD_LOCATION	= "server.filesystem.location.upload";
	public static final String PROP_IMAGE_EXTENSION			= "server.image.extension";
	public static final String PROP_IMAGE_MAXSIZE			= "server.image.maxsize";
	public static final String PROP_IMAGE_MAXSIDE			= "server.image.maxside";
	
	/*
	 * Places constants 
	 */
	public static final String FILENAME_CONFIG_PLACES 			= "places.properties";
	
	public static final String PROP_PLACE_READ_FROM_CACHE		= "places.extservice.caching.read";
	public static final String PROP_PLACE_UPDATE_CACHE			= "places.extservice.caching.update";
	public static final String PROP_PLACE_EXT_CACHING_TTL		= "places.extservice.caching.ttl.days";
	public static final String PROP_PLACE_GPS_DECIMALPREC_CACHE	= "places.extservice.caching.gps.decimalprecision";
	public static final String PROP_GOOGLE_APIKEY				= "google.geocoding.apikey";
	public static final String PROP_GOOGLE_MAX_CALLS			= "google.geocoding.maxcallsperday";
	public static final String PROP_GOOGLE_PREMIUM				= "google.geocoding.accountpremium";
	public static final String PROP_GOOGLE_MAX_RESULTS_LOADED	= "google.geocoding.maxresultsloaded";
	public static final String PROP_GOOGLE_VIEWPORT_DIAMETER_KM	= "google.geocoding.viewport.sizekm";
	
	public final static double ONE_KM_TO_DEGREES = 0.0089982311916;
	public final static double FIVE_KM_TO_DEGREES = 0.044991155958;
	public final static double TEN_KM_TO_DEGREES = 0.089982311916;
	public final static int LATITUDE_RANGE_ABS = 90;
	public final static int LONGITUDE_RANGE_ABS = 180;
	
	
	/*
	 * Facebook constants 
	 */
	public static final String FILENAME_CONFIG_FACEBOOK 		= "facebook.properties";	
	
	public static final String PROP_FACEBOOK_APP_ID				= "facebook.app.id";
	public static final String PROP_FACEBOOK_APP_SECRET			= "facebook.app.secret";
	public static final String PROP_FACEBOOK_ACCESSTOKEN_URL	= "facebook.accesstoken.url";
	public static final String PROP_FACEBOOK_REDIRECT_URL		= "facebook.redirect.url";
	public static final String PROP_FACEBOOK_ME_URL				= "facebook.me.url";
	public static final String PROP_FACEBOOK_ME_FIELDS			= "facebook.me.fields";

	/*
	 * JDBC constants
	 */
	public static final String FILENAME_CONFIG_JDBC 			= "jdbc.cfg.properties";

	public static final String PROP_JDBC_DRIVER				= "jdbc.driver";
	public static final String PROP_JDBC_CONNECTION_URL		= "jdbc.connection.url";
	public static final String PROP_JDBC_CONNECTION_USR		= "jdbc.connection.username";
	public static final String PROP_JDBC_CONNECTION_PSW		= "jdbc.connection.password";

	/*
	 * Named Queries
	 */
	public static final String QY_GET_ATTENDANCE_BY_EVENT_ORGANIZER	= "getAttendanceByEventAndOrganizer";
	public static final String QY_UPDATE_ACCOUNT_PSW				= "updateAccountPassword";
	public static final String QY_GET_INFO_BY_MAIL					= "geInfoByEmail";
	public static final String QY_GET_ACCOUNT_BY_FB					= "geAccountByFacebookId";
	public static final String QY_GET_ACCOUNT_BY_EMAIL				= "geAccountByEmail";
	
	/*
	 * Varie
	 */
	
	public static final String UNDEFINED_DATA = "UNDEFINED";
}

