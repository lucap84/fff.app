package it.fff.persistence.service.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.util.JdbcUtil;

/**
 * Created by lpelosi on 26/10/16.
 */
public class EventPersistenceServiceJDBC implements EventPersistenceService {

    private static final Logger logger = LogManager.getLogger(EventPersistenceServiceJDBC.class);

    @Override
    public EventBO retrieveEvent(int eventId) throws PersistenceException {
        logger.debug("begin retrieveEvent...");

        EventBO bo = null;
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_EVENT_BY_ID);
            prepStmt.setInt(1, eventId);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                bo = new EventBO();
                bo.setId(eventId);
                bo.setDescrizione(rs.getString(1));
                //TODO popola l'oggetto da ritornare

            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during retrieveEvent() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...retrieveEvent finished");
        return bo;
    }

    @Override
    public WriteResultBO cancelEvent(int eventId, int organizerId) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO updateEventState(int eventId, EventStateEnum state) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO createEvent(EventBO bo) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO createEventMessage(int attendanceId, String message) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws PersistenceException {
        return null;
    }

    @Override
    public WriteResultBO createAttandance(AttendanceBO eo) throws PersistenceException {
        return null;
    }

    @Override
    public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException {
        return null;
    }

    @Override
    public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
        return null;
    }

    @Override
    public List<EventBO> searchEvents(double gpsLatFrom, double gpsLatTo, double gpsLongFrom, double gpsLongTo, int idCategoria, int minPartecipanti) throws PersistenceException {
        return null;
    }

    @Override
    public List<MessageBO> getEventMessages(int eventId) throws PersistenceException {
        return null;
    }
}
