package it.fff.persistence.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import it.fff.persistence.exception.PersistenceException;


public class HibernateUtil {
	
	private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory;
    
    private static SessionFactory buildSessionFactory() throws PersistenceException {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            logger.debug("Hibernate Annotation Configuration loaded");
             
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            logger.debug("Hibernate Annotation serviceRegistry created");
             
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             
            return sessionFactory;
        }
        catch (Throwable ex) {
        	logger.error("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new PersistenceException(ex.getMessage());
        }
    }
     
    public static SessionFactory getSessionFactory() throws PersistenceException {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }	

}
