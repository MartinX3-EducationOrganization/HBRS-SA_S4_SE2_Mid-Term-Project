package org.bonn.se.ss18.view;

import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationUnternehmenTest {
    private final Unternehmer unternehmer = new Unternehmer();
    private WebDriver driver;
    private UnternehmerDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen);

        unternehmer.setFirmenname("Musterfirma");
        unternehmer.setEmail("mail@unternehmen.de");
        unternehmer.setTelNr("023456");
        unternehmer.setPasswort("123");
        unternehmer.setPlz("31567");
        unternehmer.setOrt("Bonn");

        //setzen den Chrome driver in system umgebung
        //systemproperty key
        //pfad zu Chrome driver
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        //erzeugen den Chrome driver
        driver = new ChromeDriver();
        //nach ausführung einer Action warte von 15s
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //URL aufrufen
        //Webseite URL
        driver.get("http://localhost:8080/");
    }

    @After
    public void tearDown() {
        dao.close();
        driver.close();
    }

    @Test
    public void testRegistrationUnternehmen() {
        WebElement element = driver.findElement(By.id("registrieren_in"));
        element.click();

        element = driver.findElement(By.id("unternehmer"));
        element.sendKeys(unternehmer.getFirmenname());

        element = driver.findElement(By.id("email"));
        element.sendKeys(unternehmer.getEmail());

        element = driver.findElement(By.id("passwort"));
        element.sendKeys(unternehmer.getPasswort());

        element = driver.findElement(By.id("telefon"));
        element.sendKeys(unternehmer.getTelNr());

        element = driver.findElement(By.id("ort"));
        element.sendKeys(unternehmer.getOrt());

        element = driver.findElement(By.id("plz"));
        element.sendKeys(unternehmer.getPlz());

        element = driver.findElement(By.id("agb"));
        element.click();

        element = driver.findElement(By.id("registrieren"));
        element.click();
    }
}