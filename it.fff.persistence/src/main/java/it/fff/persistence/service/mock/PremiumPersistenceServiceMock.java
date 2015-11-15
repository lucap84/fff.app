package it.fff.persistence.service.mock;

import java.sql.SQLException;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.eo.SubscriptionEO;
import it.fff.persistence.service.PremiumPersistenceService;

public class PremiumPersistenceServiceMock implements PremiumPersistenceService {

	@Override
	public CreateResultBO upgradeToPremium(int userId, SubscriptionEO eo) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

}
