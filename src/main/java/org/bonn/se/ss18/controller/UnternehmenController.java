/*
 * Created by Martin Dünkelmann on 05.07.18 16:30
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 05.07.18 16:30
 */

package org.bonn.se.ss18.controller;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;

/**
 * @author martin on 05.07.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class UnternehmenController {
    public boolean removeProfil(int id) {
        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        UserDAO uDAO;
        UnternehmerDAO untDAO;
        try {
            uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            untDAO = (UnternehmerDAO) dao.getDAO(Tables.table_unternehmen);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }

        return untDAO.deleteByUserID(id) && uDAO.delete(id);
    }
}
