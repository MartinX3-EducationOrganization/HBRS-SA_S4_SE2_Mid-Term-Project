package org.bonn.se.ss18.controller;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.BrancheDAO;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author rjourd2s
 */
public class TableController {
    public HashMap<Integer, String> brancheTable() {
        ConnectionFactory dao = null;
        try {
            dao = ConnectionFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BrancheDAO bDAO = null;
        try {
            bDAO = (BrancheDAO) dao.getDAO(Tables.table_branche);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
        }
        return bDAO.getTable();
    }
}
