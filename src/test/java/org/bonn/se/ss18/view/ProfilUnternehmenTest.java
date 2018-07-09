package org.bonn.se.ss18.view;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ProfilUnternehmenTest {

    private WebDriver driver;



    @Before
    public void setUp() throws Exception {
        //pfad zu Chrome driver
        String CHROME_PATH = "driver/chromedriver.exe";//systemproperty key
        String SYSTEM_PATH = "webdriver.chrome.driver";
        System.setProperty(SYSTEM_PATH, CHROME_PATH);
        //erzeugen den Chrome driver
        driver = new ChromeDriver();
        //nach auführung einer Action warte von 15s
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //URL aufrufen
        String URL = "http://localhost:8080/";
        driver.get(URL);
    }

    @Test
    public void testProfilUnternehmen() throws InterruptedException {
        WebElement element = driver.findElement(By.id("gwt-uid-15"));
        //in der gefundene inpult feld soll die user name salda2s eingegeben werden
        element.sendKeys("email@unternehmen.de");

        element = driver.findElement(By.id("gwt-uid-16"));
        element.sendKeys("123");

        //anmelder button mit der id log-in soll gefunden werden
        element = driver.findElement(By.id("log-in"));
        //soll die gefundene anmelde button klicken
        element.click();


    }
}