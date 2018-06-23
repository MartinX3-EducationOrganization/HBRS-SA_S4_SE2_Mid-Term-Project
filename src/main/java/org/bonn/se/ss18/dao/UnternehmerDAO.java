package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Unternehmer;

import java.sql.*;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class UnternehmerDAO extends GenericDAO<Unternehmer> {
    public UnternehmerDAO(Connection con) {
        super(con, "table_unternehmen");
    }

    @Override
    public Unternehmer getByID(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userid=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
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
        ps.setInt(2, unternehmer.getId());
        ps.setString(3, unternehmer.getFirmenname());
        ps.setString(4, unternehmer.getWebsite());
        ps.setInt(5, unternehmer.getBranchenid());
        ps.setString(6, unternehmer.getAnsprechpartner());
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }

    private Unternehmer readResults(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Unternehmer unternehmer = new Unternehmer();
            unternehmer.setUnternehmerid(rs.getInt(1));
            unternehmer.setId(rs.getInt(2));
            unternehmer.setFirmenname(rs.getString(3));
            unternehmer.setWebsite(rs.getString(4));
            unternehmer.setBranchenid(rs.getInt(5));
            unternehmer.setAnsprechpartner(rs.getString(6));
            return unternehmer;
        }
        return null;
    }
}
