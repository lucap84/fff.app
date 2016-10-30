package it.fff.persistence.service.jdbc;

import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.PremiumPersistenceService;

/**
 * Created by lpelosi on 26/10/16.
 */
public class PremiumPersistenceServiceJDBC implements PremiumPersistenceService {
    @Override
    public WriteResultBO upgradeToPremium(int userId, SubscriptionBO bo) throws PersistenceException {
        return null;
    }
}
