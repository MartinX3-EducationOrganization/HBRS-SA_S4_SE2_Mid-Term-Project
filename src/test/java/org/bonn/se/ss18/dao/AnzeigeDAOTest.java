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
        try {
            dao = ConnectionFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            adao = (AnzeigeDAO) dao.getDAO(Tables.table_anzeige);
        }

        anzeige.setId(100);
        anzeige.setUserid(100);
        anzeige.setDatum(new Date(2000, 1, 1));
        anzeige.setTitle("Titel");
        anzeige.setOrt("Ort");
        anzeige.setTyp("Typ");
        anzeige.setAnstellungsart("Anstellung");
        anzeige.setArbeitszeit("8-18");
        anzeige.setBranchenid(42);
        anzeige.setBeginn(new Date(2000, 1, 1));
        anzeige.setAktiv(true);
        anzeige.setText("Text");
        adao.create(anzeige);
    }

    @After
    public void tearDown() throws Exception {
        dao.getConnection().close();
    }

    @Test
    public void create() {


        //Tested ob es geklappt hat.
        Assert.assertTrue(adao.create(anzeige));
    }

    @Test
    public void readbyId() {
        Anzeige anzeige2 = adao.readbyId(100);
        Assert.assertEquals(100, anzeige2.getId());
        Assert.assertEquals(100, anzeige2.getUserid());
        Assert.assertEquals(new Date(2000, 1, 1), Date.valueOf(anzeige.getDatum()));
        Assert.assertEquals("Titel", anzeige2.getTitle());
        Assert.assertEquals("Ort", anzeige2.getOrt());
        Assert.assertEquals("Typ", anzeige2.getTyp());
        Assert.assertEquals("Anstellung", anzeige2.getAnstellungsart());
        Assert.assertEquals("8-18", anzeige2.getArbeitszeit());
        Assert.assertEquals(42, anzeige2.getBranchenid());
        Assert.assertEquals(new Date(2000, 1, 1), Date.valueOf(anzeige.getDatum()));
        Assert.assertTrue(anzeige2.isAktiv());
        Assert.assertEquals("Text", anzeige2.getText());
    }

    @Test
    public void getAllbyId() {
        // Set<Anzeige> result = dao.getAllbyId(1);
        //a Assert.assertEquals(4,result.size());

        Set<Anzeige> result1 = adao.getAllbyId(100);
        Assert.assertEquals(1, result1.size());


    }


    @Test
    public void update() {

//  Neue Werte die du updaten möchtest.
//  Teste auf True dat update geklappt, danach adao.readbyId(id) ob es geklappt hat.

    }

    @Test
    public void delete() {
        // Lösche
        adao.delete(anzeige);
        // Hat löschen geklappt
        Assert.assertTrue(adao.delete(anzeige));
        // Gibt es noch eine Anzeige mit der Id 100 ? Wenn nicht Null.
        Assert.assertNull(adao.readbyId(100));


    }
}