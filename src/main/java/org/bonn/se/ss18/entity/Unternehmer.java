package org.bonn.se.ss18.entity;


/**
 * @author rjourd2s
 */
public class Unternehmer extends User {
    private int unternehmerid;
    private int userid;
    private String firmenname;
    private String website;
    private int branchenid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getType() {
        return "Unternehmer";
    }

}
