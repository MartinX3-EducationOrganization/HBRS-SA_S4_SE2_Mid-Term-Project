package org.bonn.se.ss18.entity;


/**
 * @author rjourd2s
 */
public class User {
    private final int userID;
    private final String passwort;
    private final String strasse;
    private final String hausnr;
    private final String plz;
    private final String ort;
    private final String email;
    private final String telNr;
    private final String faxNr;
    private final byte[] foto;
    private final String kurzVorstellung;

    public User(int userID, String passwort, String strasse, String hausnr, String plz, String ort, String email, String telNr, String faxNr, byte[] foto, String kurzvorstellung) {
        this.userID = userID;
        this.passwort = passwort;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.plz = plz;
        this.ort = ort;
        this.email = email;
        this.telNr = telNr;
        this.faxNr = faxNr;
        this.foto = foto;
        kurzVorstellung = kurzvorstellung;
    }

    public int getUserID() {
        return userID;
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
