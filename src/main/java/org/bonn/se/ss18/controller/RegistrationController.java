/*
 * Created by Martin Dünkelmann on 23.06.18 11:59
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.06.18 11:59
 */

package org.bonn.se.ss18.controller;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;

/**
 * @author martin on 23.06.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class RegistrationController {
    public boolean registration(UnternehmerDTO unternehmerDTO) {
        try (UserDAO userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user)) {
            try (UnternehmerDAO unternehmerDAO = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen)) {
                if (userDAO.getByColumnValue("email", unternehmerDTO.getEmail()) != null) {
                    return false;
                }

                userDAO.create(new User(unternehmerDTO));
                User neu = userDAO.getByColumnValue("email", unternehmerDTO.getEmail());
                unternehmerDTO.setId(neu.getId());
                unternehmerDAO.create(new Unternehmer(unternehmerDTO));

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }
    }
}
