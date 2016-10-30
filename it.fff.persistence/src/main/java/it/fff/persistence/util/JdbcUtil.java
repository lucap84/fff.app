package it.fff.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.util.ConfigurationProvider;
import it.fff.clientserver.common.util.Constants;

/**
 * Created by lpelosi on 29/10/16.
 */
public class JdbcUtil {

    private static final Logger logger = LogManager.getLogger(JdbcUtil.class);

    public static final String QY_GET_EVENT_BY_ID = "SELECT * FROM evento WHERE id = ?";
    public static final String QY_GET_PLACE_BY_ID = "SELECT * FROM luogo WHERE id = ?";
    public static final String QY_GET_CITY_BY_ID = "SELECT * FROM citta WHERE id = ?";
    public static final String QY_GET_COUNTRY_BY_ID = "SELECT * FROM nazione WHERE id = ?";
    public static final String QY_GET_EVENTCATEGORY_BY_ID = "SELECT * FROM categoria_evento WHERE id = ?";
    public static final String QY_GET_MESSAGES_BY_EVENT = "SELECT * FROM .... id = ?";
    public static final String QY_GET_STDMSG_BY_ID = "SELECT * FROM ... WHERE id = ?";

    public static final String QY_SEARCH_EVENTS = "SELECT * FROM ... WHERE id = ? and ? ? ? ?";

    public static final String QY_LOGIN_CHECK_ACCOUNT = "SELECT * FROM ... WHERE id = ? and ? ? ? ?";
    public static final String QY_LOGIN_NEWSESSION = "INSERT ... WHERE id = ? and ? ? ? ?";

    public static Connection buildConnection() throws SQLException{
        ConfigurationProvider cfgProvider = ConfigurationProvider.getInstance();
        Properties jdbcConfigProperties = cfgProvider.getJdbcConfigProperties();

        String jdbcDriver = jdbcConfigProperties.getProperty(Constants.PROP_JDBC_DRIVER);

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            logger.error("Where is your MySQL JDBC Driver? ("+Constants.PROP_JDBC_DRIVER+")");
            e.printStackTrace();
            throw new SQLException(e);
        }

        Connection connection = null;
        //TODO usa pool di connessioni

        String jdbcConnURL = jdbcConfigProperties.getProperty(Constants.PROP_JDBC_CONNECTION_URL);
        String jdbcConnUsr = jdbcConfigProperties.getProperty(Constants.PROP_JDBC_CONNECTION_USR);
        String jdbcConnPsw = jdbcConfigProperties.getProperty(Constants.PROP_JDBC_CONNECTION_PSW);

        connection = DriverManager.getConnection(jdbcConnURL, jdbcConnUsr, jdbcConnPsw);

        return connection;
    }

    public static void close(Statement statement, Connection connection) {

        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
