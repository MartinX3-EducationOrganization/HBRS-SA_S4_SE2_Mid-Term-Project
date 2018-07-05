/*
 * Created by Martin Dünkelmann on 23.06.18 17:30
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 06.06.18 16:54
 */

package org.bonn.se.ss18.dto;


import org.bonn.se.ss18.entity.User;

/**
 * @author mduenk2s
 */
public class UserDTO extends AbstractDTO {
    private String passwort;
    private String strasse;
    private String hausnr;
    private String plz;
    private String ort;
    private String email;
    private String telNr;
    private String faxNr;
    private byte[] foto;
    private String kurzVorstellung;

    public UserDTO() {
        super();
    }

    public UserDTO(UserDTO userDTO) {
        super();
        setPasswort(userDTO.getPasswort());
        setStrasse(userDTO.getStrasse());
        setHausnr(userDTO.getHausnr());
        setPlz(userDTO.getPlz());
        setOrt(userDTO.getOrt());
        setEmail(userDTO.getEmail());
        setTelNr(userDTO.getTelNr());
        setFaxNr(userDTO.getFaxNr());
        setFoto(userDTO.getFoto());
        setKurzVorstellung(userDTO.getKurzVorstellung());
        setId(userDTO.getId());
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnr() {
        return hausnr;
    }

    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getFaxNr() {
        return faxNr;
    }

    public void setFaxNr(String faxNr) {
        this.faxNr = faxNr;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getKurzVorstellung() {
        return kurzVorstellung;
    }

    public void setKurzVorstellung(String kurzVorstellung) {
        this.kurzVorstellung = kurzVorstellung;
    }

    public User toEntity() {
        User user = new User();
        user.setEmail(getEmail());
        user.setFaxNr(getFaxNr());
        user.setFoto(getFoto());
        user.setHausnr(getHausnr());
        user.setKurzVorstellung(getKurzVorstellung());
        user.setOrt(getOrt());
        user.setPasswort(getPasswort());
        user.setPlz(getPlz());
        user.setStrasse(getStrasse());
        user.setTelNr(getTelNr());
        user.setId(getId());
        return user;
    }
}
