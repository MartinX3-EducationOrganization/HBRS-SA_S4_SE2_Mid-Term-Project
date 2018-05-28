package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.config.ConnectionFactory;
import org.bonn.se.ss18.entity.AbstractEntity;
import org.bonn.se.ss18.entity.User;

import java.sql.*;

/**
 * @author rjourd2s
 */
public class UserDao<T extends AbstractEntity> implements GenericDao<User> {
    private Connection connection = ConnectionFactory.getConnection();

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO table_user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            return createps(user, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public User read(int id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM table_user WHERE id=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean update(User user) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE table_user SET id=?,passwort=?,strasse=?,hausnr=?,plz=?,ort=?,email=?,telnr=?,faxnr=?,foto=?,kurzvorstellung=? WHERE id=" + user.getiD());
            return createps(user, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM table_user WHERE id=" + user.getiD());
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private boolean createps(User user, PreparedStatement ps) throws SQLException {
        ps.setInt(1, user.getiD());
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
            user.setiD(rs.getInt(1));
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
