package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Set;

public class UnternehmerDAOTest {
    private UserDAO userDAO;
    private UnternehmerDAO unternehmerDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user);
        unternehmerDAO = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen);
    }

    @After
    public void tearDown() throws Exception {
        unternehmerDAO.close();
        userDAO.close();
    }

    @Test
    public void readbyId() {
        Unternehmer unternehmer = unternehmerDAO.getByID(53, userDAO);
        Assert.assertEquals("ZFirma", unternehmer.getFirmenname());

    }

    @Test
    public void getAllbyId() {
        Set<Unternehmer> result = unternehmerDAO.getAllByID(0);
        Assert.assertEquals(null, result);
    }

    @Test
    public void create() {
        Unternehmer unternehmer = new Unternehmer();
        unternehmer.setBranchenid(42);
        unternehmer.setUnternehmerid(2);
        unternehmer.setFirmenname("AG Dümer 2");
        unternehmer.setWebsite("www.ag2.de");

        Assert.assertFalse(unternehmerDAO.create(unternehmer));


    }

    @Test
    public void update() {
        Unternehmer unternehmer = unternehmerDAO.getByID(0, userDAO);
        Assert.assertFalse(unternehmer == null);
       unternehmer.setFirmenname("Example Consulting new");
        Assert.assertTrue(unternehmerDAO.update(unternehmer));
        Assert.assertEquals("Example Consulting new", unternehmer.getFirmenname());
    }

    @Test
    public void delete() {

        Unternehmer unternehmer = new Unternehmer();
        unternehmer.setBranchenid(42);
        unternehmer.setUnternehmerid(2);
        unternehmer.setFirmenname("AG Dümer 2");
        unternehmer.setWebsite("www.ag2.de");
        try {
            Assert.assertFalse(unternehmerDAO.delete(unternehmer));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}