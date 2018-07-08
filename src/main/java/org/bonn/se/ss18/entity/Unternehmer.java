package org.bonn.se.ss18.entity;


import org.bonn.se.ss18.dto.UnternehmerDTO;

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

    public Unternehmer(UnternehmerDTO unternehmerDTO) {
        setId(unternehmerDTO.getId());
        setUnternehmerid(unternehmerDTO.getUnternehmerid());
        setFirmenname(unternehmerDTO.getFirmenname());
        setWebsite(unternehmerDTO.getWebsite());
        setAnsprechpartner(unternehmerDTO.getAnsprechpartner());
        setBranchenid(unternehmerDTO.getBranchenid());
    }

    final public String getAnsprechpartner() {
        return ansprechpartner;
    }

    final public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    final public int getUnternehmerid() {
        return unternehmerid;
    }

    final public void setUnternehmerid(int unternehmerid) {
        this.unternehmerid = unternehmerid;
    }

    final public String getFirmenname() {
        return firmenname;
    }

    final public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    final public String getWebsite() {
        return website;
    }

    final public void setWebsite(String website) {
        this.website = website;
    }

    final public int getBranchenid() {
        return branchenid;
    }

    final public void setBranchenid(int branchenid) {
        this.branchenid = branchenid;
    }

}
