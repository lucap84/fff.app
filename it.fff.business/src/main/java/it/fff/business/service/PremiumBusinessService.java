package it.fff.business.service;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface PremiumBusinessService extends BusinessService {

	public CreateResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws  PersistenceException;
}
