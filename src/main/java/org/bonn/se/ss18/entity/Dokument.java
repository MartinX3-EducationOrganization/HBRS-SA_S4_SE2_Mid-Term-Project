package org.bonn.se.ss18.entity;

/**
 * @author rjourd2s
 */
public class Dokument extends AbstractEntity {
    private int userid;
    private String dateiname;
    private byte[] datei;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDateiname() {
        return dateiname;
    }

    public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }

    public byte[] getDatei() {
        return datei;
    }

    public void setDatei(byte[] datei) {
        this.datei = datei;
    }
}
