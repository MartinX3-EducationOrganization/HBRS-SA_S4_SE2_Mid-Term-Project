package org.bonn.se.ss18.entity;

import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Qualifikation extends AbstractEntity {
    private String inuxid;
    private String typ;
    private String bezeichnung;
    private LocalDate von;
    private LocalDate bis;

    final public String getInuxid() {
        return inuxid;
    }

    final public void setInuxid(String inuxid) {
        this.inuxid = inuxid;
    }

    final public String getTyp() {
        return typ;
    }

    final public void setTyp(String typ) {
        this.typ = typ;
    }

    final public String getBezeichnung() {
        return bezeichnung;
    }

    final public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    final public LocalDate getVon() {
        return von;
    }

    final public void setVon(LocalDate von) {
        this.von = von;
    }

    final public LocalDate getBis() {
        return bis;
    }

    final public void setBis(LocalDate bis) {
        this.bis = bis;
    }
}
