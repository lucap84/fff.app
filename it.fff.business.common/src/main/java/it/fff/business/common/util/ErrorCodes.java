package it.fff.business.common.util;

public class ErrorCodes {
	
	//Codici errore persistenza
	public static final String ERR_PERS_GENERIC = "1001";
	public static final String ERR_PERS_INVALID_INPUT = "1002";
	public static final String ERR_IMGPERS_INVALID_INPUT = "1003";

	//Codici errore business
	public static final String ERR_BUSIN_GENERIC = "2001";
	public static final String ERR_BUSIN_GETEVENT = "2002";
	public static final String ERR_BUSIN_CREATEUSER = "2003";
	public static final String ERR_BUSIN_ID_FORMAT_NOT_VALID = "2004";
	public static final String ERR_BUSIN_GETATTENDANCES ="2005";
	public static final String ERR_BUSIN_CREATEEVENT = "2006";
	public static final String ERR_BUSIN_CANCELATTENDANCES = "2007";
	public static final String ERR_BUSIN_CANCELEVENT = "2008";
	public static final String ERR_BUSIN_POSTMSG = "2009";
	public static final String ERR_BUSIN_POSTSTDMSG = "2010";
	public static final String ERR_BUSIN_UPGRADE_TO_PREMIUM = "2011";

}
