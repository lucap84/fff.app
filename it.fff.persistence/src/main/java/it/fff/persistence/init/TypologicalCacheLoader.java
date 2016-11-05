package it.fff.persistence.init;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.service.TypologicalPersistenceService;
import it.fff.persistence.util.PersistenceServiceProvider;

public class TypologicalCacheLoader {

	private static final Logger logger = LogManager.getLogger(TypologicalCacheLoader.class);
	private static TypologicalCacheLoader typologicalLoader;

	//	public static Map<EventStateEnum,Integer> eventStateEnum2ID = new HashMap<EventStateEnum,Integer>();
	//	public static Map<AttendanceStateEnum,Integer> attendanceStateEnum2ID = new HashMap<AttendanceStateEnum,Integer>();

	public List<PlaceTypeEnum> cachedPlaceTypes;
	public List<EventStateEnum> cachedEventStates;
	public List<AttendanceStateEnum> cachedAttendanceStates;

	private TypologicalCacheLoader() {
		logger.debug("Loading cached typological data...");
		//Fetch data on startup
		this.loadEventStates();
		this.loadAttendanceStates();
		this.loadPlaceTypes();
		logger.debug("...Typological cached data loaded");
	}

	public static TypologicalCacheLoader getInstance() {
		if (typologicalLoader == null) {
			typologicalLoader = new TypologicalCacheLoader();
		}
		return typologicalLoader;
	}

	private void loadPlaceTypes() {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService) PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");

		try {
			//fetch all PlaceTypeEnum
			cachedPlaceTypes = typologicalPersistenceService.getAllPlaceTypes();
		} catch (Exception e) {
			logger.error("Error loading typological data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadEventStates() {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService) PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");

		try {
			//fetch all EventStateEnum
			cachedEventStates = typologicalPersistenceService.getAllEventStates();
		} catch (Exception e) {
			logger.error("Error loading typological data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadAttendanceStates() {
		TypologicalPersistenceService typologicalPersistenceService = (TypologicalPersistenceService) PersistenceServiceProvider.getPersistenceService("typologicalPersistenceService");

		try {
			//fetch all AttendanceStateEnum
			cachedAttendanceStates = typologicalPersistenceService.getAllAttendanceStates();

		} catch (Exception e) {
			logger.error("Error loading typological data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<PlaceTypeEnum> getCachedPlaceTypes() {
		return cachedPlaceTypes;
	}

	public List<EventStateEnum> getCachedEventStates() {
		return cachedEventStates;
	}

	public List<AttendanceStateEnum> getCachedAttendanceStates() {
		return cachedAttendanceStates;
	}

	public List<PlaceTypeEnum> getCachedPlaceTypes(boolean inCache) {
		if (!inCache) {
			this.loadPlaceTypes();
		}
		return cachedPlaceTypes;
	}

	public List<EventStateEnum> getCachedEventStates(boolean inCache) {
		if (!inCache) {
			this.loadEventStates();
		}
		return cachedEventStates;
	}

	public List<AttendanceStateEnum> getCachedAttendanceStates(boolean inCache) {
		if (!inCache) {
			this.loadAttendanceStates();
		}
		return cachedAttendanceStates;
	}

}

