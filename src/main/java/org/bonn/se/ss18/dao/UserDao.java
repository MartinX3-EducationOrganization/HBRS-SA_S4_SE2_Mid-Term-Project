package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.config.ConnectionFactory;
import org.bonn.se.ss18.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rjourd2s
 */
public class UserDao {

    public User getUser(int userID) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + userID);
            if (rs.next()) {
                // Nur für User erstmal später allgemein
                return new User(rs.getInt("userID"), rs.getString("passwort"), rs.getString("strasse"),
                        rs.getString("hausnr"), rs.getString("plz"), rs.getString("ort"), rs.getString("email"),
                        rs.getString("telnr"), rs.getString("faxnr"), rs.getBytes("foto"), rs.getString("kurzvorstellung"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}