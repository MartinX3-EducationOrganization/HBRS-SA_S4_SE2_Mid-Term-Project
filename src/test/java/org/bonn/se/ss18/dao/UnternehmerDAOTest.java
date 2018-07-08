package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class UnternehmerDAOTest {
    private UnternehmerDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen);
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void readbyId() {
        Unternehmer unternehmer = dao.getByID(53);
        Assert.assertEquals("ZFirma", unternehmer.getFirmenname());

    }

    @Test
    public void getAllbyId() {
        Set<Unternehmer> result = dao.getAllByID(0);
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
        Unternehmer unternehmer = dao.getByID(0);
        Assert.assertFalse(unternehmer == null);
       unternehmer.setFirmenname("Example Consulting new");
       Assert.assertTrue(dao.update(unternehmer));
        Assert.assertEquals("Example Consulting new", unternehmer.getFirmenname());
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