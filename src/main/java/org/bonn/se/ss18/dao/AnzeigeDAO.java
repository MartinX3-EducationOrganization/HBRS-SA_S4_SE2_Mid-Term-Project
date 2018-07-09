package org.bonn.se.ss18.dao;

/*
  @author rjourd2s
 */

import org.bonn.se.ss18.entity.Anzeige;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AnzeigeDAO extends GenericDAO<Anzeige> {
    public AnzeigeDAO(Connection con) {
        super(con, "table_anzeige", "anzeigeid");
    }

    public Anzeige getByID(int id) throws SQLException {
        HashSet<Anzeige> set = readResults(super.getRsByID(String.format("%d", id)));
        return set.isEmpty() ? null : set.iterator().next();
    }

    @Override
    public Set<Anzeige> getAllByID(int id) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE userid=%d", tableName, id));
        return readResults(rs);
    }

    @Override
    public boolean create(Anzeige stelle) throws SQLException {
        return createps(
                stelle,
                con.prepareStatement(String.format("INSERT INTO %s(userid,datum,titel,ort,typ,anstellungsart,arbeitszeit,brancheid,beginn,aktiv,text) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", tableName))
        );
    }

    @Override
    public boolean update(Anzeige stelle) throws SQLException {
        return createps(
                stelle,
                con.prepareStatement(String.format("UPDATE %s SET userid = ?, datum = ? , titel = ?, ort = ?, typ = ?, anstellungsart = ?, arbeitszeit = ?, brancheid = ?, beginn = ?, aktiv = ?, text = ? WHERE anzeigeid = %d", tableName, stelle.getId()))
        );
    }

    private boolean createps(Anzeige anzeige, PreparedStatement ps) throws SQLException {
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

        return ps.executeUpdate() == 1;
    }


    private HashSet<Anzeige> readResults(ResultSet rs) throws SQLException {
        HashSet<Anzeige> result = new HashSet<>();

        while (rs.next()) {
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
