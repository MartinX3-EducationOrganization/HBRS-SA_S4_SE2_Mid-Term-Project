package org.bonn.se.ss18.entity;

import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Bewerbung extends AbstractEntity {
    private String linuxid;
    private int unternehmenid;
    private LocalDate datum;
    private byte[] anschreiben;
    private byte[] lebenslauf;

    final public String getLinuxid() {
        return linuxid;
    }

    final public void setLinuxid(String linuxid) {
        this.linuxid = linuxid;
    }

    final public int getUnternehmenid() {
        return unternehmenid;
    }

    final public void setUnternehmenid(int unternehmenid) {
        this.unternehmenid = unternehmenid;
    }

    final public LocalDate getDatum() {
        return datum;
    }

    final public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    final public byte[] getAnschreiben() {
        return anschreiben;
    }

    final public void setAnschreiben(byte[] anschreiben) {
        this.anschreiben = anschreiben;
    }

    final public byte[] getLebenslauf() {
        return lebenslauf;
    }

    final public void setLebenslauf(byte[] lebenslauf) {
        this.lebenslauf = lebenslauf;
    }
}
