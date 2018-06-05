package org.bonn.se.ss18.entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Anzeige extends AbstractEntity {
    private int anzeigeid;
    private int userid;
    private LocalDate datum;
    private String title;
    private String ort;
    private String typ;
    private String anstellungsart;
    private String arbeitszeit;
    private int branchenid;
    private LocalDate beginn;
    private boolean aktiv;
    private String text;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnzeigeid() {
        return anzeigeid;
    }

    public void setAnzeigeid(int anzeigeid) {
        this.anzeigeid = anzeigeid;
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

    public int getBranchenid() {
        return branchenid;
    }

    public void setBranchenid(int branchenid) {
        this.branchenid = branchenid;
    }

    public LocalDate getBeginn() {
        return beginn;
    }

    public void setBeginn(Date beginn) {
        this.beginn = beginn.toLocalDate();
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }
}
