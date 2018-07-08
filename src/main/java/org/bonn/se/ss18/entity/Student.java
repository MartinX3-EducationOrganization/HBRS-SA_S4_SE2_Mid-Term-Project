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

    final public String getLinuxID() {
        return linuxID;
    }

    final public void setLinuxID(String linuxID) {
        this.linuxID = linuxID;
    }

    final public String getAnrede() {
        return anrede;
    }

    final public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    final public String getVorname() {
        return vorname;
    }

    final public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    final public String getNachname() {
        return nachname;
    }

    final public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    final public LocalDate getGebDatum() {
        return gebDatum;
    }

    final public void setGebDatum(Date gebDatum) {
        this.gebDatum = gebDatum.toLocalDate();
    }

}
