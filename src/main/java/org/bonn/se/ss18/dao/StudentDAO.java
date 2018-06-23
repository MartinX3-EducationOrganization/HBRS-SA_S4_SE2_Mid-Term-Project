package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Student;

import java.sql.*;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO(Connection con) {
        super(con, "table_student");
    }


    @Override
    public Student getByID(int id) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM table_user INNER JOIN table_student ss ON table_user.userid = ss.userid " +
                    "WHERE table_user.userid=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Macht kein Sinn vorerst da man immer nur einen Studenten bekommen würde.
    @Override
    public Set<Student> getAllByID(int id) {
        return null;
    }

    @Override
    public boolean create(Student student) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?)");
            return createps(student, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Student student) {

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET linuxid=?,userid=?,anrede=?,vorname=?,nachname=?,gebdatum=? WHERE linuxid='" + student.getLinuxID() +"'");
            return createps(student, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean delete(Student student) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + student.getId());
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /*
        Methoden die zusätzlich dazukommen
     */
    public Student read(String linuxid) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM table_user INNER JOIN table_student ss ON table_user.userid = ss.userid " +
                    "WHERE ss.linuxid=" + "\'" + linuxid + "\'");
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        Statements für Student
     */
    private boolean createps(Student student, PreparedStatement ps) throws SQLException {
        ps.setString(1, student.getLinuxID());
        ps.setInt(2, student.getId());
        ps.setString(3, student.getAnrede());
        ps.setString(4, student.getVorname());
        ps.setString(5, student.getNachname());
        ps.setDate(6, Date.valueOf(student.getGebDatum()));
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }

    private Student readResults(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt(1));
            student.setPasswort(rs.getString(2));
            student.setStrasse(rs.getString(3));
            student.setHausnr(rs.getString(4));
            student.setPlz(rs.getString(5));
            student.setOrt(rs.getString(6));
            student.setEmail(rs.getString(7));
            student.setTelNr(rs.getString(8));
            student.setFaxNr(rs.getString(9));
            student.setFoto(rs.getBytes(10));
            student.setKurzVorstellung(rs.getString(11));
            student.setLinuxID(rs.getString(12));
            student.setId(rs.getInt(13));
            student.setAnrede(rs.getString(14));
            student.setVorname(rs.getString(15));
            student.setNachname(rs.getString(16));
            student.setGebDatum(rs.getDate(17));
            return student;
        }
        return null;
    }
}
