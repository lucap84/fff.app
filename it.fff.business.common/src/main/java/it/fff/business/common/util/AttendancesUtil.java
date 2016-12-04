package it.fff.business.common.util;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;

public class AttendancesUtil {
	
	public static int calculateTotalUserAttendances(List<AttendanceBO> attendances){
		int numUsers = 0;
		if(attendances==null || attendances.size()==0){
			return numUsers;
		}
		for (AttendanceBO att : attendances) {
			if (att.isValid()){
				numUsers++;
			}
		}
		return numUsers;
	}
	
	public static int calculateTotalGuestAttendances(List<AttendanceBO> attendances){
		int numGuests = 0;
		if(attendances==null || attendances.size()==0){
			return numGuests;
		}
		for (AttendanceBO att : attendances) {
			if (att.isValid()){
				numGuests += att.getNumeroOspiti();
			}
		}
		return numGuests;
	}
	
}
