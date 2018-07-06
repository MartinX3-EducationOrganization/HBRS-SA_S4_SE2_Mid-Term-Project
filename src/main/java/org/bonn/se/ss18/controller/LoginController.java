package org.bonn.se.ss18.controller;


import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.bonn.se.ss18.dao.StudentDAO;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.dto.StudentDTO;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.exception.NoSuchUserOrPasswort;
import org.bonn.se.ss18.service.Roles;
import org.bonn.se.ss18.service.Tables;
import org.bonn.se.ss18.view.LoginView;
import org.bonn.se.ss18.view.ProfilStudent;
import org.bonn.se.ss18.view.ProfilUnternehmen;

import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class LoginController {

    public boolean login(String username, String password) throws NoSuchUserOrPasswort {
        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        UserDAO uDAO;
        StudentDAO sDAO;
        UnternehmerDAO untDAO;
        try {
            uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
            untDAO = (UnternehmerDAO) dao.getDAO(Tables.table_unternehmen);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }

        int id;
        Student student = sDAO.getByUserAndPass(username, password);
        if (student != null) {
            UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, new StudentDTO(student));
        } else if (uDAO.getByColumnValue("email", username) != null) {
            id = uDAO.getByColumnValue("email", username).getId();
            student = sDAO.getByID(id);
            if (student != null) {
                if (student.getPasswort().equals(password)) {
                    UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, new StudentDTO(student));
                } else {
                    throw new NoSuchUserOrPasswort();
                }
            } else {
                Unternehmer unternehmer = untDAO.getByID(id);
                if (unternehmer != null && unternehmer.getPasswort().equals(password)) {
                    UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, new UnternehmerDTO(unternehmer));
                } else {
                    throw new NoSuchUserOrPasswort();
                }
            }
        } else {
            throw new NoSuchUserOrPasswort();
        }

        if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof StudentDTO) {
            UI.getCurrent().getNavigator().navigateTo(ProfilStudent.getName());
            return true;
        } else if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof UnternehmerDTO) {
            UI.getCurrent().getNavigator().navigateTo(ProfilUnternehmen.getName());
            return true;
        }
        return false;
    }

    public void logout() {
        UI.getCurrent().getNavigator().navigateTo(LoginView.getName());
        UI.getCurrent().getSession().close();
        Page.getCurrent().reload();
    }
}

