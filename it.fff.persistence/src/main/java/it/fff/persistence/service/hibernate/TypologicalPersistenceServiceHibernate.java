package it.fff.persistence.service.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.EventCategoryEO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.business.common.eo.MessageStandardEO;
import it.fff.business.common.eo.NationEO;
import it.fff.business.common.eo.PlaceTypeEO;
import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.business.common.mapper.AchievementTypeMapper;
import it.fff.business.common.mapper.AttendanceStateMapper;
import it.fff.business.common.mapper.EventCategoryMapper;
import it.fff.business.common.mapper.EventStateMapper;
import it.fff.business.common.mapper.LanguageMapper;
import it.fff.business.common.mapper.MessageStandardMapper;
import it.fff.business.common.mapper.NationMapper;
import it.fff.business.common.mapper.PlaceTypeMapper;
import it.fff.business.common.mapper.SubscriptionTypeMapper;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.TypologicalPersistenceService;
import it.fff.persistence.util.HibernateUtil;

public class TypologicalPersistenceServiceHibernate implements TypologicalPersistenceService {

	@Override
	public List<EventCategoryBO> getAllEventCategories() throws PersistenceException {
		List<EventCategoryBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<EventCategoryEO> eos = session.createCriteria(EventCategoryEO.class).list();
	    	bos = EventCategoryMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllEventCategories() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<EventStateEnum> getAllEventStates() throws PersistenceException {
		List<EventStateEnum> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<EventStateEO> eos = session.createCriteria(EventStateEO.class).list();
	    	bos = EventStateMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllEventStates() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<AttendanceStateEnum> getAllAttendanceStates() throws PersistenceException {
		List<AttendanceStateEnum> bos = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
			List<AttendanceStateEO> eos = session.createCriteria(AttendanceStateEO.class).list();
			bos = AttendanceStateMapper.getInstance().mapEOs2BOs(eos);
			
			tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllAttendanceStates() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<MessageStandardBO> getAllStandardMessages() throws PersistenceException {
		List<MessageStandardBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<MessageStandardEO> eos = session.createCriteria(MessageStandardEO.class).list();
	    	bos = MessageStandardMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllStandardMessages() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<AchievementTypeBO> getAllAchievementTypes() throws PersistenceException {
		List<AchievementTypeBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<AchievementTypeEO> eos = session.createCriteria(AchievementTypeEO.class).list();
	    	bos = AchievementTypeMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllAchievementTypes() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<SubscriptionTypeBO> getAllSubscriptionTypes() throws PersistenceException {
		List<SubscriptionTypeBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<SubscriptionTypeEO> eos = session.createCriteria(SubscriptionTypeEO.class).list();
	    	bos = SubscriptionTypeMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllSubscriptionTypes() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<LanguageBO> getAllLanguages() throws PersistenceException {
		List<LanguageBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<LanguageEO> eos = session.createCriteria(LanguageEO.class).list();
	    	bos = LanguageMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllLanguages() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<NationBO> getAllNations() throws PersistenceException {
		List<NationBO> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<NationEO> eos = session.createCriteria(NationEO.class).list();
	    	bos = NationMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllNations() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

	@Override
	public List<PlaceTypeEnum> getAllPlaceTypes() throws PersistenceException {
		List<PlaceTypeEnum> bos = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    try{
	    	tx = session.beginTransaction();
	    	
	    	List<PlaceTypeEO> eos = session.createCriteria(PlaceTypeEO.class).list();
	    	bos = PlaceTypeMapper.getInstance().mapEOs2BOs(eos);
	    	
	    	tx.commit();
	    }catch (HibernateException e) {
	    	if(tx!=null)tx.rollback();
	        e.printStackTrace();
	        throw new PersistenceException("HibernateException during getAllEventStates() ",e);
	     }finally {
	        session.close(); 
	     }
		return bos;
	}

}
