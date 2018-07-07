package org.bonn.se.ss18.entity;

/**
 * @author rjourd2s
 */
public class Branche extends AbstractEntity {
    private String bezeichnung;

    public Branche(int brancheid, String bezeichnung) {
        super();
        setId(brancheid);
        setBezeichnung(bezeichnung);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}

