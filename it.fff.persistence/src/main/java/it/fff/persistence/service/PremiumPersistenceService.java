package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.SubscriptionEO;

public interface PremiumPersistenceService extends PersistenceService {

	CreateResultBO upgradeToPremium(int userId, SubscriptionEO eo) throws SQLException;

}
