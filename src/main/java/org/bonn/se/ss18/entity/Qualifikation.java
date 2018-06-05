package org.bonn.se.ss18.entity;

import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Qualifikation {
    private int qualifikationid;
    private String inuxid;
    private String typ;
    private String bezeichnung;
    private LocalDate von;
    private LocalDate bis;

    public int getQualifikationid() {
        return qualifikationid;
    }

    public void setQualifikationid(int qualifikationid) {
        this.qualifikationid = qualifikationid;
    }

    public String getInuxid() {
        return inuxid;
    }

    public void setInuxid(String inuxid) {
        this.inuxid = inuxid;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public LocalDate getVon() {
        return von;
    }

    public void setVon(LocalDate von) {
        this.von = von;
    }

    public LocalDate getBis() {
        return bis;
    }

    public void setBis(LocalDate bis) {
        this.bis = bis;
    }
}
