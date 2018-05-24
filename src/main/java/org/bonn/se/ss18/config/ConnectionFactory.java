package org.bonn.se.ss18.config;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/amoham2s";
    private static final String USER = "amoham2s";
    private static final String PASS = "amoham2s";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
