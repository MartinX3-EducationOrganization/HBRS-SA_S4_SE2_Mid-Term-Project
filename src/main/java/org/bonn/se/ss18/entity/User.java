package org.bonn.se.ss18.entity;


import org.bonn.se.ss18.dto.UnternehmerDTO;

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

    public User(UnternehmerDTO unternehmerDTO) {
        setId(unternehmerDTO.getId());
        setTelNr(unternehmerDTO.getTelNr());
        setStrasse(unternehmerDTO.getStrasse());
        setPasswort(unternehmerDTO.getPasswort());
        setOrt(unternehmerDTO.getOrt());
        setKurzVorstellung(unternehmerDTO.getKurzVorstellung());
        setHausnr(unternehmerDTO.getHausnr());
        setFoto(unternehmerDTO.getFoto());
        setFaxNr(unternehmerDTO.getFaxNr());
        setEmail(unternehmerDTO.getEmail());
        setPlz(unternehmerDTO.getPlz());
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
}
