package org.bonn.se.ss18.controller;

import org.bonn.se.ss18.exception.NoSuchUserOrPasswort;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoginControllerTest {

   private LoginController login;

    @Before
    public void setUp() throws Exception {
        login = new LoginController();





    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLogin() throws SQLException, NoSuchUserOrPasswort {
        Assert.assertFalse(login.login("salda2s","123"));
      // Assert.assertFalse(login.login("salklda2s","183"));
       //Assert.assertFalse(login.login("emmail@hotmail.de","345"));




    }

    @Test
    public void logout() {
    }
}