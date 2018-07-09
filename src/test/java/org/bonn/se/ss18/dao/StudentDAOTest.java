package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class StudentDAOTest {
    private UserDAO userDAO;
    private StudentDAO studentDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user);
        studentDAO = (StudentDAO) ConnectionFactory.getDAO(Tables.table_student);
    }

    @After
    public void tearDown() throws Exception {
        studentDAO.close();
        userDAO.close();
    }

    @Test
    public void readbyId() throws SQLException {
        Student student = studentDAO.getByID(45, userDAO);

        Assert.assertEquals("Heike", student.getVorname());
    }

    @Test
    public void create() {
        Student student = new Student();
        student.setLinuxID("ux2s");
        student.setPasswort("123");
        student.setId(45);
        student.setAnrede("Frau");
        student.setVorname("Heike");
        student.setNachname("Baumann");
        student.setGebDatum(new Date(1L));
        try {
            Assert.assertFalse(studentDAO.create(student));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Student student = null;
        try {
            student = studentDAO.getByUserAndPass("ux2s", "123", userDAO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        student.setVorname("Heike");
        try {
            Assert.assertTrue(studentDAO.update(student));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setLinuxID("hmn2s");
        student.setPasswort("1111");
        student.setId(2);
        student.setAnrede("Herr");
        student.setVorname("beni");
        student.setNachname("Müller");
        student.setGebDatum(new Date(1L));

        try {
            Assert.assertFalse(studentDAO.delete(student));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void read() {
        Student student = null;
        try {
            student = studentDAO.getByUserAndPass("salda2s", "123", userDAO);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertNull(student);
        Assert.assertNull(student);
    }
}