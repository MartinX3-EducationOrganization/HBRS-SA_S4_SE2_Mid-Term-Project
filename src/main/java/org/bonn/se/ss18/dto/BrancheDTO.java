/*
 * Created by Martin Dünkelmann on 07.07.18 14:17
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 07.07.18 14:17
 */

package org.bonn.se.ss18.dto;

import org.bonn.se.ss18.entity.Branche;

/**
 * @author martin on 07.07.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class BrancheDTO extends AbstractDTO {
    private String bezeichnung;

    public BrancheDTO(Branche branche) {
        super();

        setBezeichnung(branche.getBezeichnung());
        setId(branche.getId());
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
