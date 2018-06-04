package org.bonn.se.ss18.controller;


import com.vaadin.server.VaadinSession;
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
public class LoggingController {

    /*
     *   LOGIN via Linuxid
     *
     */
    public void login(String usernamme, String password) {
        ConnectionFactory dao;
        UserDAO uDAO = null;
        StudentDAO sDAO = null;
        try {
            dao = ConnectionFactory.getInstance();
            // DAO von User (EMAIL) oder von Student(LINUXID)
            uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int id = 0;

        if (sDAO.read(usernamme) != null) {
            id = sDAO.read(usernamme).getiD();
        } else if (uDAO.readbyString("email", usernamme) != null) {
            id = uDAO.readbyString("email", usernamme).getiD();
        }

        VaadinSession session = UI.getCurrent().getSession();

        session.setAttribute(Roles.CURRENT_USER, uDAO.readbyId(id));

        if ((uDAO.readbyId(id).getPasswort().equals(password))) {
            if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Student) {
                UI.getCurrent().getNavigator().navigateTo(ProfilStudent.getName());
            } else if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Unternehmer) {
                UI.getCurrent().getNavigator().navigateTo(ProfilUnternehmen.getName());
            }
        }

    }

    public void logout() {
        //  Umleiten auf login seite und Session closen.
        UI.getCurrent().getNavigator().navigateTo(Login.getName());
        UI.getCurrent().getSession().close();
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
//        try {
//            dao = ConnectionFactory.getInstance();
//
//            if (user instanceof Student) {
//                StudentDAO sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
//                return sDAO.readbyId(user.getiD()).getNachname();
//            } else if (user instanceof Unternehmer) {
//                UnternehmerDAO uDAO = (UnternehmerDAO) dao.getDAO(Tables.table_unternehmen);
//                return uDAO.readbyId(user.getiD()).getFirmenname();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;

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

