package it.fff.persistence.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.MessageStandardBO;
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
                //TODO popola l'oggetto da ritornare
                bo = new EventBO();
                bo.setId(eventId);
                bo.setTitolo(null);
                bo.setStato(EventStateEnum.valueOf(null));
                bo.setDurata(0);
                bo.setDataInizio(null);
                bo.setDescrizione(null);

                List<AttendanceBO> attendancesByEvent = this.getAttendancesByEvent(eventId);
                bo.setPartecipazioni(attendancesByEvent);

                PlacesPersistenceServiceJDBC placeJDBC = new PlacesPersistenceServiceJDBC();

                bo.setLocation(placeJDBC.getPlaceById(-1));
                bo.setCategoria(this.getEventCategoryById(-1));
                bo.setMessages(this.getEventMessages(eventId));
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
    public MessageStandardBO getStandardMessageById(int stdMsgId) throws PersistenceException {
        logger.debug("begin getStandardMessageById...");

        MessageStandardBO bo = null;
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_STDMSG_BY_ID);
            prepStmt.setInt(1, stdMsgId);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //TODO popola l'oggetto da ritornare
                bo = new MessageStandardBO();
                bo.setId(stdMsgId);
                bo.setText(null);
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during getStandardMessageById() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...getStandardMessageById finished");
        return bo;
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
    public List<EventBO> searchEvents(  double gpsLatFrom,
                                        double gpsLatTo,
                                        double gpsLongFrom,
                                        double gpsLongTo,
                                        int idCategoria,
                                        int minPartecipanti) throws PersistenceException {
        logger.debug("begin searchEvents...");

        List<EventBO> bos = new ArrayList<EventBO>();
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_SEARCH_EVENTS);
            prepStmt.setDouble(1, gpsLatFrom);
            prepStmt.setDouble(2, gpsLatTo);
            prepStmt.setDouble(3, gpsLongFrom);
            prepStmt.setDouble(4, gpsLongTo);
            prepStmt.setInt(5, idCategoria);
            prepStmt.setInt(6, minPartecipanti);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                //TODO popola l'oggetto da ritornare
                EventBO bo = new EventBO();
                bo.setId(-1);
                bo.setTitolo(null);
                bo.setStato(EventStateEnum.valueOf(null));
                bo.setDurata(0);
                bo.setDataInizio(null);
                bo.setDescrizione(null);

                List<AttendanceBO> attendancesByEvent = this.getAttendancesByEvent(-1);
                bo.setPartecipazioni(attendancesByEvent);

                PlacesPersistenceServiceJDBC placeJDBC = new PlacesPersistenceServiceJDBC();

                bo.setLocation(placeJDBC.getPlaceById(-1));
                bo.setCategoria(this.getEventCategoryById(-1));
                bo.setMessages(this.getEventMessages(-1));

                bos.add(bo);
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during searchEvents() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...searchEvents finished");
        return bos;
    }

    @Override
    public List<MessageBO> getEventMessages(int eventId) throws PersistenceException {
        logger.debug("begin getEventMessages...");

        List<MessageBO> bos = new ArrayList<MessageBO>();
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_MESSAGES_BY_EVENT);
            prepStmt.setInt(1, eventId);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                //TODO popola l'oggetto da ritornare
                MessageBO bo = new MessageBO();
                bo.setId(-1);
                bo.setAttendanceId(-1);
                bo.setEventId(eventId);
                bo.setText(null);
                bo.setDataCreazione(null);
                bo.setMsgStd(this.getStandardMessageById(-1));

                bos.add(bo);
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during getEventMessages() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...getEventMessages finished");
        return bos;
    }

    @Override
    public EventCategoryBO getEventCategoryById(int eventCategoryId) throws PersistenceException {
        logger.debug("begin getEventCategoryById...");

        EventCategoryBO bo = null;
        Connection connection = null;
        PreparedStatement prepStmt = null;

        try {
            connection = JdbcUtil.buildConnection();
            prepStmt = connection.prepareStatement(JdbcUtil.QY_GET_EVENTCATEGORY_BY_ID);
            prepStmt.setInt(1, eventCategoryId);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //TODO popola l'oggetto da ritornare
                bo = new EventCategoryBO();
                bo.setId(eventCategoryId);
                bo.setNome(null);
                bo.setDescrizione(null);
            }

        } catch (SQLException e) {
            throw new PersistenceException("SQLException during getEventCategoryById() ", e);
        } finally {
            JdbcUtil.close(prepStmt, connection);
        }

        logger.debug("...getEventCategoryById finished");
        return bo;
    }
}
