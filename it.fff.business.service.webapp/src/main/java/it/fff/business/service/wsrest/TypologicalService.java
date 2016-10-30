package it.fff.business.service.wsrest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.business.common.util.LogUtils;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.clientserver.common.dto.AchievementTypeDTO;
import it.fff.clientserver.common.dto.EventCategoryDTO;
import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.dto.LanguageDTO;
import it.fff.clientserver.common.dto.MessageStandardDTO;
import it.fff.clientserver.common.dto.NationDTO;
import it.fff.clientserver.common.dto.SubscriptionTypeDTO;
import it.fff.clientserver.common.enums.EventStateEnum;

@Component("typologicalService")
@Path("/typological")
public class TypologicalService extends ApplicationService {
	
	private static final Logger logger = LogManager.getLogger(TypologicalService.class);
	
	@Autowired
	private BusinessServiceFacade businessServiceFacade;	
	
	public TypologicalService() {
		logger.debug("Service created");
	}
	
	@GET
	@Path("languages/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LanguageDTO> getAllLanguagesJSON(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllLanguages(request);
	}
	@GET
	@Path("languages/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<LanguageDTO> getAllLanguagesXML(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllLanguages(request);
	}

	
	@GET
	@Path("subscriptionTypes/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubscriptionTypeDTO> getAllSubscriptionTypesJSON(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllSubscriptionTypes(request);
	}
	@GET
	@Path("subscriptionTypes/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<SubscriptionTypeDTO> getAllSubscriptionTypesXML(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllSubscriptionTypes(request);
	}
	
	@GET
	@Path("achievementTypes/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AchievementTypeDTO> getAllAchievementTypesJSON(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllAchievementTypes(request);
	}
	@GET
	@Path("achievementTypes/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<AchievementTypeDTO> getAllAchievementTypesXML(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllAchievementTypes(request);
	}	
	
	@GET
	@Path("standardMessages/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MessageStandardDTO> getAllStandardMessagesJSON(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllStandardMessages(request);
	}
	@GET
	@Path("standardMessages/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<MessageStandardDTO> getAllStandardMessagesXML(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllStandardMessages(request);
	}
	
//	@GET
//	@Path("attendanceStates/json")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<AttendanceStateDTO> getAllAttendanceStatesJSON(@Context HttpServletRequest request) throws BusinessException {
//		return this.getAllAttendanceStates(request);
//	}
//	@GET
//	@Path("attendanceStates/xml")
//	@Produces(MediaType.APPLICATION_XML)
//	public List<AttendanceStateDTO> getAllAttendanceStatesXML(@Context HttpServletRequest request) throws BusinessException {
//		return this.getAllAttendanceStates(request);
//	}	
	
//	@GET
//	@Path("eventStates/json")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<EventStateEnum> getAllEventStatesJSON(@Context HttpServletRequest request) throws BusinessException {
//		return this.getAllEventStates(request);
//	}
//	@GET
//	@Path("eventStates/xml")
//	@Produces(MediaType.APPLICATION_XML)
//	public List<EventStateEnum> getAllEventStatesXML(@Context HttpServletRequest request) throws BusinessException {
//		return this.getAllEventStates(request);
//	}
	
	@GET
	@Path("eventCategories/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventCategoryDTO> getAllEventCategoriesJSON(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllEventCategories(request);
	}
	@GET
	@Path("eventCategories/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventCategoryDTO> getAllEventCategoriesXML(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllEventCategories(request);
	}
	
	@GET
	@Path("nations/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NationDTO> getAllNationsJSON(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllNations(request);
	}
	@GET
	@Path("nations/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<NationDTO> getAllNationsXML(@Context HttpServletRequest request) throws BusinessException {
		return this.getAllNations(request);
	}	
	

	/*
	 *	
	 *
	 *
	 *
	 *		Delegating methods 
	 *
	 *
	 *
	 *
	 *
	 */	

	private List<LanguageDTO> getAllLanguages(HttpServletRequest request) {
		List<LanguageDTO> languages = null;
		try{
			languages = businessServiceFacade.getAllLanguages();
		} catch (BusinessException e) {
			languages = new ArrayList<LanguageDTO>();
			languages.add(new LanguageDTO());
			super.manageErrors(e, languages.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return languages;
	}
	
	private List<SubscriptionTypeDTO> getAllSubscriptionTypes(HttpServletRequest request) {
		List<SubscriptionTypeDTO> subscriptionTypes = null;
		try{
			subscriptionTypes = businessServiceFacade.getAllSubscriptionTypes();
		} catch (BusinessException e) {
			subscriptionTypes = new ArrayList<SubscriptionTypeDTO>();
			subscriptionTypes.add(new SubscriptionTypeDTO());
			super.manageErrors(e, subscriptionTypes.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return subscriptionTypes;
	}
	
	private List<AchievementTypeDTO> getAllAchievementTypes(HttpServletRequest request) {
		List<AchievementTypeDTO> achievements = null;
		try{
			achievements = businessServiceFacade.getAllAchievementTypes();
		} catch (BusinessException e) {
			achievements = new ArrayList<AchievementTypeDTO>();
			achievements.add(new AchievementTypeDTO());
			super.manageErrors(e, achievements.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return achievements;
	}	
	
	private List<MessageStandardDTO> getAllStandardMessages(HttpServletRequest request) {
		List<MessageStandardDTO> msgsStandard = null;
		try{
			msgsStandard = businessServiceFacade.getAllStandardMessages();
		} catch (BusinessException e) {
			msgsStandard = new ArrayList<MessageStandardDTO>();
			msgsStandard.add(new MessageStandardDTO());
			super.manageErrors(e, msgsStandard.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return msgsStandard;
	}	
	
//	private List<AttendanceStateDTO> getAllAttendanceStates(HttpServletRequest request) {
//		List<AttendanceStateDTO> attendanceStates = null;
//		try{
//			attendanceStates = businessServiceFacade.getAllAttendanceStates();
//		} catch (BusinessException e) {
//			attendanceStates = new ArrayList<AttendanceStateDTO>();
//			logger.error(LogUtils.stackTrace2String(e));
//		}
//		return attendanceStates;
//	}	
	
//	private List<EventStateEnum> getAllEventStates(HttpServletRequest request) {
//		List<EventStateEnum> eventStates = null;
//		try{
//			eventStates = businessServiceFacade.getAllEventStates();
//		} catch (BusinessException e) {
//			eventStates = new ArrayList<EventStateEnum>();
//			logger.error(LogUtils.stackTrace2String(e));
//		}
//		return eventStates;
//	}
	
	private List<EventCategoryDTO> getAllEventCategories(HttpServletRequest request) {
		List<EventCategoryDTO> languages = null;
		try{
			languages = businessServiceFacade.getAllEventCategories();
		} catch (BusinessException e) {
			languages = new ArrayList<EventCategoryDTO>();
			languages.add(new EventCategoryDTO());
			super.manageErrors(e, languages.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return languages;
	}
	
	private List<NationDTO> getAllNations(HttpServletRequest request) {
		List<NationDTO> nations = null;
		try{
			nations = businessServiceFacade.getAllNations();
		} catch (BusinessException e) {
			nations = new ArrayList<NationDTO>();
			nations.add(new NationDTO());
			super.manageErrors(e, nations.get(0), request.getLocale());
			logger.error(LogUtils.stackTrace2String(e));
		}
		return nations;
	}

}
