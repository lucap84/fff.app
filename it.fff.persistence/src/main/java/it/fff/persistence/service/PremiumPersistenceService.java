package it.fff.persistence.service;


import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.SubscriptionBO;

public interface PremiumPersistenceService extends PersistenceService {

	WriteResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws Exception;

}
