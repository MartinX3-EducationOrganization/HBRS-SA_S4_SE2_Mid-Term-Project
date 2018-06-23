package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.User;

import java.sql.*;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class UserDAO extends GenericDAO<User> {
    public UserDAO(Connection con) {
        super(con, "table_user");
    }


    @Override
    public User getByID(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userid=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByColumnValue(String column, String keyword) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + column + "=" + " \'" + keyword + "\'");
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<User> getAllByID(int id) {
        return null;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            return createps(user, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean update(User user) {

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET userid=?,passwort=?,strasse=?,hausnr=?,plz=?,ort=?,email=?,telnr=?,faxnr=?,foto=?,kurzvorstellung=? WHERE userid=" + user.getId());
            return createps(user, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean createps(User user, PreparedStatement ps) throws SQLException {
        ps.setInt(1, user.getId());
        ps.setString(2, user.getPasswort());
        ps.setString(3, user.getStrasse());
        ps.setString(4, user.getHausnr());
        ps.setString(5, user.getPlz());
        ps.setString(6, user.getOrt());
        ps.setString(7, user.getEmail());
        ps.setString(8, user.getTelNr());
        ps.setString(9, user.getFaxNr());
        ps.setBytes(10, user.getFoto());
        ps.setString(11, user.getKurzVorstellung());
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }

    private User readResults(ResultSet rs) throws SQLException {
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setPasswort(rs.getString(2));
            user.setStrasse(rs.getString(3));
            user.setHausnr(rs.getString(4));
            user.setPlz(rs.getString(5));
            user.setOrt(rs.getString(6));
            user.setEmail(rs.getString(7));
            user.setTelNr(rs.getString(8));
            user.setFaxNr(rs.getString(9));
            user.setFoto(rs.getBytes(10));
            user.setKurzVorstellung(rs.getString(11));
            return user;
        }
        return null;
    }
}
