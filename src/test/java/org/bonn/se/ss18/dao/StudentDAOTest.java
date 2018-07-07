package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class StudentDAOTest {
    private StudentDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new StudentDAO(ConnectionFactory.getInstance().getConnection());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readbyId() throws SQLException {
        Student student = dao.getByID(45);

        Assert.assertEquals("Heike", student.getVorname());
    }

   /* @Test
    public void getAllByID() {
        Student student = new Student();
        student.setLinuxID(null);
        Assert.assertEquals(null,null);*/

//}

    @Test
    public void create() {
        Student student = new Student();
        student.setLinuxID("ux2s");
        student.setPasswort("123");
        student.setId(45);
        student.setAnrede("Frau");
        student.setVorname("Heike");
        student.setNachname("Baumann");
        student.setGebDatum(new Date(1l));
        Assert.assertFalse(dao.create(student));
    }

    @Test
    public void update() {
        Student student = dao.getByUserAndPass("ux2s", "123");
        student.setVorname("Heike");
        Assert.assertTrue(dao.update(student));
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
        student.setGebDatum(new Date(1l));

        Assert.assertFalse(dao.delete(student));
    }

    @Test
    public void read() {
        Student student = dao.getByUserAndPass("salda2s", "123");

        Assert.assertTrue(student == null);
        Assert.assertFalse(student != null);
       //    Assert.assertEquals("Bauman", student.getNachname());


    }
}