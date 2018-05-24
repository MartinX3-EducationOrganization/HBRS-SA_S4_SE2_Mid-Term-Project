package org.bonn.se.ss18.entity;

import java.util.Date;

/**
 * @author rjourd2s
 */
public class Student extends User {
    private String linuxID;
    private String userID;
    private String anrede;
    private String vorname;
    private String nachname;
    private Date gebDatum;

    public Student(String userID, String passwort, String strasse, String hausnr, String plz, String ort, String email) {
        super(userID, passwort, strasse, hausnr, plz, ort, email);
        linuxID = linuxID;
        this.userID = userID;
        anrede = anrede;
        vorname = vorname;
        nachname = nachname;
        gebDatum = gebDatum;
    }

    public String getLinuxID() {
        return linuxID;
    }

    public void setLinuxID(String linuxID) {
        this.linuxID = linuxID;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(Date gebDatum) {
        this.gebDatum = gebDatum;
    }


}
