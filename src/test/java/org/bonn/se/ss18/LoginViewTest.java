package org.bonn.se.ss18;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginViewTest extends TestCase {
    //Webseite URL
    private static String URL = "http://localhost:8080/";
    //systemproperty key
    private static String SYSTEM_PATH = "webdriver.chrome.driver";
    //pfad zu Chrome driver
    private static String CHROME_PATH = "driver/chromedriver.exe";

    private WebDriver driver;

    @Before
    public void setUp(){
        //setzen den Chrome driver in system umgebung
        System.setProperty(SYSTEM_PATH, CHROME_PATH);
        //erzeugen den Chrome driver
        driver = new ChromeDriver();
        //nach auführung einer Action warte von 15s
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //URL aufrufen
        driver.get(URL);
    }

    @Test
    public void testLogin(){
        //website aufgerufen und driver soll den webelement mit der Id finden(input feld für user name)
        WebElement element = driver.findElement(By.id("gwt-uid-3"));
        //in der gefundene inpult feld soll die user name salda2s eingegeben werden
        element.sendKeys("salda2s");

        element = driver.findElement(By.id("gwt-uid-5"));
        element.sendKeys("123");

        //anmelder button mit der id log-in soll gefunden werden
        element = driver.findElement(By.id("log-in"));
        //soll die gefundene anmelde button klicken
        element.click();
        //nach erfolgreicher anmeldung soll diese Vorname label gefunden werden

        element = driver.findElement(By.id("gwt-uid-8"));

        //label text muss vorame heissen
        Assert.assertEquals("Vorame", element.getText());

        element = driver.findElement(By.className("log-out"));
        element.click();




    }

    @After
    public void tearDown(){

        driver.close();
    }




}
