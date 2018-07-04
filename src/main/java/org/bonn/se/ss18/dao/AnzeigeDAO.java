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
        super(con, "table_anzeige", "anzeigeid");
    }

    @Override
    public Anzeige getByID(int id) {
        try {
            HashSet<Anzeige> set = readResults(super.getRsByID(id + ""));
            return set.isEmpty() ? null : set.iterator().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Anzeige> getAllByID(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userid=" + id);
            Set stellen = new HashSet();
            while (rs.next()) {
                stellen = readResults(rs);
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName +
                    "(userid,datum,titel,ort,typ,anstellungsart,arbeitszeit,brancheid,beginn,aktiv,text, anzeigeid)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            return createps(stelle, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Anzeige stelle) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " "
                    + "SET userid = ?, datum = ? , titel = ?, ort = ?, "
                    + "typ = ?, anstellungsart = ?, arbeitszeit = ?, "
                    + "brancheid = ?, beginn = ?, aktiv = ?, text = ?, "
                    +   "anzeigeid = ?"
                    + "WHERE anzeigeid = " + stelle.getId());
            return createps(stelle, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean createps(Anzeige anzeige, PreparedStatement ps) throws SQLException {
        //ps.setInt(1, anzeige.getId());
        ps.setInt(1, anzeige.getUserid());
        ps.setDate(2, Date.valueOf(anzeige.getDatum()));
        ps.setString(3, anzeige.getTitel());
        ps.setString(4, anzeige.getOrt());
        ps.setString(5, anzeige.getTyp());
        ps.setString(6, anzeige.getAnstellungsart());
        ps.setString(7, anzeige.getArbeitszeit());
        ps.setInt(8, anzeige.getBrancheid());
        ps.setDate(9, Date.valueOf(anzeige.getBeginn()));
        ps.setBoolean(10, anzeige.getAktiv());
        ps.setString(11, anzeige.getText());
        ps.setInt(12, anzeige.getId());

        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }


    private HashSet<Anzeige> readResults(ResultSet rs) throws SQLException {
        HashSet<Anzeige> result = new HashSet<>();
        if (rs.next()) {
            Anzeige anzeige = new Anzeige();

            anzeige.setId(rs.getInt("anzeigeid"));
            anzeige.setUserid(rs.getInt("userid"));
            anzeige.setDatum(rs.getDate("datum"));
            anzeige.setTitel(rs.getString("titel"));
            anzeige.setOrt(rs.getString("ort"));
            anzeige.setTyp(rs.getString("typ"));
            anzeige.setAnstellungsart(rs.getString("anstellungsart"));
            anzeige.setArbeitszeit(rs.getString("arbeitszeit"));
            anzeige.setBrancheid(rs.getInt("brancheid"));
            anzeige.setBeginn(rs.getDate("beginn"));
            anzeige.setAktiv(rs.getBoolean("aktiv"));
            anzeige.setText(rs.getString("text"));

            result.add(anzeige);
        }
        return result;
    }
}
