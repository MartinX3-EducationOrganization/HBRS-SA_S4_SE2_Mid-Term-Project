/*
 * Created by Martin Dünkelmann on 23.06.18 17:30
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 06.06.18 16:54
 */

package org.bonn.se.ss18.dto;


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

    final public String getPasswort() {
        return passwort;
    }

    final public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    final public String getStrasse() {
        return strasse;
    }

    final public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    final public String getHausnr() {
        return hausnr;
    }

    final public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    final public String getPlz() {
        return plz;
    }

    final public void setPlz(String plz) {
        this.plz = plz;
    }

    final public String getOrt() {
        return ort;
    }

    final public void setOrt(String ort) {
        this.ort = ort;
    }

    final public String getEmail() {
        return email;
    }

    final public void setEmail(String email) {
        this.email = email;
    }

    final public String getTelNr() {
        return telNr;
    }

    final public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    final public String getFaxNr() {
        return faxNr;
    }

    final public void setFaxNr(String faxNr) {
        this.faxNr = faxNr;
    }

    final public byte[] getFoto() {
        return foto;
    }

    final public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    final public String getKurzVorstellung() {
        return kurzVorstellung;
    }

    final public void setKurzVorstellung(String kurzVorstellung) {
        this.kurzVorstellung = kurzVorstellung;
    }
}
