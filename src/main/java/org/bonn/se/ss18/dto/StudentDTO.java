/*
 * Created by Martin Dünkelmann on 05.07.18 17:54
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 25.06.18 19:35
 */

package org.bonn.se.ss18.dto;

import org.bonn.se.ss18.entity.Student;

import java.time.LocalDate;

/**
 * @author mduenk2s
 */
public class StudentDTO extends UserDTO {
    private String linuxID;
    private String anrede;
    private String vorname;
    private String nachname;
    private LocalDate gebDatum;
    private String socialMediaLink = "";

    public StudentDTO() {
        super();
    }

    public StudentDTO(Student student) {
        super();
        setLinuxID(student.getLinuxID());
        setAnrede(student.getAnrede());
        setVorname(student.getVorname());
        setNachname(student.getNachname());
        setGebDatum(student.getGebDatum());
        setPasswort(student.getPasswort());
        setStrasse(student.getStrasse());
        setHausnr(student.getHausnr());
        setPlz(student.getPlz());
        setOrt(student.getOrt());
        setEmail(student.getEmail());
        setTelNr(student.getTelNr());
        setFaxNr(student.getFaxNr());
        setFoto(student.getFoto());
        setKurzVorstellung(student.getKurzVorstellung());
        setId(student.getId());
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

    final public void setGebDatum(LocalDate gebDatum) {
        this.gebDatum = gebDatum;
    }

    final public String getSocialMediaLink() {
        return socialMediaLink;
    }

    final public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }
}
