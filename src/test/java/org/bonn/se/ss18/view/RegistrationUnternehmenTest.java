package org.bonn.se.ss18.view;
import junit.framework.TestCase;
import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Tables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



import java.util.concurrent.TimeUnit;

public class RegistrationUnternehmenTest extends TestCase {
    //Webseite URL
    private static String URL = "http://localhost:8080/";
    //systemproperty key
    private static String SYSTEM_PATH = "webdriver.chrome.driver";
    //pfad zu Chrome driver
    private static String CHROME_PATH = "driver/chromedriver.exe";

    private WebDriver driver;
     private Unternehmer unternehmer = new Unternehmer();
    private UnternehmerDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = (UnternehmerDAO) (ConnectionFactory.getInstance().getDAO(Tables.table_unternehmen));

       // unternehmer.setAnsprechpartner("Herr Musteransprechpartner");
        unternehmer.setFirmenname("Musterfirma");


      //  unternehmer.setWebsite("google.com");
        //unternehmer.setUnternehmerid(4);
        unternehmer.setEmail("mail@unternehmen.de");
        unternehmer.setTelNr("023456");
         unternehmer.setPasswort("123");
        //unternehmer.setStrasse("berungstr");
        //unternehmer.setHausnr("12a");
        unternehmer.setPlz("31567");
        unternehmer.setOrt("Bonn");
        //unternehmer.setKurzVorstellung("Bio");
        //unternehmer.setFoto(null);
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

    public  void testRegistrationUnternehmen() throws InterruptedException {
        WebElement element = driver.findElement(By.id("registrieren_in"));
        element.click();


        // element = driver.findElement(By.id("gwt-uid-27"));


       // element = driver.findElement(By.id("gwt-uid-14"));
       //element.sendKeys(unternehmer.getAnsprechpartner());



        //element.sendKeys("Thomas Zimmermann");

        element = driver.findElement(By.id("unternehmer"));
        element.sendKeys(unternehmer.getFirmenname());

        element = driver.findElement(By.id("email"));
        element.sendKeys(unternehmer.getEmail());

        element = driver.findElement(By.id("passwort"));
        element.sendKeys(unternehmer.getPasswort());

        element = driver.findElement(By.id("telefon"));
        element.sendKeys(unternehmer.getTelNr());

        element = driver.findElement(By.id("ort"));
        // element.sendKeys("52117, Bonn");
        element.sendKeys(unternehmer.getOrt());

        element = driver.findElement(By.id("plz"));
        element.sendKeys(unternehmer.getPlz());

      //  element = driver.findElement(By.id("gwt-uid-18"));
      //  element.sendKeys("16.04.10");




        //element.sendKeys("Zimmermann@gmail.de");




        //element.sendKeys("020287654");





        element = driver.findElement(By.id("agb"));
        element.click();
       // ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", element);

        //element.click();
      //  element = driver.findElement(By.id("gwt-uid-26"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", element);
       // element.click();


        element = driver.findElement(By.id("registrieren"));
        element.click();





























    }

  //@After
  //public void tearDown(){

      //  driver.close();
   // }


}