package org.bonn.se.ss18.view;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BewerbenStudentTest {
    //Webseite URL
    private static String URL = "http://localhost:8080/";
    //systemproperty key
    private static String SYSTEM_PATH = "webdriver.chrome.driver";
    //pfad zu Chrome driver
    private static String CHROME_PATH = "driver/chromedriver.exe";

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        //setzen den Chrome driver in system umgebung
        System.setProperty(SYSTEM_PATH, CHROME_PATH);
        //erzeugen den Chrome driver
        driver = new ChromeDriver();
        //nach ausführung einer Action warte von 15s
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //URL aufrufen
        driver.get(URL);
    }


    @Test
    public void testBewerbenStudent() throws  InterruptedException{
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

         element = driver.findElement(By.id("bewerben"));
        element.click();


        // element = driver.findElement(By.id("gwt-uid-27"));


        element = driver.findElement(By.id("gwt-uid-31"));

        element.sendKeys("Beispiel");

        element = driver.findElement(By.id("gwt-uid-34"));
        element.sendKeys("Gehalt");


        element = driver.findElement(By.id("gwt-uid-36"));
        element.sendKeys("Backen");

    }

    }
