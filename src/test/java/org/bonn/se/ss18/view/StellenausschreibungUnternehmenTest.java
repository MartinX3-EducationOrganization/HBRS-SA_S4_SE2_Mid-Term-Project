package org.bonn.se.ss18.view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StellenausschreibungUnternehmenTest  {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

            //setzen den Chrome driver in system umgebung
        //pfad zu Chrome driver
        String CHROME_PATH = "driver/chromedriver.exe";//systemproperty key
        String SYSTEM_PATH = "webdriver.chrome.driver";
        System.setProperty(SYSTEM_PATH, CHROME_PATH);
            //erzeugen den Chrome driver
            driver = new ChromeDriver();
            //nach ausführung einer Action warte von 15s
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            //URL aufrufen
        String URL = "http://localhost:8080/";
        driver.get(URL);
        }

        @Test
        public void testStellenausschreibungUnternehmrn()throws InterruptedException{


            WebElement element = driver.findElement(By.id("gwt-uid-3"));
            //in der gefundene inpult feld soll die user name salda2s eingegeben werden
            element.sendKeys("email@unternehmen.de");

            element = driver.findElement(By.id("gwt-uid-5"));
            element.sendKeys("123");

            //anmelder button mit der id log-in soll gefunden werden
            element = driver.findElement(By.id("log-in"));
            //soll die gefundene anmelde button klicken
            element.click();


            element = driver.findElement(By.id("stellen"));
            element.click();

            element = driver.findElement(By.id("gut"));
            Assert.assertEquals("Eine gute Stelle!" ,element.getText());


            element = driver.findElement(By.id("schlecht"));
            Assert.assertEquals("Schlechte Bezahlung!" ,element.getText());













        }
        @After
        public void tearDown(){

        driver.close();
        }



}
