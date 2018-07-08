package org.bonn.se.ss18.dao;

import com.vaadin.ui.Notification;
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

    public Student getByID(int userID, UserDAO userDAO) {
        try {
            ResultSet resultSet = con.createStatement().executeQuery(String.format("SELECT userid FROM table_student WHERE userid=%d", userID));
            if (resultSet.next()) {
                return readResults(
                        getRsByID(userID + ""),
                        userDAO.getByID(resultSet.getInt(1))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
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
    public Set<Student> getAllByID(int id) {
        return null;
    }

    @Override
    public boolean create(Student student) {
        try {
            PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?)", super.tableName));
            return createps(student, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE %s SET linuxid=?,userid=?,anrede=?,vorname=?,nachname=?,gebdatum=? WHERE linuxid='%s'", super.tableName, student.getLinuxID()));
            return createps(student, ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /*
        Methoden die zusätzlich dazukommen
     */
    public Student getByUserAndPass(String linuxid, String password, UserDAO userDAO) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
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
        return i == 1;
    }

    private Student readResults(ResultSet rs, User user) throws SQLException {
        if (rs.next()) {
            Student student = new Student(user);
            student.setLinuxID(rs.getString(1));
            student.setId(rs.getInt(2));
            student.setAnrede(rs.getString(3));
            student.setVorname(rs.getString(4));
            student.setNachname(rs.getString(5));
            student.setGebDatum(rs.getDate(6));
            return student;
        }
        return null;
    }

    @Override
    public boolean delete(Student entity) {
        try {
            if (con.createStatement().executeUpdate(String.format("DELETE FROM %s WHERE %s='%s'", tableName, primaryKey, entity.getLinuxID())) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteByUserID(int id, UserDAO userDAO) {
        return delete(getByID(id, userDAO));
    }
}
