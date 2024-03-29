/*
 * Created by Martin Dünkelmann on 19.06.18 14:02
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 19.06.18 14:02
 */

package org.bonn.se.ss18.entity;

import java.time.LocalDate;

/**
 * @author martin on 19.06.18
 * @project wi-inf_se2_2018_grundgeruest
 */
class EntityGitConflictTest {
    private int alter;
    private LocalDate bday;
    private String vorname;
    private String nachname;

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = 10;

    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
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
}
