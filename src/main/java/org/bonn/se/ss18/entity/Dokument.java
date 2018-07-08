package org.bonn.se.ss18.entity;

/**
 * @author rjourd2s
 */
public class Dokument extends AbstractEntity {
    private int userid;
    private String dateiname;
    private byte[] datei;

    final public int getUserid() {
        return userid;
    }

    final public void setUserid(int userid) {
        this.userid = userid;
    }

    final public String getDateiname() {
        return dateiname;
    }

    final public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }

    final public byte[] getDatei() {
        return datei;
    }

    final public void setDatei(byte[] datei) {
        this.datei = datei;
    }
}
