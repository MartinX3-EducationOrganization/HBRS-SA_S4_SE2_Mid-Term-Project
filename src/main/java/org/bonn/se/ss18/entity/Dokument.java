package org.bonn.se.ss18.entity;

import java.util.Arrays;

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
