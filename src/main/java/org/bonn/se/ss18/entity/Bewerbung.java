package org.bonn.se.ss18.entity;

import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Bewerbung {
    private int bewerbungid;
    private String linuxid;
    private int unternehmenid;
    private LocalDate datum;
    private byte[] anschreiben;
    private byte[] lebenslauf;

    public int getBewerbungid() {
        return bewerbungid;
    }

    public void setBewerbungid(int bewerbungid) {
        this.bewerbungid = bewerbungid;
    }

    public String getLinuxid() {
        return linuxid;
    }

    public void setLinuxid(String linuxid) {
        this.linuxid = linuxid;
    }

    public int getUnternehmenid() {
        return unternehmenid;
    }

    public void setUnternehmenid(int unternehmenid) {
        this.unternehmenid = unternehmenid;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public byte[] getAnschreiben() {
        return anschreiben;
    }

    public void setAnschreiben(byte[] anschreiben) {
        this.anschreiben = anschreiben;
    }

    public byte[] getLebenslauf() {
        return lebenslauf;
    }

    public void setLebenslauf(byte[] lebenslauf) {
        this.lebenslauf = lebenslauf;
    }
}
