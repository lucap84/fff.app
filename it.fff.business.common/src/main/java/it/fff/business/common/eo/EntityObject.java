package it.fff.business.common.eo;

public class EntityObject {
	
	public boolean isEmpty(String s) {
		return (s==null || "".equals(s));
	}
	
	public boolean isEmpty(Integer i) {
		return (i==null || i==0);
	}
	
	public boolean isEmpty(Double d) {
		return (d==null || d==0);
	}
	
	public boolean isEmpty(Long l) {
		return (l==null || l==0);
	}	
}
