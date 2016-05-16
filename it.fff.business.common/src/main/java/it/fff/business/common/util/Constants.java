package it.fff.business.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
	
	/*
	 * Date formatter constants
	 */
	public final static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			
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
	 * Places constants 
	 */
	public static final String PLACES_CONF_FILENAME 			= "places.properties";
	
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
	public static final String FACEBOOK_CONF_FILENAME 	= "facebook.properties";	
	
	public static final String PROP_FACEBOOK_APP_ID				= "facebook.app.id";
	public static final String PROP_FACEBOOK_APP_SECRET			= "facebook.app.secret";
	public static final String PROP_FACEBOOK_ACCESSTOKEN_URL	= "facebook.accesstoken.url";
	public static final String PROP_FACEBOOK_REDIRECT_URL		= "facebook.redirect.url";
	public static final String PROP_FACEBOOK_ME_URL				= "facebook.me.url";
	public static final String PROP_FACEBOOK_ME_FIELDS			= "facebook.me.fields";
	
	
	/*
	 * Named Queries
	 */
	public static final String QY_GET_ATTENDANCE_BY_EVENT_ORGANIZER	= "getAttendanceByEventAndOrganizer";
	public static final String QY_UPDATE_ACCOUNT_PSW				= "updateAccountPassword";
	public static final String QY_GET_INFO_BY_MAIL					= "geInfoByEmail";
}
