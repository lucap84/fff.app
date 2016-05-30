package it.fff.persistence.service.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.enums.UserSexEnum;
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
import it.fff.persistence.service.UserPersistenceService;

public class UserPersistenceServiceMock implements UserPersistenceService {

	@Override
	public WriteResultBO registerUser(UserBO userBO) throws Exception {
		this.updateSecureConfiguration(userBO);
		
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO updateProfileImage(ProfileImageBO eoInput) throws Exception {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public UserBO getUser(int userId) throws Exception {
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
		AchievementTypeBO achievementTypeBO = typologicalMock.getAllAchievementTypes().get(0);
		
		AchievementBO achievementBO = new AchievementBO();
		achievementBO.setId(1);
		achievementBO.setDataOttenimento("1901-01-01");
		achievementBO.setType(achievementTypeBO);
		
		List<AchievementBO> achievementsBO = new ArrayList<AchievementBO>();
		achievementsBO.add(achievementBO);
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
	public WriteResultBO updateUserData(UserBO bo) throws Exception {
		
		this.updateSecureConfiguration(bo);
		
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(bo.getId());
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws Exception {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public EmailInfoBO getEmailInfo(String email) throws Exception {
		EmailInfoBO bo = new EmailInfoBO();
		bo.setEmail(email);
		bo.setExisting(false);
		bo.setValidAccount(false);
		bo.setVerifiedAccount(false);
		return bo;
	}

	@Override
	public List<FeedbackEnum> getUserFeedbacks(int userId) throws Exception {
		
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
	public ProfileImageBO readProfileImage(int userId) throws Exception {
		ProfileImageBO bo = new ProfileImageBO();
		bo.setId(1); 
		bo.setExtension("jpg");
		bo.setFileName("filemock.jpg");
		bo.setHash("filemock.jpg".hashCode()+"");
		bo.setImageAsB64("qwertyuiopasdfghjk34567890");
		bo.setImageInputStream(null);
		bo.setName("filemock");
		bo.setParameters(null);
		bo.setPath("/path/to/this/mock/image/");
		bo.setProfileImage(true);
		bo.setSize(12345);
		bo.setUserId(userId);
		return bo;
	}

	@Override
	public List<AttendanceBO> getAttendancesByUser(int userId) throws Exception {
		EventPersistenceServiceMock mock = new EventPersistenceServiceMock();
		List<AttendanceBO> attendances = mock.getAttendancesByEvent(userId);
		return attendances;
	}

	@Override
	public AccountBO getUserAccountByFacebookId(long facebookId) throws Exception {
		AccountBO bo = this.getUserAccountByEmail("mailmock@mock.it");
		bo.setFacebookId(facebookId);
		return bo;
	}

	@Override
	public AccountBO getUserAccountByEmail(String email) throws Exception {
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
