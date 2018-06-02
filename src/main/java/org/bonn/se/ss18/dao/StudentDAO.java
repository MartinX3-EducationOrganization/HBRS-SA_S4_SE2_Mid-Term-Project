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
    public Student readbyId(int id) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userid=" + id);
            return readResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Macht kein Sinn vorerst da man immer nur einen Studenten bekommen würde.
    @Override
    public Set<Student> getAllbyId(int id) throws SQLException {
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
            PreparedStatement ps = con.prepareStatement("UPDATE " + tableName + " SET linuxid=?,userid=?,anrede=?,vorname=?,nachname=?,gebdatum=? WHERE linuxid=" + student.getLinuxID());
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
            int i = stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + student.getiD());
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE linuxid=" + "\'" + linuxid + "\'");
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
        ps.setInt(2, student.getiD());
        ps.setString(3, student.getAnrede());
        ps.setString(4, student.getVorname());
        ps.setString(5, student.getNachname());
        ps.setDate(6, student.getGebDatum());
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }

    private Student readResults(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Student student = new Student();
            student.setLinuxID(rs.getString(1));
            student.setiD(rs.getInt(2));
            student.setAnrede(rs.getString(3));
            student.setVorname(rs.getString(4));
            student.setNachname(rs.getString(5));
            student.setGebDatum(rs.getDate(6));
            return student;
        }
        return null;
    }
}
