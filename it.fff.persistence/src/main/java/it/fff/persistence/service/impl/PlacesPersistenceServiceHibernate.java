package it.fff.persistence.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.persistence.service.PlacesPersistenceService;

public class PlacesPersistenceServiceHibernate implements PlacesPersistenceService {

	private static final Logger logger = LogManager.getLogger(PlacesPersistenceServiceHibernate.class);

	@Override
	public WriteResultBO setCurrentPosition(int userId, int eventId, PlaceBO bo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceBO> getPlacesByDescription(String description) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
