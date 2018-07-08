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

    final public String getTitel() {
        return titel;
    }

    final public void setTitel(String titel) {
        this.titel = titel;
    }

    final public String getText() {
        return text;
    }

    final public void setText(String text) {
        this.text = text;
    }

    final public int getUserid() {
        return userid;
    }

    final public void setUserid(int userid) {
        this.userid = userid;
    }

    final public LocalDate getDatum() {
        return datum;
    }

    final public void setDatum(Date datum) {
        this.datum = datum.toLocalDate();
    }

    final public String getOrt() {
        return ort;
    }

    final public void setOrt(String ort) {
        this.ort = ort;
    }

    final public String getTyp() {
        return typ;
    }

    final public void setTyp(String typ) {
        this.typ = typ;
    }

    final public String getAnstellungsart() {
        return anstellungsart;
    }

    final public void setAnstellungsart(String anstellungsart) {
        this.anstellungsart = anstellungsart;
    }

    final public String getArbeitszeit() {
        return arbeitszeit;
    }

    final public void setArbeitszeit(String arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    final public int getBrancheid() {
        return brancheid;
    }

    final public void setBrancheid(int brancheid) {
        this.brancheid = brancheid;
    }

    final public LocalDate getBeginn() {
        return beginn;
    }

    final public void setBeginn(Date beginn) {
        this.beginn = beginn.toLocalDate();
    }

    final public boolean getAktiv() {
        return aktiv;
    }

    final public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }
}
