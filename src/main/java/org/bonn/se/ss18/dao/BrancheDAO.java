package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Branche;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Branche> getBranches() throws SQLException {
        ResultSet rs = con.createStatement().executeQuery(String.format("SELECT * FROM %s", tableName));
        List<Branche> branchenlist = new ArrayList<>(rs.getFetchSize());
        while (rs.next()) {
            branchenlist.add(new Branche(rs.getInt("brancheid"), rs.getString("bezeichnung")));
        }
        return branchenlist;
    }
}
