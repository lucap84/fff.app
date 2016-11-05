package it.fff.business.common.util;

public class ErrorCodes {
	
	//Codici errore integration layer
	public static final String ERR_INTEGRATION_GENERIC = "1001";

	public static final String ERR_INTEGRATION_getAllStandardMessages = "1002";
	public static final String ERR_INTEGRATION_retrieveEvent = "1003";
	public static final String ERR_INTEGRATION_updateProfileImage = "1004";
	public static final String ERR_INTEGRATION_createEvent = "1005";
	public static final String ERR_INTEGRATION_cancelEvent = "1006";
	public static final String ERR_INTEGRATION_cancelAttendance = "1007";
	public static final String ERR_INTEGRATION_createEventMessage = "1008";
	public static final String ERR_INTEGRATION_createStandardEventMessage = "1009";
	public static final String ERR_INTEGRATION_joinEvent = "1010";
	public static final String ERR_INTEGRATION_addFeedback = "1011";
	public static final String ERR_INTEGRATION_getEventMessages = "1012";
	public static final String ERR_INTEGRATION_searchEvents = "1013";
	public static final String ERR_INTEGRATION_getEventsByUser = "1014";
	public static final String ERR_INTEGRATION_getAttendancesByEvent = "1015";
	public static final String ERR_INTEGRATION_setCurrentPosition = "1016";
	public  static final String ERR_INTEGRATION_getPlacesByDescription = "1017";
	public  static final String ERR_INTEGRATION_getPlaceByGPSInCache = "1018";
	public  static final String ERR_INTEGRATION_getPlacesByDescriptionInCache = "1019";
	public  static final String ERR_INTEGRATION_updatePassword = "1020";
	public  static final String ERR_INTEGRATION_checkVerificationCode = "1021";
	public  static final String ERR_INTEGRATION_saveVerficationCode = "1022";
	public  static final String ERR_INTEGRATION_logout = "1023";
	public  static final String ERR_INTEGRATION_upgradeToPremium = "1024";
	public  static final String ERR_INTEGRATION_registerUser = "1025";
	public  static final String ERR_INTEGRATION_login = "1026";
	public  static final String ERR_INTEGRATION_updateUserData = "1027";
	public  static final String ERR_INTEGRATION_getUser = "1028";
	public  static final String ERR_INTEGRATION_retrieveClientSecrets = "1029";
	public  static final String ERR_INTEGRATION_getAllLanguages = "1030";
	public  static final String ERR_INTEGRATION_getAllSubscriptionTypes = "1031";
	public  static final String ERR_INTEGRATION_getAllAchievementTypes = "1032";
	public  static final String ERR_INTEGRATION_getAllAttendanceStates = "1033";
	public  static final String ERR_INTEGRATION_getAllEventStates = "1034";
	public  static final String ERR_INTEGRATION_getAllEventCategories = "1035";
	public  static final String ERR_INTEGRATION_getAllNations = "1036";
	public  static final String ERR_INTEGRATION_resetPassword = "1037";
	public  static final String ERR_INTEGRATION_getEmailInfo = "1038";
	public  static final String ERR_INTEGRATION_getCityByName = "1039";
	public  static final String ERR_INTEGRATION_getUserFeedbacks = "1040";
	public  static final String ERR_INTEGRATION_readProfileImage = "1041";
	public  static final String ERR_INTEGRATION_getFacebookUserData = "1042";
	public  static final String ERR_INTEGRATION_getAttendancesByUser = "1043";
	public  static final String ERR_INTEGRATION_getUserAccountByFacebookId = "1044";
	public  static final String ERR_INTEGRATION_getUserAccountByEmail = "1045";

	
	//Codici errore business
	public static final String ERR_BUSIN_GENERIC = "2001";
	public static final String ERR_BUSIN_GENERIC_ID_NOT_VALID = "2002";
	public static final String ERR_BUSIN_IMG_INVALID_INPUT = "2003";
	
	public static final String ERR_BUSIN_GETEVENT = "2004";
	public static final String ERR_BUSIN_CREATEUSER = "2005";
	public static final String ERR_BUSIN_GETATTENDANCES_BYEVENT ="2006";
	public static final String ERR_BUSIN_CREATEEVENT = "2007";
	public static final String ERR_BUSIN_CANCELATTENDANCES = "2008";
	public static final String ERR_BUSIN_POSTMSG = "2009";
	public static final String ERR_BUSIN_POSTSTDMSG = "2010";
	public static final String ERR_BUSIN_UPGRADE_TO_PREMIUM = "2011";
	public static final String ERR_BUSIN_UPDATE_USERDATA = "2012";
	public static final String ERR_BUSIN_JOINEVENT = "2013";
	public static final String ERR_BUSIN_ADDFEEDBACK = "2014";
	public static final String ERR_BUSIN_GETEVENTMSG = "2015";
	public static final String ERR_BUSIN_SEACRHEVENTS =  "2016";
	public static final String ERR_BUSIN_GETPLACES = "2017";
	public static final String ERR_BUSIN_LOGIN = "2018";
	public static final String ERR_BUSIN_CHECK_VERIFICATIONCODE = "2019";
	public static final String ERR_BUSIN_UPDATEPSW = "2020";
	public static final String ERR_BUSIN_SEND_VERIFICATIONCODE = "2021";
	public static final String ERR_BUSIN_SET_CURRENTPOSITION = "2022";
	public static final String ERR_BUSIN_GET_EVENTSBYUSER = "2023";
	public static final String ERR_BUSIN_GET_USER = "2024";
	public static final String ERR_BUSIN_CANCELEVENT = "2025";
	public static final String ERR_BUSIN_RESET_PASSWORD = "2026";
	public static final String ERR_BUSIN_EXISTING_MAIL = "2027";
	public static final String ERR_BUSIN_GETCITY_BY_NAME = "2028";
	public static final String ERR_BUSIN_GETFEEDBACKS_BY_USERID = "2029";
	public static final String ERR_BUSIN_READIMAGE_BY_USERID = "2030";
	public static final String ERR_BUSIN_GET_FB_USERDATA = "2031";
	public static final String ERR_BUSIN_GETATTENDANCES_BYUSER ="2032";
	public static final String ERR_BUSIN_LOGIN_FACEBOOK ="2033";


}
