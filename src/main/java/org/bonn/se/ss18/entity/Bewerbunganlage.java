package org.bonn.se.ss18.entity;

/**
 * @author rjourd2s
 */
public class Bewerbunganlage {
    private int bewerbunganlageid;
    private int bewerbungid;
    private byte[] datei;

    public int getBewerbunganlageid() {
        return bewerbunganlageid;
    }

    public void setBewerbunganlageid(int bewerbunganlageid) {
        this.bewerbunganlageid = bewerbunganlageid;
    }

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
