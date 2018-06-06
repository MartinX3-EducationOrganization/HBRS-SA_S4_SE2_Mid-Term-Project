package org.bonn.se.ss18.entity;

/**
 * @author rjourd2s
 */
public class Bewerbunganlage extends AbstractEntity {
    private int bewerbungid;
    private byte[] datei;
    

    public int getBewerbungid() {
        return bewerbungid;
    }

    public void setBewerbungid(int bewerbungid) {
        this.bewerbungid = bewerbungid;
    }

    public byte[] getDatei() {
        return datei;
    }

    public void setDatei(byte[] datei) {
        this.datei = datei;
    }
}
