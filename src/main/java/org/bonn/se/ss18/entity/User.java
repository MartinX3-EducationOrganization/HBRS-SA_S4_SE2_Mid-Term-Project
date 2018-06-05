package org.bonn.se.ss18.entity;


/**
 * @author rjourd2s
 */
public class User {
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

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public void setFaxNr(String faxNr) {
        this.faxNr = faxNr;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setKurzVorstellung(String kurzVorstellung) {
        this.kurzVorstellung = kurzVorstellung;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnr() {
        return hausnr;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getEmail() {
        return email;
    }

    public String getTelNr() {
        return telNr;
    }

    public String getFaxNr() {
        return faxNr;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getKurzVorstellung() {
        return kurzVorstellung;
    }
}
