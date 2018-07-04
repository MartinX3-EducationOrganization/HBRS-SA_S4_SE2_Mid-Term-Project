package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Bewerbung;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

/**
 * @author rjourd2s
 */
public class BewerbungDAO extends GenericDAO<Bewerbung> {
    public BewerbungDAO(Connection con) {
        super(con, "table_bewerbung", "bewerbungid");
    }

    @Override
    public Bewerbung getByID(UUID id) throws SQLException {
        return null;
    }

    @Override
    public Set<Bewerbung> getAllByID(UUID id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Bewerbung user) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Bewerbung user) throws SQLException {
        return false;
    }
}
