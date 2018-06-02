package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Stellenausschreibung;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class StellenausschreibungDAO extends GenericDAO<Stellenausschreibung> {
    public StellenausschreibungDAO(Connection con) {
        super(con, "table_stellenunternehmen");
    }

    //  Eine Stellenausschreibung nach ID.
    @Override
    public Stellenausschreibung readbyId(int id) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userid=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Alle Stellen die von einem Unternehmen angeboten werden (Sammlung -> StellenausschreibungUnternehmen)
    @Override
    public Set<Stellenausschreibung> getAllbyId(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE unternehmensid=" + id);
            Set stellen = new HashSet();
            while (rs.next()) {
                Stellenausschreibung st = readResults(rs);
                stellen.add(st);
            }
            return stellen;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Stellenausschreibung stelle) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?)");
            return createps(stelle, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean update(Stellenausschreibung stelle) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET id=?,unternehmensid=?,title=?,text=?,datum=? WHERE id=" + stelle.getiD());
            return createps(stelle, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Stellenausschreibung stelle) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + stelle.getiD());
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private boolean createps(Stellenausschreibung stelle, PreparedStatement ps) throws SQLException {
        ps.setInt(1, stelle.getiD());
        ps.setInt(2, stelle.getUnternehmensID());
        ps.setString(3, stelle.getTitle());
        ps.setString(4, stelle.getText());
        ps.setDate(5, stelle.getDatum());
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }


    private Stellenausschreibung readResults(ResultSet rs) throws SQLException {
        Stellenausschreibung stelle = new Stellenausschreibung();
        stelle.setiD(rs.getInt("id"));
        stelle.setUnternehmensID(rs.getInt("unternehmensid"));
        stelle.setTitle(rs.getString("title"));
        stelle.setText(rs.getString("text"));
        stelle.setDatum(rs.getDate("datum"));
        return stelle;
    }
}