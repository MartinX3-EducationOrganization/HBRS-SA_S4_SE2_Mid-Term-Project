package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.entity.Anzeige;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

public class AnzeigeDAOTest {
    private final Anzeige anzeige = new Anzeige();
    private AnzeigeDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = (AnzeigeDAO) ConnectionFactory.getDAO(Tables.table_anzeige);
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void testCreate() {
        //Tested ob es geklappt hat.
        anzeige.setUserid(100);
        anzeige.setDatum(new Date(2000, 1, 1));
        anzeige.setTitel("Titel");
        anzeige.setOrt("Ort");
        anzeige.setTyp("Gesuch");
        anzeige.setAnstellungsart("Praktikum");
        anzeige.setArbeitszeit("Teilzeit");
        anzeige.setBrancheid(1);
        anzeige.setBeginn(new Date(2000, 1, 1));
        anzeige.setAktiv(true);
        anzeige.setText("Text");
        Assert.assertFalse(dao.create(anzeige));
        try {
            dao.delete(anzeige);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadbyId() {
        Anzeige anzeige2 = dao.getByID(6);
        Assert.assertEquals(6, anzeige2.getId());
        Assert.assertEquals(1, anzeige2.getUserid());
       // Assert.assertEquals(new Date(2000, 1, 1), Date.valueOf(anzeige.getDatum()));
        Assert.assertEquals("Titel", anzeige2.getTitel());
        Assert.assertEquals("Ort", anzeige2.getOrt());
        Assert.assertEquals("Gesuch", anzeige2.getTyp());
        Assert.assertEquals("Praktikum", anzeige2.getAnstellungsart());
        Assert.assertEquals("Minijob", anzeige2.getArbeitszeit());
        Assert.assertEquals(1, anzeige2.getBrancheid());
       // Assert.assertEquals(new Date(2000, 1, 1), Date.valueOf(anzeige.getDatum()));
       // Assert.assertTrue(anzeige2.getBrancheid());
        Assert.assertEquals("Text----- bllal", anzeige2.getText());

    }

    @Test
    public void testGetAllbyId() {
        Set<Anzeige> result = dao.getAllByID(100);
        Assert.assertEquals(0,result.size());

        // Was macht GetAllById und wofür wird es gebraucht????
        Set<Anzeige> result1 = dao.getAllByID(2);
     Assert.assertEquals(1, result1.size());




    }


    @Test
    public void testUpadate() {

        Anzeige anzeige = dao.getByID(6);

        anzeige.setTyp("Gesuch");
        Assert.assertTrue(dao.update(anzeige));

//  Neue Werte die du updaten möchtest.
//  Teste auf True dat update geklappt, danach dao.getByID(id) ob es geklappt hat.

    }

    @Test
    public void testDelete() {
        anzeige.setId(100);
        anzeige.setUserid(100);
        anzeige.setDatum(new Date(2000, 1, 1));
        anzeige.setTitel("Titel");
        anzeige.setOrt("Ort");
        anzeige.setTyp("Gesuch");
        anzeige.setAnstellungsart("Praktikum");
        anzeige.setArbeitszeit("Teilzeit");
        anzeige.setBrancheid(1);
        anzeige.setBeginn(new Date(2000, 1, 1));
        anzeige.setAktiv(true);
        anzeige.setText("Text");
        dao.create(anzeige);
        try {
            Assert.assertFalse(dao.delete(anzeige));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // Gibt es noch eine Anzeige mit der Id 100 ? Wenn nicht Null.
        // Assert.assertNull(dao.getByID(100));


    }
}