package org.bonn.se.ss18.view;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ProfilStudentTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        //setzen den Chrome driver in system umgebung
        //pfad zu Chrome driver
        String CHROME_PATH = "driver/chromedriver.exe";//systemproperty key
        String SYSTEM_PATH = "webdriver.chrome.driver";
        System.setProperty(SYSTEM_PATH, CHROME_PATH);
        //erzeugungen den Chrome driver
        driver = new ChromeDriver();
        //nach ausführung einer Action warte von 15s
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //URL aufrufen
        //Webseite URL
        String URL = "http://localhost:8080/";
        driver.get(URL);
    }

    @Test

    public void testProfilStudent() throws InterruptedException{
        WebElement element = driver.findElement(By.id("gwt-uid-3"));
        //in der gefundene inpult feld soll die user name salda2s eingegeben werden
        element.sendKeys("salda2s");

        element = driver.findElement(By.id("gwt-uid-5"));
        element.sendKeys("123");

        //anmelden button mit der id log-in soll gefunden werden
        element = driver.findElement(By.id("log-in"));
        //soll die gefundene anmelden button klicken
        element.click();

        element = driver.findElement(By.id("profil"));
        element.click();

        element = driver.findElement(By.id("firstname"));
        Assert.assertEquals("Sascha",element.getAttribute("value"));

        element = driver.findElement(By.id("lastname"));
        Assert.assertEquals("Alda",element.getAttribute("value"));



        element = driver.findElement(By.id("birthday"));
        Assert.assertNull(element.getAttribute("value")); // DAtum nicht erreichbar

        element = driver.findElement(By.id("street"));
        Assert.assertEquals("Hauptstraße",element.getAttribute("value"));



        element = driver.findElement(By.id("hs"));
        Assert.assertEquals("18",element.getAttribute("value"));


        element = driver.findElement(By.id("plz"));
        Assert.assertEquals("53757",element.getAttribute("value"));


        element = driver.findElement(By.id("ort"));
        Assert.assertEquals("Sankt Augustin",element.getAttribute("value"));



        element = driver.findElement(By.id("email"));
        Assert.assertEquals("e@m.de",element.getAttribute("value"));



        element = driver.findElement(By.id("tel"));
        Assert.assertEquals("0716180",element.getAttribute("value"));


        element = driver.findElement(By.id("fax"));
        Assert.assertEquals("298189",element.getAttribute("value"));

       element = driver.findElement(By.id("bio"));
        Assert.assertEquals("neuerwert",element.getText());


        element = driver.findElement(By.id("edit"));

        element.click();



















    }

}