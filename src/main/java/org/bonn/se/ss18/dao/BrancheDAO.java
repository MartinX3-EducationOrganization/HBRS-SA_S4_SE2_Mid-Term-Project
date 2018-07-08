package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Branche;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class BrancheDAO extends GenericDAO<Branche> {
    public BrancheDAO(Connection con) {
        super(con, "table_branche", "brancheid");
    }

    public Branche getByID(int id) throws SQLException {
        return null;
    }

    @Override
    public Set<Branche> getAllByID(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Branche user) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Branche user) throws SQLException {
        return false;
    }

    public List<Branche> getBranches() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            List<Branche> branchenlist = new ArrayList<>();
            while (rs.next()) {
                branchenlist.add(new Branche(rs.getInt("brancheid"), rs.getString("bezeichnung")));
            }
            return branchenlist;
        } catch (SQLException ex) {
            return null;
        }
    }
}
