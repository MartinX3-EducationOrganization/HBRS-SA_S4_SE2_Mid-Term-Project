package org.bonn.se.ss18.entity;

import java.util.Date;

/**
 * @author rjourd2s
 */
public class Stellenausschreibung extends AbstractEntity {
    private int UnternehmensID;
    private String title;
    private String text;
    private Date datum;

    public Stellenausschreibung(String title, String text) {
        super();
        setTitle(title);
        setText(text);
    }

    public int getUnternehmensID() {
        return UnternehmensID;
    }

    public void setUnternehmensID(int unternehmensID) {
        UnternehmensID = unternehmensID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}