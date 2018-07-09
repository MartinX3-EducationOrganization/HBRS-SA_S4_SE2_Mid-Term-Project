package org.bonn.se.ss18.entity;

import java.time.LocalDate;
import java.util.Arrays;

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
        if (anschreiben == null) {
            return null;
        }
        return Arrays.copyOf(anschreiben, anschreiben.length);
    }

    final public void setAnschreiben(byte[] anschreiben) {
        if (anschreiben == null) {
            this.anschreiben = null;
        } else {
            this.anschreiben = Arrays.copyOf(anschreiben, anschreiben.length);
        }
    }

    final public byte[] getLebenslauf() {
        if (lebenslauf == null) {
            return null;
        }
        return Arrays.copyOf(lebenslauf, lebenslauf.length);
    }

    final public void setLebenslauf(byte[] lebenslauf) {
        if (lebenslauf == null) {
            this.lebenslauf = null;
        } else {
            this.lebenslauf = Arrays.copyOf(lebenslauf, lebenslauf.length);
        }
    }
}