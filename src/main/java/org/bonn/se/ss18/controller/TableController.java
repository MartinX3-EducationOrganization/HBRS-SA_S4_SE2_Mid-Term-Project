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
        try (BrancheDAO brancheDAO = (BrancheDAO) ConnectionFactory.getDAO(Tables.table_branche)) {
            List<BrancheDTO> brancheDTOList = new ArrayList<>();

            brancheDAO.getBranches().forEach(x -> brancheDTOList.add(new BrancheDTO(x)));

            return brancheDTOList;
        } catch (SQLException e) {
            Notification.show("Keine Verbindung zur Datenbank!\n" + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return null;
    }
}
