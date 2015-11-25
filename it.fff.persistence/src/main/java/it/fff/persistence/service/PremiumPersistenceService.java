package it.fff.persistence.service;


import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.SubscriptionBO;

public interface PremiumPersistenceService extends PersistenceService {

	CreateResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws Exception;

}
