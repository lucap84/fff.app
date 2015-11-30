package it.fff.clientserver.common.secure;


public interface DHSecureConfiguration extends SecureConfiguration {

	public void storeSharedKey(String userId, String deviceId, String sharedKey);
	
	public void removeSharedKey(String userId, String deviceId);
	
	public String retrieveSharedKey(String userId, String deviceId);
}
