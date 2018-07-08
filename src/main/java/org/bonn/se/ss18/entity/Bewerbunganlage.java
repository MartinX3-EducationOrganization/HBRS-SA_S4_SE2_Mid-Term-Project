package org.bonn.se.ss18.entity;

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
        return datei;
    }

    final public void setDatei(byte[] datei) {
        this.datei = datei;
    }
}
