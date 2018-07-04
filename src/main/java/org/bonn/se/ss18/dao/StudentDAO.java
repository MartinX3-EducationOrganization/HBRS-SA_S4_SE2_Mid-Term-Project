package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Tables;

import java.sql.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author rjourd2s
 */
public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO(Connection con) {
        super(con, "table_student", "linuxid");
    }

    @Override
    public Student getByID(UUID userID) {
        try {
            ResultSet resultSet = con.createStatement().executeQuery("SELECT userid FROM table_student WHERE userid=" + userID);
            if (resultSet.next()) {
                return readResults(
                        getRsByID(userID + ""),
                        ((UserDAO) ConnectionFactory.getInstance().getDAO(Tables.table_user))
                                .getByID(UUID.fromString(resultSet.getString(1)))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getRsByID(String id) {
        try {
            return con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE userid='%s'", tableName, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Macht kein Sinn vorerst da man immer nur einen Studenten bekommen würde.
    @Override
    public Set<Student> getAllByID(UUID id) {
        return null;
    }

    @Override
    public boolean create(Student student) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.tableName + " VALUES (?, ?, ?, ?, ?, ?)");
            return createps(student, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Student student) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + super.tableName + " SET linuxid=?,userid=?,anrede=?,vorname=?,nachname=?,gebdatum=? WHERE linuxid='" + student.getLinuxID() + "'");
            return createps(student, ps);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /*
        Methoden die zusätzlich dazukommen
     */
    public Student getByUserAndPass(String linuxid, String password) {
        try {
            ResultSet resultSet = con.createStatement().executeQuery(String.format("SELECT table_user.userid FROM %s JOIN table_user ON %s.userid=table_user.userid WHERE %s.%s='%s' AND table_user.passwort='%s'", super.tableName, super.tableName, super.tableName, super.primaryKey, linuxid, password));
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                ResultSet studentResultSet = con.createStatement().executeQuery("SELECT userid FROM table_student WHERE userid=" + id);
                if (studentResultSet.next()) {
                    return readResults(
                            super.getRsByID(linuxid),
                            ((UserDAO) ConnectionFactory.getInstance().getDAO(Tables.table_user))
                                    .getByID(UUID.fromString(studentResultSet.getString(1)))
                    );
                }
            }
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
        ps.setString(2, student.getId().toString());
        ps.setString(3, student.getAnrede());
        ps.setString(4, student.getVorname());
        ps.setString(5, student.getNachname());
        ps.setDate(6, Date.valueOf(student.getGebDatum()));
        int i = ps.executeUpdate();
        // Eine Reihe(ROW)
        return i == 1;
    }

    private Student readResults(ResultSet rs, User user) throws SQLException {
        if (rs.next()) {
            Student student = new Student(user);
            student.setLinuxID(rs.getString(1));
            student.setId(UUID.fromString(rs.getString(2)));
            student.setAnrede(rs.getString(3));
            student.setVorname(rs.getString(4));
            student.setNachname(rs.getString(5));
            student.setGebDatum(rs.getDate(6));
            return student;
        }
        return null;
    }
}
