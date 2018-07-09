package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class UnternehmerDAO extends GenericDAO<Unternehmer> {
    public UnternehmerDAO(Connection con) {
        super(con, "table_unternehmen", "unternehmenid");
    }

    public Unternehmer getByID(int id, UserDAO userDAO) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(String.format("SELECT userid FROM %s WHERE %s=%s", super.tableName, "userid", id));
        if (resultSet.next()) {
            return readResults(
                    getRsByID(id + ""),
                    userDAO.getByID(id)
            );
        }
        return null;
    }

    @Override
    public ResultSet getRsByID(String id) throws SQLException {
        return con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE userid='%s'", tableName, id));
    }

    @Override
    public Set<Unternehmer> getAllByID(int id) {
        return null;
    }

    @Override
    public boolean create(Unternehmer unternehmer) throws SQLException {
        return createps(unternehmer, con.prepareStatement(String.format("INSERT INTO %s(userid,firmenname,website,ansprechpartner,brancheid) VALUES (?, ?, ?, ?, ?)", tableName)));
    }

    @Override
    public boolean update(Unternehmer unternehmer) throws SQLException {
        return createps(unternehmer, con.prepareStatement(String.format("UPDATE %s SET userid=?,firmenname=?,website=?,ansprechpartner=?,brancheid=? WHERE unternehmenid=%d", tableName, unternehmer.getUnternehmerid())));
    }

/*
Statements für Unternehmen
*/

    private boolean createps(Unternehmer unternehmer, PreparedStatement ps) throws SQLException {
        ps.setInt(1, unternehmer.getId());
        ps.setString(2, unternehmer.getFirmenname());
        ps.setString(3, unternehmer.getWebsite());
        ps.setString(4, unternehmer.getAnsprechpartner());
        ps.setInt(5, unternehmer.getBranchenid());

        return ps.executeUpdate() == 1;
    }

    private Unternehmer readResults(ResultSet rs, User user) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        Unternehmer unternehmer = new Unternehmer(user);

        unternehmer.setUnternehmerid(rs.getInt(1));
        unternehmer.setId(rs.getInt(2));
        unternehmer.setFirmenname(rs.getString(3));
        unternehmer.setWebsite(rs.getString(4));
        unternehmer.setAnsprechpartner(rs.getString(5));
        unternehmer.setBranchenid(rs.getInt(6));

        return unternehmer;
    }

    @Override
    public boolean delete(Unternehmer entity) throws SQLException {
        return con.createStatement().executeUpdate(String.format("DELETE FROM %s WHERE %s=%d", tableName, primaryKey, entity.getUnternehmerid())) == 1;
    }

    public boolean deleteByUserID(int id, UserDAO userDAO) throws SQLException {
        return delete(getByID(id, userDAO));
    }
}
