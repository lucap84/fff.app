package it.fff.business.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {
	
	public static String stackTrace2String(Throwable t){
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

}
