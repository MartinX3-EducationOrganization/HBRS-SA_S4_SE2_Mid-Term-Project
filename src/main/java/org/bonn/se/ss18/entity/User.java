package org.bonn.se.ss18.entity;


/**
 * @author rjourd2s
 */
public class User {
    private String userID;
    private String passwort;
    private String strasse;
    private String hausnr;
    private String plz;
    private String ort;
    private String email;
    private String telNr;
    private String faxNr;
    private Byte foto;
    private String kurzVorstellung;

    public User(String userID, String passwort, String strasse, String hausnr, String plz, String ort, String email) {
        this.userID = userID;
        this.passwort = passwort;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.plz = plz;
        this.ort = ort;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public Byte getFoto() {
        return foto;
    }

    public void setFoto(Byte foto) {
        this.foto = foto;
    }

    public String getKurzVorstellung() {
        return kurzVorstellung;
    }

    public void setKurzVorstellung(String kurzVorstellung) {
        this.kurzVorstellung = kurzVorstellung;
    }
}
