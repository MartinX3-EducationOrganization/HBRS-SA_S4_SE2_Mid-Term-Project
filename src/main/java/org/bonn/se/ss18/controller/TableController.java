package org.bonn.se.ss18.controller;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.BrancheDAO;
import org.bonn.se.ss18.dto.BrancheDTO;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rjourd2s
 */
public class TableController {
    public List<BrancheDTO> getBranches() {
        ConnectionFactory dao;
        BrancheDAO bDAO = null;
        List<BrancheDTO> brancheDTOList = new ArrayList<>();

        try {
            dao = ConnectionFactory.getInstance();
            bDAO = (BrancheDAO) dao.getDAO(Tables.table_branche);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
        }

        if (bDAO != null) {
            bDAO.getBranches().forEach(x -> brancheDTOList.add(new BrancheDTO(x)));
        }

        return brancheDTOList;
    }
}
