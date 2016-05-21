package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
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
	public WriteResultBO registerUser(UserBO userBO) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO updateProfileImage(ProfileImageBO eoInput) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public UserBO getUser(int userId) throws SQLException {
		UserBO bo = new UserBO();
		bo.setId(1);
		
		bo.setNome("nome");
		bo.setCognome("cognome");
		bo.setDataNascita("1900-01-01");
		bo.setDescrizione("descrizione");
		bo.setFlagAttivo(true);
		
		bo.setLastPositionDate("1900-01-02");
		bo.setLastPositionLat(1.23);
		bo.setLastPositionLong(3.45);
		
		AccountBO accountBO = new AccountBO();

		SessionBO sessionBO = new SessionBO();
		sessionBO.setAccount(accountBO);
		sessionBO.setDataLogin("1900-01-01");
		sessionBO.setDataLogout("1900-01-01");
		sessionBO.setDeviceId("mydev-011");
		sessionBO.setLogged(true);
		sessionBO.setSharedKey("981479y4re982yur994hf984yur942872387410847019");
		
		List<SessionBO> sessionsBO = new ArrayList<SessionBO>();
		sessionsBO.add(sessionBO);
		
		accountBO.setId(1);
		accountBO.setEmail("email@mail.it");
		accountBO.setFlgValidita(true);
		accountBO.setFlgVerificato(true);
		accountBO.setPassword("jhgdjqhgd3877geid2b");
		accountBO.setUserId(bo.getId());
		accountBO.setSessions(sessionsBO);
		
		bo.setAccount(accountBO);
		
		AchievementTypeBO achievementTypeBO = new AchievementTypeBO();
		achievementTypeBO.setId(1);
		achievementTypeBO.setNome("AchievementType 1");
		achievementTypeBO.setDescrizione("AchievementType 1");
		
		AchievementBO achievementBO = new AchievementBO();
		achievementBO.setId(1);
		achievementBO.setDataOttenimento("1901-01-01");
		achievementBO.setType(achievementTypeBO);
		
		List<AchievementBO> achievementsBO = new ArrayList<AchievementBO>();
		achievementsBO.add(achievementBO);
		
		bo.setAchievements(achievementsBO);
		
		LanguageBO languageBO = new LanguageBO();
		languageBO.setId(1);
		languageBO.setNome("Italian");;
		languageBO.setIso639_1("it");
		languageBO.setIso639_2("ita");
		languageBO.setIso639_3("ita");
		
		List<LanguageBO> languagesBO = new ArrayList<LanguageBO>();
		languagesBO.add(languageBO);
		
		bo.setLingue(languagesBO);
		
		NationBO nationBO = new NationBO();
		nationBO.setId(1);
		nationBO.setNome("Italia");
		nationBO.setInternationalKey("380");
		nationBO.setInternationalCodeAplha2("IT");
		nationBO.setInternationalCodeAplha3("ITA");

		
		bo.setNazionalita(nationBO);
		
		return bo;
	}

	@Override
	public WriteResultBO updateUserData(UserBO bo) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(bo.getId());
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO cancelAttendance(int eventId, int userId) throws SQLException {
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
		bo.setFileName("fileMock.jpg");
		bo.setImageAsB64("wertyuiopa");
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
		UserBO user = this.getUser(1);
		AccountBO bo = user.getAccount();
		bo.setFacebookId(facebookId);
		return bo;
	}

	@Override
	public AccountBO getUserAccountByEmail(String email) throws Exception {
		UserBO user = this.getUser(1);
		AccountBO bo = user.getAccount();
		bo.setEmail(email);
		return bo;
	}

}
