package it.fff.persistence.init;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
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
		this.loadPlaceTypes();
		logger.debug("...Typological data loaded");
	}
	
	private void loadPlaceTypes() {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		try {
			//fetch all PlaceTypeEnum
			List<PlaceTypeEnum> placeTypes = typologicalPersistenceService.getAllPlaceTypes();
		} 
		catch (Exception e) {
			logger.error("Error loading typological data: "+e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadEventStates(){
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		try {
			//fetch all EventStateEnum
			List<EventStateEnum> allEventStates = typologicalPersistenceService.getAllEventStates();
		} 
		catch (Exception e) {
			logger.error("Error loading typological data: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadAttendanceStates(){
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService)PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");
		
		try {
			//fetch all AttendanceStateEnum
			List<AttendanceStateEnum> allAttendanceStates = typologicalPersistenceService.getAllAttendanceStates();
		
		} 
		catch (Exception e) {
			logger.error("Error loading typological data: "+e.getMessage());
			e.printStackTrace();
		}
	}	
	
}
