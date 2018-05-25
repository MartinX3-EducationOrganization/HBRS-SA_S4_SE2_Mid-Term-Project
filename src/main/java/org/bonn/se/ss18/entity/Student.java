package org.bonn.se.ss18.entity;

import java.util.Date;

/**
 * @author rjourd2s
 */
public class Student extends User {
    private String linuxID;
    private int userID;
    private String anrede;
    private String vorname;
    private String nachname;
    private Date gebDatum;


    public Student(int userID, String passwort, String strasse, String hausnr, String plz, String ort, String email, String telNr, String faxNr, byte[] foto, String kurzvorstellung) {
        super(userID, passwort, strasse, hausnr, plz, ort, email, telNr, faxNr, foto, kurzvorstellung);
        linuxID = linuxID;
        this.userID = userID;
        anrede = anrede;
        vorname = vorname;
        nachname = nachname;
        gebDatum = gebDatum;
    }
}
