package org.bonn.se.ss18.dao;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.entity.User;

import java.sql.*;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class UnternehmerDAO extends GenericDAO<Unternehmer> {
    public UnternehmerDAO(Connection con) {
        super(con, "table_unternehmen", "unternehmenid");
    }

    public Unternehmer getByID(int id, UserDAO userDAO) {
        try (Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT userid FROM %s WHERE %s=%s", super.tableName, "userid", id));
            if (resultSet.next()) {
                return readResults(
                        getRsByID(id + ""),
                        userDAO.getByID(id)
                );
            }
        } catch (SQLException e) {
            Notification.show("Keine Verbindung zur Datenbank!\n" + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public ResultSet getRsByID(String id) {
        try (Statement statement = con.createStatement()) {
            return statement.executeQuery(String.format("SELECT * FROM %s WHERE userid='%s'", tableName, id));
        } catch (SQLException e) {
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Set<Unternehmer> getAllByID(int id) {
        return null;
    }

    @Override
    public boolean create(Unternehmer unternehmer) {
        try {
            PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO %s(userid,firmenname,website,ansprechpartner,brancheid) VALUES (?, ?, ?, ?, ?)", tableName));
            return createps(unternehmer, ps);
        } catch (SQLException e) {
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean update(Unternehmer unternehmer) {
        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE %s SET userid=?,firmenname=?,website=?,ansprechpartner=?,brancheid=? WHERE unternehmenid=%d", tableName, unternehmer.getUnternehmerid()));
            return createps(unternehmer, ps);

        } catch (SQLException ex) {
            Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }

        /*
        Statements für Unternehmen
     */

    private boolean createps(Unternehmer unternehmer, PreparedStatement ps) throws SQLException {
        ps.setInt(1, unternehmer.getId());
        ps.setString(2, unternehmer.getFirmenname());
        ps.setString(3, unternehmer.getWebsite());
        ps.setString(4, unternehmer.getAnsprechpartner());
        ps.setInt(5, unternehmer.getBranchenid());
        int i = ps.executeUpdate();
        return i == 1;
    }

    private Unternehmer readResults(ResultSet rs, User user) throws SQLException {
        if (rs.next()) {
            Unternehmer unternehmer = new Unternehmer(user);
            unternehmer.setUnternehmerid(rs.getInt(1));
            unternehmer.setId(rs.getInt(2));
            unternehmer.setFirmenname(rs.getString(3));
            unternehmer.setWebsite(rs.getString(4));
            unternehmer.setAnsprechpartner(rs.getString(5));
            unternehmer.setBranchenid(rs.getInt(6));
            return unternehmer;
        }
        return null;
    }

    @Override
    public boolean delete(Unternehmer entity) {
        try (Statement statement = con.createStatement()) {
            if (statement.executeUpdate(String.format("DELETE FROM %s WHERE %s=%d", tableName, primaryKey, entity.getUnternehmerid())) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean deleteByUserID(int id, UserDAO userDAO) {
        return delete(getByID(id, userDAO));
    }
}
