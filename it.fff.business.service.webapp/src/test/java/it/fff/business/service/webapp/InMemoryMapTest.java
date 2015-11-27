package it.fff.business.service.webapp;

import java.util.HashMap;
import java.util.Map;

public class InMemoryMapTest {

	public static void main(String[] args) {
		 System.gc();
		 Map<Long,Map<String, String>> clientSecrets = new HashMap<Long,Map<String, String>>();
		 for(long counter=0;;counter++){
			 Map<String, String> device2key = new HashMap<String, String>();
			 device2key.put("android-mobile-"+counter, "36:C9:25:E9:01:BC:B6:86:5E:60:5E:72:15:37:87:86:8D:AC:27:95:EE:C1:71:CE:2E:2B:FC:2E:AE:F1:2A:AA:E2:6F:8E:36:5C:16:2C:94:24:EA:C6:B5:2F:D9:9F:9A:9B:03:E7:A8:1C:51:E2:C4:DC:32:E1:E4:C7:E4:C8:1D");
			 clientSecrets.put(counter,device2key);
			 
			 if(counter%10000==0) System.out.println(""+counter);
		 }
	}
}
