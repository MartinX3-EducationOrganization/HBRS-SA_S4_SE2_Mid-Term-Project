/*
 * Created by Martin Dünkelmann on 05.07.18 16:30
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 05.07.18 16:30
 */

package org.bonn.se.ss18.controller;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.AnzeigeDAO;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Anzeige;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author martin on 05.07.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class UnternehmenController {
    public boolean removeProfil(int id) {
        try (UserDAO userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user)) {
            try (UnternehmerDAO unternehmerDAO = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen)) {
                return unternehmerDAO.deleteByUserID(id, userDAO) && userDAO.delete(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean updateProfil(UnternehmerDTO unternehmerDTO) {
        try (UserDAO userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user)) {
            try (UnternehmerDAO unternehmerDAO = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen)) {
                return userDAO.update(new Unternehmer(unternehmerDTO)) && unternehmerDAO.update(new Unternehmer(unternehmerDTO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }
    }

    public void updateAnzeige(Anzeige documentData) {
        try (AnzeigeDAO anzeigeDAO = (AnzeigeDAO) ConnectionFactory.getDAO(Tables.table_anzeige)) {
            anzeigeDAO.update(documentData);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
        }
    }

    public Set<Anzeige> getAllAnzeigenByID(int id) {
        try (AnzeigeDAO anzeigeDAO = (AnzeigeDAO) ConnectionFactory.getDAO(Tables.table_anzeige)) {
            return anzeigeDAO.getAllByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return null;
        }
    }
}
