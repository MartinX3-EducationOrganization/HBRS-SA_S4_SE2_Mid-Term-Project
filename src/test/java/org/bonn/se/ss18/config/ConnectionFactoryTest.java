package org.bonn.se.ss18.config;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.StudentDAO;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class ConnectionFactoryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetDAO() throws SQLException {
        StudentDAO dao = (StudentDAO) ConnectionFactory.getDAO(Tables.table_student);
        Assert.assertNotNull(dao);
    }
}