package org.bonn.se.ss18.entity;

import java.util.Date;

/**
 * @author rjourd2s
 */
public class Student extends User {
    private String linuxID;
    private String anrede;
    private String vorname;
    private String nachname;
    private Date gebDatum;

    public String getLinuxID() {
        return linuxID;
    }

    public void setLinuxID(String linuxID) {
        this.linuxID = linuxID;
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
