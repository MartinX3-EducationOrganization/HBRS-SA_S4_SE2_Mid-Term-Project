package org.bonn.se.ss18.controller;


import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.bonn.se.ss18.dao.StudentDAO;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.exception.NoSuchUserOrPasswort;
import org.bonn.se.ss18.service.Roles;
import org.bonn.se.ss18.service.Tables;
import org.bonn.se.ss18.view.Login;
import org.bonn.se.ss18.view.ProfilStudent;
import org.bonn.se.ss18.view.ProfilUnternehmen;

import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class LoginController {

    /*
     *   LOGIN via Linuxid
     *
     */
    public boolean login(String usernamme, String password) throws NoSuchUserOrPasswort {
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

        int id = 0;


        if (sDAO.read(usernamme) != null)

        {
            id = sDAO.read(usernamme).getId();
            UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, sDAO.read(usernamme));

        } else if (uDAO.readbyString("email", usernamme) != null)

        {
            id = uDAO.readbyString("email", usernamme).getId();
            if (sDAO.readbyId(id) != null) {
                UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, sDAO.readbyId(id));
            } else if (untDAO.readbyId(id) != null) {
                UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, untDAO.readbyId(id));
            } else {
                throw new NoSuchUserOrPasswort();
            }
        } else {
            throw new NoSuchUserOrPasswort();
        }


        if ((uDAO.readbyId(id).

                getPasswort().

                equals(password)))

        {
            if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Student) {
                UI.getCurrent().getNavigator().navigateTo(ProfilStudent.getName());
                return true;
            } else if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Unternehmer) {
                UI.getCurrent().getNavigator().navigateTo(ProfilUnternehmen.getName());
                return true;
            }
        } else

        {
            throw new NoSuchUserOrPasswort();
        }
        return false;
    }

    public void logout() {
        UI.getCurrent().getNavigator().navigateTo(Login.getName());
        UI.getCurrent().getSession().close();
        Page.getCurrent().reload();
    }
}

