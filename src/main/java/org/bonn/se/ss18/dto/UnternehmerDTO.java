/*
 * Created by Martin Dünkelmann on 23.06.18 17:32
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 06.06.18 17:12
 */

package org.bonn.se.ss18.dto;


import org.bonn.se.ss18.entity.Unternehmer;

/**
 * @author mduenk2s
 */
public class UnternehmerDTO extends UserDTO {
    private int unternehmerid;
    private String firmenname;
    private String website;
    private int branchenid;
    private String ansprechpartner;
    private boolean checkboxAGB;

    public UnternehmerDTO() {
        super();
    }

    public UnternehmerDTO(Unternehmer unternehmer) {
        super();
        setAnsprechpartner(unternehmer.getAnsprechpartner());
        setUnternehmerid(unternehmer.getUnternehmerid());
        setFirmenname(unternehmer.getFirmenname());
        setWebsite(unternehmer.getWebsite());
        setBranchenid(unternehmer.getBranchenid());
        setCheckboxAGB(false);
        setPasswort(unternehmer.getPasswort());
        setStrasse(unternehmer.getStrasse());
        setHausnr(unternehmer.getHausnr());
        setPlz(unternehmer.getPlz());
        setOrt(unternehmer.getOrt());
        setEmail(unternehmer.getEmail());
        setTelNr(unternehmer.getTelNr());
        setFaxNr(unternehmer.getFaxNr());
        setFoto(unternehmer.getFoto());
        setKurzVorstellung(unternehmer.getKurzVorstellung());
        setId(unternehmer.getId());
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

    public boolean isCheckboxAGB() {
        return checkboxAGB;
    }

    public void setCheckboxAGB(boolean checkboxAGB) {
        this.checkboxAGB = checkboxAGB;
    }

    @Override
    public Unternehmer toEntity() {
        Unternehmer unternehmer = new Unternehmer();
        unternehmer.setFirmenname(getFirmenname());
        unternehmer.setEmail(getEmail());
        unternehmer.setTelNr(getTelNr());
        unternehmer.setOrt(getOrt());
        unternehmer.setPlz(getPlz());
        return unternehmer;
    }
}
