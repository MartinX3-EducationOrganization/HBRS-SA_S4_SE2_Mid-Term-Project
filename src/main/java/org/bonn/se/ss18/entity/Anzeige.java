package org.bonn.se.ss18.entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Anzeige extends AbstractEntity {
    private int userid;
    private LocalDate datum;
    private String titel;
    private String ort;
    private String typ;
    private String anstellungsart;
    private String arbeitszeit;
    private int brancheid;
    private LocalDate beginn;
    private boolean aktiv;
    private String text;


    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum.toLocalDate();
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getAnstellungsart() {
        return anstellungsart;
    }

    public void setAnstellungsart(String anstellungsart) {
        this.anstellungsart = anstellungsart;
    }

    public String getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(String arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public int getBrancheid() {
        return brancheid;
    }

    public void setBrancheid(int brancheid) {
        this.brancheid = brancheid;
    }

    public LocalDate getBeginn() {
        return beginn;
    }

    public void setBeginn(Date beginn) {
        this.beginn = beginn.toLocalDate();
    }

    public boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }
}
