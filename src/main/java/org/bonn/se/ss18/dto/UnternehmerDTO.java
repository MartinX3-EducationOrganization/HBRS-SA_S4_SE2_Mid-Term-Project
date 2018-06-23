/*
 * Created by Martin Dünkelmann on 23.06.18 17:32
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 06.06.18 17:12
 */

package org.bonn.se.ss18.dto;


/**
 * @author rjourd2s
 */
public class UnternehmerDTO extends UserDTO {
    private int unternehmerid;
    private String firmenname;
    private String website;
    private int branchenid;
    private String ansprechpartner;

    public String getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public int getUnternehmerid() {
        return unternehmerid;
    }

    public void setUnternehmerid(int unternehmerid) {
        this.unternehmerid = unternehmerid;
    }

    public String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getBranchenid() {
        return branchenid;
    }

    public void setBranchenid(int branchenid) {
        this.branchenid = branchenid;
    }

}
