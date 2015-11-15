package it.fff.business.service.impl;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.service.PremiumBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class PremiumBusinessServiceImpl implements PremiumBusinessService {

	private PersistenceServiceFacade persistenceFacade;
	
	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}
	
	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}
	
	@Override
	public CreateResultBO upgradeToPremium(int userId, SubscriptionBO subscriptionBO) throws PersistenceException {
		CreateResultBO resultBO = persistenceFacade.upgradeToPremium(userId, subscriptionBO);
		return resultBO;
	}

}
