package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
    private UserDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = (UserDAO) ConnectionFactory.getDAO(Tables.table_user);
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void testcreate() {
        //UUID id ;
        User user = new User();
        user.setId(5);


        user.setPasswort("123");
        user.setStrasse("Weg");
        user.setHausnr("23");
        user.setPlz("53757");
        user.setOrt("Sankt Augustin");
        user.setEmail("email@unternehmen.de");
        user.setTelNr("180189");
        user.setFaxNr("0781");
        user.setFoto(null);
        user.setKurzVorstellung(null);
        Assert.assertFalse(dao.create(user));


        User newUser = new User();
        newUser.setId(8);
        newUser.setPasswort("üü8ää");
        newUser.setStrasse("Wieg");
        newUser.setHausnr("108");
        newUser.setPlz("5579");
        newUser.setOrt("Sankt Augustin ort");
        newUser.setEmail("khmail@email.de");
        newUser.setTelNr("0710980");
        newUser.setFaxNr("257189");
        newUser.setFoto(null);
        newUser.setKurzVorstellung("blabla");

        Assert.assertFalse(dao.create(newUser));
        // dao.delete(newUser);


    }

    @Test
    public void testreadbyId() {

      User user = dao.getByID(7);
        Assert.assertEquals(null, user);
        User user1 = dao.getByID(2);
        Assert.assertEquals("email@unternehmen.de", user1.getEmail());
        User user2 = dao.getByID(1);
        Assert.assertEquals("Sankt Augustin", user2.getOrt());

        Assert.assertTrue(user1 != null);
        Assert.assertFalse(user1 == null);
    }

    @Test
    public void testupdate() {

         User user = dao.getByID(1);
        user.setKurzVorstellung("neuer");
        Assert.assertTrue(dao.update(user));

        User user1 = dao.getByID(1);

        Assert.assertFalse(user1 == null);
        Assert.assertEquals("53757", user1.getPlz());


        //  User user2=dao.getByID(1);
        //Assert.assertEquals("neuerwert",user2.getKurzVorstellung());


    }

    @Test
    public void testdelete() {

        User newUser = new User();
        newUser.setId(8);
        newUser.setPasswort("üü8ää");
        newUser.setStrasse("Wieg");
        newUser.setHausnr("108");
        newUser.setPlz("5579");
        newUser.setOrt("Sankt Augustin ort");
        newUser.setEmail("khmail@email.de");
        newUser.setTelNr("0710980");
        newUser.setFaxNr("257189");
        newUser.setFoto(null);
        newUser.setKurzVorstellung("blabla");

        Assert.assertFalse(dao.delete(newUser));


    }
}