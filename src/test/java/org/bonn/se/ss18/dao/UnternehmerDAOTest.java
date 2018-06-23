package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Unternehmer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class UnternehmerDAOTest {
    private UnternehmerDAO dao;

    @Before
    public void setUp() throws Exception {

        dao = new UnternehmerDAO(ConnectionFactory.getInstance().getConnection());
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    @Test
    public void readbyId() {
        Unternehmer unternehmer = dao.readByID(2);
        Assert.assertEquals("Musterfirma", unternehmer.getFirmenname());

    }

    @Test
    public void getAllbyId() {
        Set<Unternehmer> result = dao.getAllbyId(0);
        Assert.assertEquals(null, result);
    }

    @Test
    public void create() {
        Unternehmer unternehmer = new Unternehmer();
        unternehmer.setBranchenid(42);
        unternehmer.setUnternehmerid(2);
        unternehmer.setFirmenname("AG Dümer 2");
        unternehmer.setWebsite("www.ag2.de");

        Assert.assertFalse(dao.create(unternehmer));


    }

    @Test
    public void update() {
        Unternehmer unternehmer = dao.readByID(2);
        Assert.assertFalse(unternehmer == null);
        Assert.assertEquals("Musterfirma", unternehmer.getFirmenname());
    }

    @Test
    public void delete() {

        Unternehmer unternehmer = new Unternehmer();
        unternehmer.setBranchenid(42);
        unternehmer.setUnternehmerid(2);
        unternehmer.setFirmenname("AG Dümer 2");
        unternehmer.setWebsite("www.ag2.de");
        Assert.assertFalse(dao.delete(unternehmer));

    }
}