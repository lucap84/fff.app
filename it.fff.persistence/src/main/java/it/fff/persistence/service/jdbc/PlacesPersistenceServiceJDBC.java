package it.fff.persistence.service.jdbc;

import java.util.Set;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.PlacesPersistenceService;

/**
 * Created by lpelosi on 26/10/16.
 */
public class PlacesPersistenceServiceJDBC implements PlacesPersistenceService {
    @Override
    public WriteResultBO setCurrentPosition(int userId, double gpsLat, double gpsLong) throws PersistenceException {
        return null;
    }

    @Override
    public Set<PlaceBO> getPlacesByDescription(String token, double gpsLat, double gpsLong) throws PersistenceException {
        return null;
    }

    @Override
    public PlaceBO getPlaceByGPS(double gpsLat, double gpsLong) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO saveOrUpdatePlace(PlaceBO place, String token) throws PersistenceException {
        return null;
    }

    @Override
    public CityBO getCityByName(String cityName, String nationKey) throws PersistenceException {
        return null;
    }

    @Override
    public NationBO getNationByInternationalCode(String nationCode) throws PersistenceException {
        return null;
    }
}
