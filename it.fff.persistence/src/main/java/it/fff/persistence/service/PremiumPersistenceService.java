package it.fff.persistence.service;


import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.persistence.exception.PersistenceException;

public interface PremiumPersistenceService extends PersistenceService {

	WriteResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws PersistenceException;

}
