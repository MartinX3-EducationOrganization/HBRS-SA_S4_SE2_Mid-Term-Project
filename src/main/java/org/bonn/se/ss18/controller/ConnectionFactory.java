package org.bonn.se.ss18.controller;

import org.bonn.se.ss18.dao.*;
import org.bonn.se.ss18.service.Tables;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;
    private DataSource dsource;
    private static final String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/amoham2s";
    private static final String username = "amoham2s";
    private static final String password = "amoham2s";

    private ConnectionFactory() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionFactory();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    public GenericDAO getDAO(Tables t) throws SQLException {

        try {
            if (connection == null || connection.isClosed()) //Let's ensure our connection is open
            {
                open();
            }
        } catch (SQLException e) {
            throw e;
        }

        switch (t) {
            case table_user:
                return new UserDAO(connection);
            case table_student:
                return new StudentDAO(connection);
            case table_unternehmen:
                return new UnternehmerDAO(connection);
            case table_qualifikation:
                return new QualifikationDAO(connection);
            case table_dokument:
                return new DokumentDAO(connection);
            case table_branche:
                return new BrancheDAO(connection);
            case table_bewerbunganlage:
                return new BewerbunganlageDAO(connection);
            case table_bewerbung:
                return new BewerbungDAO(connection);
            default:
                throw new SQLException("Trying to link to an unexistant table.");

        }

    }

    protected void open() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = dsource.getConnection();
            }
        } catch (SQLException e) {
            throw e;
        }

    }

    protected void close() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    protected void finalize() throws SQLException {

        try {
            close();
        } finally {
            try {
                super.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }
}