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
import org.bonn.se.ss18.service.Views;

import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class LoginController {
    public boolean login(String username, String password) throws NoSuchUserOrPasswort {
        try (UserDAO userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user)) {
            try (UnternehmerDAO unternehmerDAO = (UnternehmerDAO) ConnectionFactory.getDAO(Tables.table_unternehmen)) {
                try (StudentDAO studentDAO = (StudentDAO) ConnectionFactory.getDAO(Tables.table_student)) {
                    loginStudent(username, password, userDAO, unternehmerDAO, studentDAO);
                }
            }
            return navigateToProfil();
        } catch (SQLException e) {
            Notification.show("Keine Verbindung zur Datenbank!\n" + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }

    private void loginStudent(String username, String password, UserDAO userDAO, UnternehmerDAO unternehmerDAO, StudentDAO studentDAO) throws NoSuchUserOrPasswort {
        Student student = studentDAO.getByUserAndPass(username, password, userDAO);
        if (student != null) {
            UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, new StudentDTO(student));
        } else if (userDAO.getByColumnValue("email", username) != null) {
            int id = userDAO.getByColumnValue("email", username).getId();
            student = studentDAO.getByID(id, userDAO);
            if (student != null) {
                loginStudent(password, student);
            } else {
                loginUnternehmer(password, userDAO, unternehmerDAO, id);
            }
        } else {
            throw new NoSuchUserOrPasswort();
        }
    }

    private void loginStudent(String password, Student student) throws NoSuchUserOrPasswort {
        if (student.getPasswort().equals(password)) {
            UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, new StudentDTO(student));
        } else {
            throw new NoSuchUserOrPasswort();
        }
    }

    private void loginUnternehmer(String password, UserDAO userDAO, UnternehmerDAO unternehmerDAO, int id) throws NoSuchUserOrPasswort {
        Unternehmer unternehmer = unternehmerDAO.getByID(id, userDAO);
        if (unternehmer != null && unternehmer.getPasswort().equals(password)) {
            UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, new UnternehmerDTO(unternehmer));
        } else {
            throw new NoSuchUserOrPasswort();
        }
    }

    private boolean navigateToProfil() {
        if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof StudentDTO) {
            UI.getCurrent().getNavigator().navigateTo(Views.ProfilStudent);
            return true;
        } else if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof UnternehmerDTO) {
            UI.getCurrent().getNavigator().navigateTo(Views.ProfilUnternehmen);
            return true;
        }
        return false;
    }

    public void logout() {
        UI.getCurrent().getSession().close();
        Page.getCurrent().reload();
    }
}

