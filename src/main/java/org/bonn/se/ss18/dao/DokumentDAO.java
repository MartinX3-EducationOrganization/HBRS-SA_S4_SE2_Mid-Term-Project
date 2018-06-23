package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Dokument;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class DokumentDAO extends GenericDAO<Dokument> {
    public DokumentDAO(Connection con) {
        super(con, "table_dokument");
    }

    @Override
    public Dokument readByID(int id) throws SQLException {
        return null;
    }

    @Override
    public Set<Dokument> getAllbyId(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Dokument user) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Dokument user) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Dokument user) throws SQLException {
        return false;
    }
}
