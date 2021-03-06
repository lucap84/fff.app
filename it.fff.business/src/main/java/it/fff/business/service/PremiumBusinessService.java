package it.fff.business.service;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.integration.facade.exception.IntegrationException;

public interface PremiumBusinessService extends BusinessService {

	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws  IntegrationException;
}
