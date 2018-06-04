package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Stellenausschreibung;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

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
    }

    @Test
    public void getAllbyId() {
    }

    @Test
    public void create() {
       // Stellenunternehmen  stelle = new Stellenunternehmen();
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
    }

    @Test
    public void delete() {
    }
}