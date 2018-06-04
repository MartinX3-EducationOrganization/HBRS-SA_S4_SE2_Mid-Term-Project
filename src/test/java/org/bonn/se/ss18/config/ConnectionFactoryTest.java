package org.bonn.se.ss18.config;


import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.GenericDAO;
import org.bonn.se.ss18.dao.StudentDAO;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void  testGetConnection() throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        //Connection object ist nicht null
        boolean value = connection != null;

        Assert.assertTrue(value);
        //Connection nicht geschlossen

        Assert.assertFalse(connection.isClosed());

       //ich schliesse die Connection
        connection.close();
        //Connection ist geschlossen
        Assert.assertTrue(connection.isClosed());

    }

    public void testGetDAO() throws SQLException {
        GenericDAO dao = ConnectionFactory.getInstance().getDAO(Tables.table_student);
        Assert.assertTrue(dao instanceof StudentDAO);
    }
}