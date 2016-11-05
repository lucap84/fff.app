package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.PremiumPersistenceService;

public class PremiumPersistenceServiceMock implements PremiumPersistenceService {

	@Override
	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(userId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

}
