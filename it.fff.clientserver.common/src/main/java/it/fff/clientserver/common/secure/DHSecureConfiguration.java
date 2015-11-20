package it.fff.clientserver.common.secure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface DHSecureConfiguration extends SecureConfiguration {

	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

	public void storeSharedKey(String userId, String deviceId, String sharedKey);
	
	public void removeSharedKey(String userId, String deviceId);
	
	public String retrieveSharedKey(String userId, String deviceId);
}
