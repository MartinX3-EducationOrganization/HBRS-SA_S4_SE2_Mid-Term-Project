package org.bonn.se.ss18.entity;

import java.util.Arrays;

/**
 * @author rjourd2s
 */
public class Bewerbunganlage extends AbstractEntity {
    private int bewerbungid;
    private byte[] datei;

    final public int getBewerbungid() {
        return bewerbungid;
    }

    final public void setBewerbungid(int bewerbungid) {
        this.bewerbungid = bewerbungid;
    }

    final public byte[] getDatei() {
        if (datei == null) {
            return null;
        }
        return Arrays.copyOf(datei, datei.length);
    }

    final public void setDatei(byte[] datei) {
        if (datei == null) {
            this.datei = null;
        } else {
            this.datei = Arrays.copyOf(datei, datei.length);
        }
    }
}
