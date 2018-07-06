package org.bonn.se.ss18.entity;

import org.bonn.se.ss18.dto.StudentDTO;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author rjourd2s
 */
public class Student extends User {
    private String linuxID;
    private String anrede;
    private String vorname;
    private String nachname;
    private LocalDate gebDatum;

    public Student() {
    }

    public Student(User user) {
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

    public Student(StudentDTO studentDTO) {
        setId(studentDTO.getId());
        setLinuxID(studentDTO.getLinuxID());
        setAnrede(studentDTO.getAnrede());
        setVorname(studentDTO.getVorname());
        setNachname(studentDTO.getNachname());
        setGebDatum(Date.valueOf(studentDTO.getGebDatum()));
    }

    public String getLinuxID() {
        return linuxID;
    }

    public void setLinuxID(String linuxID) {
        this.linuxID = linuxID;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(Date gebDatum) {
        this.gebDatum = gebDatum.toLocalDate();
    }

}
