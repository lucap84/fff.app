package it.fff.persistence.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventCategoryEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.business.common.eo.MessageStandardEO;
import it.fff.business.common.eo.NationEO;
import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.business.common.mapper.AchievementMapper;
import it.fff.business.common.mapper.AchievementTypeMapper;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.EventCategoryMapper;
import it.fff.business.common.mapper.EventMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.business.common.mapper.LanguageMapper;
import it.fff.business.common.mapper.MessageStandardMapper;
import it.fff.business.common.mapper.NationMapper;
import it.fff.business.common.mapper.SubscriptionTypeMapper;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.persistence.service.TypologicalPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class TypologicalPersistenceServiceHibernate implements TypologicalPersistenceService {

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws Exception {
		List<EventCategoryBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	List<EventCategoryEO> eos = session.createCriteria(EventCategoryEO.class).list();
	    	bos = EventCategoryMapper.getInstance().mapEOs2BOs(eos);
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllEventCategories() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<EventStateEO> getAllEventStates() throws Exception {
		List<EventStateEO> eos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	eos = session.createCriteria(EventStateEO.class).list();
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllEventStates() ",e);
	     }finally {
	        session.close(); 
	     }
		return eos;
	}

	@Override
	public List<AttendanceStateEO> getAllAttendanceStates() throws Exception {
		List<AttendanceStateEO> eos = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			eos = session.createCriteria(AttendanceStateEO.class).list();
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllAttendanceStates() ",e);
	     }finally {
	        session.close(); 
	     }
		return eos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws Exception {
		List<MessageStandardBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	List<MessageStandardEO> eos = session.createCriteria(MessageStandardEO.class).list();
	    	bos = MessageStandardMapper.getInstance().mapEOs2BOs(eos);
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllStandardMessages() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws Exception {
		List<AchievementTypeBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	List<AchievementTypeEO> eos = session.createCriteria(AchievementTypeEO.class).list();
	    	bos = AchievementTypeMapper.getInstance().mapEOs2BOs(eos);
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllAchievementTypes() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws Exception {
		List<SubscriptionTypeBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	List<SubscriptionTypeEO> eos = session.createCriteria(SubscriptionTypeEO.class).list();
	    	bos = SubscriptionTypeMapper.getInstance().mapEOs2BOs(eos);
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllSubscriptionTypes() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws Exception {
		List<LanguageBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	List<LanguageEO> eos = session.createCriteria(LanguageEO.class).list();
	    	bos = LanguageMapper.getInstance().mapEOs2BOs(eos);
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllLanguages() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws Exception {
		List<NationBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
	    	List<NationEO> eos = session.createCriteria(NationEO.class).list();
	    	bos = NationMapper.getInstance().mapEOs2BOs(eos);
	    }catch (HibernateException e) {
	        e.printStackTrace();
	        throw new Exception("HibernateException during getAllNations() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

}
