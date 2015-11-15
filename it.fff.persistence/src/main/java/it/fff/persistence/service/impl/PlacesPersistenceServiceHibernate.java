package it.fff.persistence.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.persistence.service.PlacesPersistenceService;

public class PlacesPersistenceServiceHibernate implements PlacesPersistenceService {

	private static final Logger logger = LogManager.getLogger(PlacesPersistenceServiceHibernate.class);

	@Override
	public UpdateResultBO setCurrentPosition(int userId, int eventId, PlaceEO eo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceEO> getPlacesByDescription(String description) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
