package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.persistence.service.PremiumPersistenceService;

public class PremiumPersistenceServiceMock implements PremiumPersistenceService {

	@Override
	public WriteResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

}
