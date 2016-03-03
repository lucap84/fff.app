package it.fff.business.service.impl;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.service.PremiumBusinessService;
import it.fff.integration.facade.exception.PersistenceException;
import it.fff.integration.facade.service.IntegrationServiceFacade;

public class PremiumBusinessServiceImpl implements PremiumBusinessService {

	private IntegrationServiceFacade integrationFacade;
	
	public IntegrationServiceFacade getIntegrationFacade() {
		return integrationFacade;
	}



	public void setIntegrationFacade(IntegrationServiceFacade integrationFacade) {
		this.integrationFacade = integrationFacade;
	}



	@Override
	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException {
		WriteResultBO resultBO = integrationFacade.upgradeToPremium(userId, subscriptionBO);
		return resultBO;
	}

}
