package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Branche;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class BrancheDAO extends GenericDAO<Branche> {
    public BrancheDAO(Connection con) {
        super(con, "table_branche");
    }

    @Override
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

    @Override
    public boolean delete(Branche user) throws SQLException {
        return false;
    }
}
