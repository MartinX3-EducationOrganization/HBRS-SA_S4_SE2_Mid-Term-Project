/*
 * Created by Martin Dünkelmann on 23.06.18 11:59
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.06.18 11:59
 */

package org.bonn.se.ss18.controller;

import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;

/**
 * @author martin on 23.06.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class RegistrationController {
    public boolean registration(UnternehmerDTO unternehmerDTO) {
        UserDAO userDAO;
        UnternehmerDAO unternehmerDAO;

        try {
            userDAO = (UserDAO) ConnectionFactory.getInstance().getDAO(Tables.table_user);
            unternehmerDAO = (UnternehmerDAO) ConnectionFactory.getInstance().getDAO(Tables.table_unternehmen);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (userDAO.getByColumnValue("email", unternehmerDTO.getEmail()) != null) {
            return false;
        }

        unternehmerDAO.create(toEntity(unternehmerDTO));

        return true;
    }

    private Unternehmer toEntity(UnternehmerDTO unternehmerDTO) {
        Unternehmer unternehmer = new Unternehmer();
        unternehmer.setFirmenname(unternehmerDTO.getFirmenname());
        unternehmer.setEmail(unternehmerDTO.getEmail());
        unternehmer.setTelNr(unternehmerDTO.getTelNr());
        unternehmer.setOrt(unternehmerDTO.getOrt());
        unternehmer.setPlz(unternehmerDTO.getPlz());
        return unternehmer;
    }
}