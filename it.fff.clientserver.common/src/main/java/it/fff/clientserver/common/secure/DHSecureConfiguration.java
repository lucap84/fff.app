package it.fff.clientserver.common.secure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface DHSecureConfiguration extends SecureConfiguration {
	public static final boolean SECURITY_ACTIVATED = true;
	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

	public void storeSharedKey(String userId, String sharedKey);
	
	public String retrieveSharedKey(String userId);
}
