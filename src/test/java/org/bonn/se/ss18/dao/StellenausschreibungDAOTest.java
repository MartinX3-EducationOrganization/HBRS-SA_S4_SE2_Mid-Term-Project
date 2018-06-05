package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Stellenausschreibung;
import org.bonn.se.ss18.entity.Unternehmer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Set;

import static org.junit.Assert.*;


import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
public class StellenausschreibungDAOTest {
    private StellenausschreibungDAO dao;

    @Before
    public void setUp() throws Exception {

        dao = new StellenausschreibungDAO(ConnectionFactory.getInstance().getConnection());

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readbyId() {
        Stellenausschreibung stellenausschreibung = dao.readbyId(4);
        Assert.assertEquals("Text 2", stellenausschreibung.getText());
    }

    @Test
    public void getAllbyId() {
       // Set<Stellenausschreibung> result = dao.getAllbyId(1);
       //a Assert.assertEquals(4,result.size());

        Set<Stellenausschreibung> result1 = dao.getAllbyId(2);
        Assert.assertEquals(1,result1.size());








    }

    @Test
    public void create() {

        Stellenausschreibung stellenunternehnem = new Stellenausschreibung();
        stellenunternehnem.setText("text2");
        stellenunternehnem.setTitle("Titel 4");
        stellenunternehnem.setUnternehmensID(2);
        stellenunternehnem.setiD(4);
        stellenunternehnem.setDatum(new Date(1l));

        Assert.assertFalse(dao.create(stellenunternehnem));



    }

    @Test
    public void update() {


        Stellenausschreibung stellenausschreibung = dao.readbyId(4);
        Assert.assertFalse(stellenausschreibung==null);
        Assert.assertEquals("Text 2",stellenausschreibung.getText());

    }

    @Test
    public void delete() {
        Stellenausschreibung stellenunternehnem = new Stellenausschreibung();
        stellenunternehnem.setText("text2");
        stellenunternehnem.setTitle("Titel 4");
        stellenunternehnem.setUnternehmensID(2);
        stellenunternehnem.setiD(4);
        stellenunternehnem.setDatum(new Date(1l));

        Assert.assertFalse(dao.create(stellenunternehnem));





    }
}