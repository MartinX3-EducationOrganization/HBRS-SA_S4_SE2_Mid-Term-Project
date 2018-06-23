package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Qualifikation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class QualifikationDAO extends GenericDAO<Qualifikation> {
    public QualifikationDAO(Connection con) {
        super(con, "table_qualifikation");
    }

    @Override
    public Qualifikation getByID(int id) throws SQLException {
        return null;
    }

    @Override
    public Set<Qualifikation> getAllByID(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Qualifikation user) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Qualifikation user) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Qualifikation user) throws SQLException {
        return false;
    }
}
