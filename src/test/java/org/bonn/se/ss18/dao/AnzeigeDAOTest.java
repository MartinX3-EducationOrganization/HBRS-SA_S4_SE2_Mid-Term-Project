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
    private AnzeigeDAO adao;
    private ConnectionFactory dao;
    private final Anzeige anzeige = new Anzeige();

    @Before
    public void setUp() throws Exception {
        adao = new AnzeigeDAO(ConnectionFactory.getInstance().getConnection());






    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreate() {


        //Tested ob es geklappt hat.
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
        Assert.assertTrue(adao.create(anzeige));
        adao.delete(anzeige);
    }

    @Test
    public void testReadbyId() {
        Anzeige anzeige2 = adao.readbyId(6);
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
        // Set<Anzeige> result = dao.getAllbyId(1);
        //a Assert.assertEquals(4,result.size());

        // Was macht GetAllById und wofür wird es gebraucht????
        //Set<Anzeige> result1 = adao.getAllbyId(100);
       // Assert.assertEquals(18, result1.size());




    }


    @Test
    public void testUpadate() {

        Anzeige anzeige = adao.readbyId(6);

        anzeige.setTyp("Gesuch");
        Assert.assertTrue(adao.update(anzeige));

//  Neue Werte die du updaten möchtest.
//  Teste auf True dat update geklappt, danach adao.readbyId(id) ob es geklappt hat.

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
        adao.create(anzeige);
        Assert.assertTrue(adao.delete(anzeige));
        // Gibt es noch eine Anzeige mit der Id 100 ? Wenn nicht Null.
       // Assert.assertNull(adao.readbyId(100));


    }
}