package org.bonn.se.ss18.dao;

/**
 * @author rjourd2s
 */

import org.bonn.se.ss18.entity.Anzeige;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AnzeigeDAO extends GenericDAO<Anzeige> {

    public AnzeigeDAO(Connection con) {
        super(con, "table_anzeige");
    }

    @Override
    public Anzeige readbyId(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE anzeigeid=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Set<Anzeige> getAllbyId(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userid=" + id);
            Set stellen = new HashSet();
            while (rs.next()) {
                Anzeige st = readResults(rs);
                stellen.add(st);
            }
            return stellen;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Anzeige stelle) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
            return createps(stelle, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Anzeige stelle) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET anzeigeid=?,userid=?,datum=?,titel=?,ort=?,typ=?,anstellungsart=?,arbeitszeit=?,bracheid=?,beginn=?,aktiv=?,text=? WHERE id=" + stelle.getAnzeigeid());
            return createps(stelle, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Anzeige stelle) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + stelle.getAnzeigeid());
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean createps(Anzeige anzeige, PreparedStatement ps) throws SQLException {
        ps.setInt(1, anzeige.getAnzeigeid());
        ps.setInt(2, anzeige.getUserid());
        ps.setDate(3, Date.valueOf(anzeige.getDatum()));
        ps.setString(4, anzeige.getTitle());
        ps.setString(5, anzeige.getOrt());
        ps.setString(6, anzeige.getTyp());
        ps.setString(7, anzeige.getAnstellungsart());
        ps.setString(8, anzeige.getArbeitszeit());
        ps.setInt(9, anzeige.getBranchenid());
        ps.setDate(10, Date.valueOf(anzeige.getBeginn()));
        ps.setBoolean(11, anzeige.isAktiv());
        ps.setString(12, anzeige.getText());

        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }


    private Anzeige readResults(ResultSet rs) throws SQLException {
        Anzeige anzeige = new Anzeige();
        anzeige.setiD(rs.getInt("anzeigeid"));
        anzeige.setUserid(rs.getInt("userid"));
        anzeige.setDatum(rs.getDate("datum"));
        anzeige.setTitle(rs.getString("titel"));
        anzeige.setOrt(rs.getString("ort"));
        anzeige.setTyp(rs.getString("typ"));
        anzeige.setAnstellungsart(rs.getString("anstellungsart"));
        anzeige.setArbeitszeit(rs.getString("arbeitszeit"));
        anzeige.setBranchenid(rs.getInt("brancheid"));
        anzeige.setBeginn(rs.getDate("beginn"));
        anzeige.setAktiv(rs.getBoolean("aktiv"));
        anzeige.setText(rs.getString("text"));

        return anzeige;
    }

}