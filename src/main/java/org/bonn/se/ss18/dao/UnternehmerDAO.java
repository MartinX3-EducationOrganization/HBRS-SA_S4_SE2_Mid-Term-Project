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
    public Unternehmer readbyId(int id) {
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
    public Set<Unternehmer> getAllbyId(int id) {
        return null;
    }

    @Override
    public boolean create(Unternehmer unternehmer) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?)");
            return createps(unternehmer, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Unternehmer unternehmer) {

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET unternehmenid=?,userid=?,firmenname=?,website=?,brancheid=? WHERE unternehmenid=" + unternehmer.getUnternehmerid());
            return createps(unternehmer, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean delete(Unternehmer unternehmer) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + unternehmer.getiD());
            if (i == 1) {
                return true;
            }
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
        ps.setInt(2, unternehmer.getiD());
        ps.setString(3, unternehmer.getFirmenname());
        ps.setString(4, unternehmer.getWebsite());
        ps.setInt(5, unternehmer.getBranchenid());
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }

    private Unternehmer readResults(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Unternehmer unternehmer = new Unternehmer();
            unternehmer.setUnternehmerid(rs.getInt(1));
            unternehmer.setiD(rs.getInt(2));
            unternehmer.setFirmenname(rs.getString(3));
            unternehmer.setWebsite(rs.getString(4));
            unternehmer.setBranchenid(rs.getInt(5));
            return unternehmer;
        }
        return null;
    }
}
