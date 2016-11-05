package it.fff.persistence.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.PlacesPersistenceService;
import it.fff.persistence.util.JdbcUtil;

/**
 * Created by lpelosi on 26/10/16.
 */
public class PlacesPersistenceServiceJDBC implements PlacesPersistenceService {

    private static final Logger logger = LogManager.getLogger(PlacesPersistenceServiceJDBC.class);

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
    public CityBO getCityById(int cityId) throws PersistenceException {
        logger.debug("begin getCityById...");

        CityBO bo = null;
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_CITY_BY_ID);
            prepStmt.setInt(1, cityId);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //TODO popola l'oggetto da ritornare
                bo = new CityBO();
                bo.setId(cityId);
                bo.setNome(null);

                bo.setNazione(this.getNationById(-1));
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during getCityById() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...getCityById finished");
        return bo;
    }

    @Override
    public NationBO getNationByInternationalCode(String nationCode) throws PersistenceException {
        return null;
    }

    @Override
    public NationBO getNationById(int nationId) throws PersistenceException {
        logger.debug("begin getNationById...");

        NationBO bo = null;
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_COUNTRY_BY_ID);
            prepStmt.setInt(1, nationId);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //TODO popola l'oggetto da ritornare
                bo = new NationBO();
                bo.setId(nationId);
                bo.setNome(null);
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during getNationById() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...getNationById finished");
        return bo;
    }

    @Override
    public PlaceBO getPlaceById(int placeId) throws PersistenceException {
        logger.debug("begin getPlaceById...");

        PlaceBO bo = null;
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_PLACE_BY_ID);
            prepStmt.setInt(1, placeId);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //TODO popola l'oggetto da ritornare
                bo = new PlaceBO();
                bo.setId(placeId);
                bo.setNome(null);
                bo.setAddressRoute(null);
                bo.setCivico(null);
                bo.setCap(null);
                bo.setDataAggiornamento(null);
                bo.setDataCreazione(null);
                bo.setGpsLat(-1);
                bo.setGpsLong(-1);
                bo.setPlaceKey(null);

                bo.setPlaceType(PlaceTypeEnum.valueOf(null));
                bo.setCity(this.getCityById(-1));
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during getPlaceById() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...retrieveEvent finished");
        return bo;

    }
}
