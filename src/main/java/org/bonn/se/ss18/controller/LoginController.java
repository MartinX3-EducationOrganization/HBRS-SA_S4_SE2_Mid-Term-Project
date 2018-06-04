package org.bonn.se.ss18.controller;


import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import org.bonn.se.ss18.dao.StudentDAO;
import org.bonn.se.ss18.dao.UnternehmerDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.entity.User;
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
    public void login(String usernamme, String password) {
        ConnectionFactory dao;
        UserDAO uDAO = null;
        StudentDAO sDAO = null;
        UnternehmerDAO untDAO = null;
        try {
            dao = ConnectionFactory.getInstance();
            // DAO von User (EMAIL) oder von Student(LINUXID)
            uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
            untDAO = (UnternehmerDAO) dao.getDAO(Tables.table_unternehmen);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int id = 0;


        if (sDAO.read(usernamme) != null) {
            id = sDAO.read(usernamme).getiD();
            UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, sDAO.read(usernamme));

        } else if (uDAO.readbyString("email", usernamme) != null) {
            id = uDAO.readbyString("email", usernamme).getiD();
            if (sDAO.readbyId(id) != null) {
                UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, sDAO.readbyId(id));
            } else if (untDAO.readbyId(id) != null) {
                UI.getCurrent().getSession().setAttribute(Roles.CURRENT_USER, untDAO.readbyId(id));
            }
        }


        if ((uDAO.readbyId(id).getPasswort().equals(password))) {
            if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Student) {
                UI.getCurrent().getNavigator().navigateTo(ProfilStudent.getName());
            } else if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Unternehmer) {
                UI.getCurrent().getNavigator().navigateTo(ProfilUnternehmen.getName());
            }
        }
    }

    public void logout() {
        UI.getCurrent().getNavigator().navigateTo(Login.getName());
        UI.getCurrent().getSession().close();
        Page.getCurrent().reload();
    }

    public String getNameUser(User user) {
        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();

            StudentDAO sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
            UnternehmerDAO uDAO = (UnternehmerDAO) dao.getDAO(Tables.table_unternehmen);

            if (sDAO.readbyId(user.getiD()) != null) {
                Student a = sDAO.readbyId(user.getiD());
                return a.getNachname();
            } else if (uDAO.readbyId(user.getiD()) != null) {
                Unternehmer b = uDAO.readbyId(user.getiD());
                return b.getFirmenname();
            }
            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String isStudendOrUnternehmer(User user) {
        if (user == null) {
            return "";
        }

        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();

            StudentDAO sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
            //Falls mal mehr Unterklassen von User dazu kommmen.
            //UnternehmerDAO uDAO = (UnternehmerDAO) dao.getDAO(Tables.table_unternehmen);

            //TODO NULLPOINTER
            if (sDAO.readbyId(user.getiD()) != null) {
                dao.close();
                return "Student";
            } else {
                return "Unternehmer";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     *   SIGNUP TODO
     */
}

