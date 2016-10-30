package it.fff.persistence.service.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.enums.UserSexEnum;
import it.fff.clientserver.common.util.Constants;
import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.AchievementBO;
import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EmailInfoBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public WriteResultBO registerUser(UserBO userBO) throws PersistenceException {
		this.updateSecureConfiguration(userBO);
		
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO updateProfileImage(ProfileImageBO eoInput) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public UserBO getUser(int userId) throws PersistenceException {
		TypologicalPersistenceServiceMock typologicalMock = new TypologicalPersistenceServiceMock();
		UserBO bo = new UserBO();

		/*
		 * Account
		 */
		AccountBO accountBO = getUserAccountByEmail("mailmock@mock.it");
		bo.setAccount(accountBO);
		
		/*
		 * Achievements
		 */
		AchievementTypeBO achievementTypeBO1 = typologicalMock.getAllAchievementTypes().get(0);
		AchievementTypeBO achievementTypeBO2 = typologicalMock.getAllAchievementTypes().get(1);
		
		AchievementBO achievementBO1 = new AchievementBO();
		achievementBO1.setId(1);
		achievementBO1.setDataOttenimento(Constants.DATE_FORMATTER.format(new Date()));
		achievementBO1.setType(achievementTypeBO1);
		
		AchievementBO achievementBO2 = new AchievementBO();
		achievementBO2.setId(2);
		achievementBO2.setDataOttenimento(Constants.DATE_FORMATTER.format(new Date()));
		achievementBO2.setType(achievementTypeBO2);		
		
		List<AchievementBO> achievementsBO = new ArrayList<AchievementBO>();
		achievementsBO.add(achievementBO1);
		achievementsBO.add(achievementBO2);
		bo.setAchievements(achievementsBO);
		
		//Info base
		bo.setCognome("cognome");
		bo.setDataNascita("1980-01-01");
		bo.setDescrizione("descrizione utente mock");
		
		//Feedbacks non impostati sulla getUser
		
		bo.setFlagAttivo(true);
		bo.setId(1);
		bo.setLastPositionDate("1900-01-02");
		bo.setLastPositionLat(1.23);
		bo.setLastPositionLong(3.45);
		
		/*
		 * Lingue
		 */
		
		List<LanguageBO> languagesBO = typologicalMock.getAllLanguages();
		bo.setLingue(languagesBO);

		/*
		 * Nazionalita'
		 */
		NationBO nationBO = typologicalMock.getAllNations().get(0);
		bo.setNazionalita(nationBO);
		
		bo.setNome("nome");
		bo.setNumUpdate(1);
		
		//Profile Images non impostate sulla getUser
		
		bo.setSesso(UserSexEnum.M);
		
		return bo;
	}

	@Override
	public WriteResultBO updateUserData(UserBO bo) throws PersistenceException {
		
		this.updateSecureConfiguration(bo);
		
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(bo.getId());
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public EmailInfoBO getEmailInfo(String email) throws PersistenceException {
		EmailInfoBO bo = new EmailInfoBO();
		bo.setEmail(email);
		bo.setExisting(false);
		bo.setValidAccount(false);
		bo.setVerifiedAccount(false);
		return bo;
	}

	@Override
	public List<FeedbackEnum> getUserFeedbacks(int userId) throws PersistenceException {
		
		List<FeedbackEnum> feedbacks = new ArrayList<FeedbackEnum>();
		feedbacks.add(FeedbackEnum.POSITIVE);
		feedbacks.add(FeedbackEnum.POSITIVE);
		feedbacks.add(FeedbackEnum.POSITIVE);
		feedbacks.add(FeedbackEnum.POSITIVE);
		feedbacks.add(FeedbackEnum.NEGATIVE);
		feedbacks.add(FeedbackEnum.NEGATIVE);
		
		return feedbacks;
	}

	@Override
	public ProfileImageBO readProfileImage(int userId) throws PersistenceException {
		ProfileImageBO bo = new ProfileImageBO();
		bo.setId(1); 
		bo.setExtension("jpg");
		bo.setFileName("filemock.jpg");
		bo.setImageAsB64("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");
		bo.setHash(bo.getImageAsB64().hashCode()+"");
		bo.setImageInputStream(null);
		bo.setName("filemock");
		bo.setParameters(null);
		bo.setPath("/path/to/this/mock/image/");
		bo.setProfileImage(true);
		bo.setSize(bo.getImageAsB64().getBytes().length);
		bo.setUserId(userId);
		return bo;
	}

	@Override
	public List<AttendanceBO> getAttendancesByUser(int userId) throws PersistenceException {
		EventPersistenceServiceMock mock = new EventPersistenceServiceMock();
		List<AttendanceBO> attendances = mock.getAttendancesByEvent(userId);
		return attendances;
	}

	@Override
	public AccountBO getUserAccountByFacebookId(long facebookId) throws PersistenceException {
		AccountBO bo = this.getUserAccountByEmail("mailmock@mock.it");
		bo.setFacebookId(facebookId);
		return bo;
	}

	@Override
	public AccountBO getUserAccountByEmail(String email) throws PersistenceException {
		AccountBO bo = new AccountBO();
		
		SessionBO sessionBO = new SessionBO();
		sessionBO.setId(1);
		sessionBO.setAccount(bo);
		sessionBO.setDataLogin("1900-01-01");
		sessionBO.setDataLogout("1900-01-01");
		sessionBO.setDeviceId("mydev-011");
		sessionBO.setLogged(true);
		sessionBO.setSharedKey("981479y4re982yur994hf984yur942872387410847019");
		sessionBO.setExpiresKey(12345678L);
		
		List<SessionBO> sessionsBO = new ArrayList<SessionBO>();
		sessionsBO.add(sessionBO);
		
		bo.setId(1);
		bo.setEmail(email);
		bo.setFlgValidita(true);
		bo.setFlgVerificato(true);
		bo.setPassword("jhgdjqhgd3877geid2b");
		bo.setUserId(bo.getId());
		bo.setSessions(sessionsBO);
		
		return bo;
	}

	private void updateSecureConfiguration(UserBO userBO) {
		int userID = userBO.getId();
		String deviceId = null;
		String sharedKey = null;
		
		AccountBO account = userBO.getAccount();
		if(account!=null){
			List<SessionBO> sessions = account.getSessions();
			for (SessionBO sessionBO : sessions) {
				if (sessionBO.isLogged()){
					deviceId= sessionBO.getDeviceId();
					sharedKey = sessionBO.getSharedKey();
					break;
				}
			}
			
			Map<String, String> device2SharedKey = SecurityPersistenceServiceMock.secrets.get(userID);
			if(device2SharedKey==null){
				device2SharedKey = new HashMap<String, String>();
				SecurityPersistenceServiceMock.secrets.put(userID, device2SharedKey);
			}
			device2SharedKey.put(deviceId, sharedKey);
		}
	}
}
