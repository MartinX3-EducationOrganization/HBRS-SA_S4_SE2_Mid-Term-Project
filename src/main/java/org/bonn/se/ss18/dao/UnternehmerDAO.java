package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

/**
 * @author rjourd2s
 */
public class UnternehmerDAO extends GenericDAO<Unternehmer> {
    public UnternehmerDAO(Connection con) {
        super(con, "table_unternehmen", "unternehmenid");
    }

    @Override
    public Unternehmer getByID(UUID id) {
        try {
            ResultSet resultSet = con.createStatement().executeQuery(String.format("SELECT userid FROM %s WHERE %s=%s", super.tableName, "userid", id));
            if (resultSet.next()) {
                return readResults(
                        getRsByID(id + ""),
                        ((UserDAO) ConnectionFactory.getInstance().getDAO(Tables.table_user)).getByID(id)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getRsByID(String id) {
        try {
            return con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE userid='%s'", tableName, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Unternehmer> getAllByID(UUID id) {
        return null;
    }

    @Override
    public boolean create(Unternehmer unternehmer) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?,?)");
            return createps(unternehmer, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Unternehmer unternehmer) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET unternehmenid=?,userid=?,firmenname=?,website=?,brancheid=?,ansprechpartner=? WHERE unternehmenid=" + unternehmer.getUnternehmerid());
            return createps(unternehmer, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

        /*
        Statements für Unternehmen
     */

    private boolean createps(Unternehmer unternehmer, PreparedStatement ps) throws SQLException {
        ps.setInt(1, unternehmer.getUnternehmerid());
        ps.setString(2, unternehmer.getId().toString());
        ps.setString(3, unternehmer.getFirmenname());
        ps.setString(4, unternehmer.getWebsite());
        ps.setString(5, unternehmer.getAnsprechpartner());
        ps.setInt(6, unternehmer.getBranchenid());
        int i = ps.executeUpdate();

        // Eine Reihe(ROW)
        return i == 1;
    }

    private Unternehmer readResults(ResultSet rs, User user) throws SQLException {
        if (rs.next()) {
            Unternehmer unternehmer = new Unternehmer(user);
            unternehmer.setUnternehmerid(rs.getInt(1));
            unternehmer.setId(UUID.fromString(rs.getString(2)));
            unternehmer.setFirmenname(rs.getString(3));
            unternehmer.setWebsite(rs.getString(4));
            unternehmer.setAnsprechpartner(rs.getString(5));
            unternehmer.setBranchenid(rs.getInt(6));
            return unternehmer;
        }
        return null;
    }
}
