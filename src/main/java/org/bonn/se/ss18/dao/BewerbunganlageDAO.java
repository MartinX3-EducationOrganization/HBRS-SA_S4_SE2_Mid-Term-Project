package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Bewerbunganlage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class BewerbunganlageDAO extends GenericDAO<Bewerbunganlage> {
    public BewerbunganlageDAO(Connection con) {
        super(con, "table_bewerbunganlage");
    }

    @Override
    public Bewerbunganlage getByID(int id) throws SQLException {
        return null;
    }

    @Override
    public Set<Bewerbunganlage> getAllByID(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Bewerbunganlage user) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Bewerbunganlage user) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Bewerbunganlage user) throws SQLException {
        return false;
    }
}
