package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.User;

import java.sql.*;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class UserDAO extends GenericDAO<User> {
    public UserDAO(Connection con) {
        super(con, "table_user", "userid");
    }

    public User getByID(int id) {
        try {
            return readResults(super.getRsByID(id + ""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByColumnValue(String column, String keyword) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s WHERE %s= '%s'", tableName, column, keyword));
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
            PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO %s(passwort,strasse,hausnr,plz,ort,email,telnr,faxnr,foto,kurzvorstellung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)", tableName));
            return createps(user, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean update(User user) {

        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE %s SET passwort=?,strasse=?,hausnr=?,plz=?,ort=?,email=?,telnr=?,faxnr=?,foto=?,kurzvorstellung=? WHERE userid=%d", tableName, user.getId()));
            return createps(user, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean createps(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getPasswort());
        ps.setString(2, user.getStrasse());
        ps.setString(3, user.getHausnr());
        ps.setString(4, user.getPlz());
        ps.setString(5, user.getOrt());
        ps.setString(6, user.getEmail());
        ps.setString(7, user.getTelNr());
        ps.setString(8, user.getFaxNr());
        ps.setBytes(9, user.getFoto());
        ps.setString(10, user.getKurzVorstellung());
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

    public boolean delete(int id) {
        return delete(getByID(id));
    }
}
