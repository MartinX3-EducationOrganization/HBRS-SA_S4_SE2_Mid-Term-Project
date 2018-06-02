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

import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class LoggingControl {

    /*
     *   LOGIN via Linuxid
     *
     */
    public void login(String usernamme, String password) {
        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();
            // DAO von User (EMAIL) oder von Student(LINUXID)
            UserDAO uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            StudentDAO sDAO = (StudentDAO) dao.getDAO(Tables.table_student);

            int id;

            if (sDAO.read(usernamme).getiD() != 0) {
                id = sDAO.read(usernamme).getiD();
            } else if (uDAO.readbyString("email", usernamme).getiD() != 0) {
                id = uDAO.readbyString("email", usernamme).getiD();
            } else {
                id = 0;
            }

            VaadinSession session = UI.getCurrent().getSession();

            session.setAttribute(Roles.CURREN_USER, uDAO.readbyId(id));

            if ((uDAO.readbyId(id).getPasswort().equals(password))) {
                UI.getCurrent().getNavigator().navigateTo("MenueView");
            }

            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        //  Umleiten auf login seite und Session closen.
        UI.getCurrent().getNavigator().navigateTo("");
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
                {
                    Unternehmer b = uDAO.readbyId(user.getiD());
                    return b.getFirmenname();
                }
            }
            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


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
    }

    public String isStudenorUnternehmer(User user) {
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

