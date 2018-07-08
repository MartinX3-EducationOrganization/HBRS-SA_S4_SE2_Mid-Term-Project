package org.bonn.se.ss18.entity;


import org.bonn.se.ss18.dto.UserDTO;

/**
 * @author rjourd2s
 */
public class User extends AbstractEntity {
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

    public User() {
    }

    public User(UserDTO userDTO) {
        setId(userDTO.getId());
        setTelNr(userDTO.getTelNr());
        setStrasse(userDTO.getStrasse());
        setPasswort(userDTO.getPasswort());
        setOrt(userDTO.getOrt());
        setKurzVorstellung(userDTO.getKurzVorstellung());
        setHausnr(userDTO.getHausnr());
        setFoto(userDTO.getFoto());
        setFaxNr(userDTO.getFaxNr());
        setEmail(userDTO.getEmail());
        setPlz(userDTO.getPlz());
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
