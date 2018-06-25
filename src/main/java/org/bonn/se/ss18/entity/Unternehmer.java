package org.bonn.se.ss18.entity;


/**
 * @author rjourd2s
 */
public class Unternehmer extends User {
    private int unternehmerid;
    private String firmenname;
    private String website;
    private int branchenid;
    private String ansprechpartner;

    public Unternehmer() {
    }

    public Unternehmer(User user) {
        super();
        setId(user.getId());
        setEmail(user.getEmail());
        setOrt(user.getOrt());
        setPasswort(user.getPasswort());
        setPlz(user.getPlz());
        setFaxNr(user.getFaxNr());
        setFoto(user.getFoto());
        setHausnr(user.getHausnr());
        setKurzVorstellung(user.getKurzVorstellung());
        setStrasse(user.getStrasse());
        setTelNr(user.getTelNr());
    }

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
