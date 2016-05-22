package it.fff.business.common.eo;

public class EntityObject {
	
	public boolean isEmpty(String s) {
		return (s==null || "".equals(s));
	}
	
	public boolean isEmpty(Integer i) {
		return (i==null || i.intValue()==0);
	}
	
	public boolean isEmpty(Double d) {
		return (d==null || d.doubleValue()==0);
	}
	
	public boolean isEmpty(Long l) {
		return (l==null || l.longValue()==0);
	}
	
	public boolean isEmpty(Float f) {
		return (f==null || f.floatValue()==0);
	}	
}
