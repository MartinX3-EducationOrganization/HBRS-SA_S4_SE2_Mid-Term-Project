package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.User;

import java.sql.*;
import java.util.Set;

/**
 * @author rjourd2s
 */
public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO(Connection con) {
        super(con, "table_student", "linuxid");
    }

    public Student getByID(int userID, UserDAO userDAO) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(String.format("SELECT userid FROM table_student WHERE userid=%d", userID));
            if (resultSet.next()) {
                return readResults(
                        getRsByID(userID + ""),
                        userDAO.getByID(resultSet.getInt(1))
                );
            }
            return null;
    }

    @Override
    public ResultSet getRsByID(String id) throws SQLException {
        return con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE userid='%s'", tableName, id));
    }

    //  Macht kein Sinn vorerst da man immer nur einen Studenten bekommen würde.
    @Override
    public Set<Student> getAllByID(int id) {
        return null;
    }

    @Override
    public boolean create(Student student) throws SQLException {
        return createps(
                student,
                con.prepareStatement(String.format("INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?)", super.tableName))
        );
    }

    @Override
    public boolean update(Student student) throws SQLException {
        return createps(
                student,
                con.prepareStatement(String.format("UPDATE %s SET linuxid=?,userid=?,anrede=?,vorname=?,nachname=?,gebdatum=? WHERE linuxid='%s'", super.tableName, student.getLinuxID()))
        );
    }

    /*
    Methoden die zusätzlich dazukommen
    */
    public Student getByUserAndPass(String linuxid, String password, UserDAO userDAO) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(String.format("SELECT table_user.userid FROM %s JOIN table_user ON %s.userid=table_user.userid WHERE %s.%s='%s' AND table_user.passwort='%s'", super.tableName, super.tableName, super.tableName, super.primaryKey, linuxid, password));
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            ResultSet studentResultSet = con.createStatement().executeQuery(String.format("SELECT userid FROM table_student WHERE userid=%d", id));
            if (studentResultSet.next()) {
                return readResults(
                        super.getRsByID(linuxid),
                        userDAO.getByID(studentResultSet.getInt(1))
                );
            }
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

        return ps.executeUpdate() == 1;
    }

    private Student readResults(ResultSet rs, User user) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        Student student = new Student(user);

        student.setLinuxID(rs.getString(1));
        student.setId(rs.getInt(2));
        student.setAnrede(rs.getString(3));
        student.setVorname(rs.getString(4));
        student.setNachname(rs.getString(5));
        student.setGebDatum(rs.getDate(6));

        return student;
    }

    @Override
    public boolean delete(Student entity) throws SQLException {
        return con.createStatement().executeUpdate(String.format("DELETE FROM %s WHERE %s='%s'", tableName, primaryKey, entity.getLinuxID())) == 1;
    }

    public boolean deleteByUserID(int id, UserDAO userDAO) throws SQLException {
        return delete(getByID(id, userDAO));
    }
}
