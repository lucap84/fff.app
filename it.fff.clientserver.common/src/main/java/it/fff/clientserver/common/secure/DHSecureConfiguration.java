package it.fff.clientserver.common.secure;


public interface DHSecureConfiguration extends SecureConfiguration {

	public void storeSharedKey(Integer userId, String deviceId, String sharedKey);
	
	public void removeSharedKey(Integer userId, String deviceId);
	
	public String retrieveSharedKey(Integer userId, String deviceId);
}
