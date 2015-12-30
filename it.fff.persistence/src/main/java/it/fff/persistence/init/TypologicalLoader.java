package it.fff.persistence.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.persistence.service.TypologicalPersistenceService;
import it.fff.persistence.util.PersistenceServiceProvider;

public class TypologicalLoader {
	
	private static final Logger logger = LogManager.getLogger(TypologicalLoader.class);
	
//	public static Map<EventStateEnum,Integer> eventStateEnum2ID = new HashMap<EventStateEnum,Integer>();
//	public static Map<AttendanceStateEnum,Integer> attendanceStateEnum2ID = new HashMap<AttendanceStateEnum,Integer>();

	public TypologicalLoader(){
		logger.debug("Loading typological data...");
		//Fetch data on startup
		this.loadEventStates();
		this.loadAttendanceStates();
		logger.debug("...Typological data loaded");
	}
	
	public void loadEventStates(){
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<EventStateEO> allEventStates = null;
		try {
			allEventStates = typologicalPersistenceService.getAllEventStates();
		} 
		catch (Exception e) {
			logger.error("Error loading typological data: "+e.getMessage());
			e.printStackTrace();
		}
		
		for (EventStateEO eventStateEO : allEventStates) {
			try{
				EventStateEnum valueOf = EventStateEnum.valueOf(eventStateEO.getNome());
				valueOf.setId(eventStateEO.getId());
//				eventStateEnum2ID.put(valueOf, eventStateEO.getId());
			}
			catch(IllegalArgumentException e){
				logger.error("State not Recognized! :"+eventStateEO.getNome());
			}
		}
	}
	
	public void loadAttendanceStates(){
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		List<AttendanceStateEO> allAttendanceStates = null;
		try {
			allAttendanceStates = typologicalPersistenceService.getAllAttendanceStates();
		
		} 
		catch (Exception e) {
			logger.error("Error loading typological data: "+e.getMessage());
			e.printStackTrace();
		}
		
		for (AttendanceStateEO attendanceState : allAttendanceStates) {
			try{
				AttendanceStateEnum valueOf = AttendanceStateEnum.valueOf(attendanceState.getNome());
				valueOf.setId(attendanceState.getId());
//				attendanceStateEnum2ID.put(valueOf, attendanceState.getId());
			}
			catch(IllegalArgumentException e){
				logger.error("State not Recognized! :"+attendanceState.getNome());
			}
		}
	}	
	
}
