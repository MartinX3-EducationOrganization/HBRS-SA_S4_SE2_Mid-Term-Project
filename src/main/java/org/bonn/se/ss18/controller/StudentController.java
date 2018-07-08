/*
 * Created by Martin Dünkelmann on 05.07.18 16:28
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 05.07.18 16:28
 */

package org.bonn.se.ss18.controller;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.StudentDAO;
import org.bonn.se.ss18.dao.UserDAO;
import org.bonn.se.ss18.dto.StudentDTO;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;

/**
 * @author martin on 05.07.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class StudentController {
    public boolean removeProfil(int id) {
        try (UserDAO userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user)) {
            try (StudentDAO studentDAO = (StudentDAO) ConnectionFactory.getDAO(Tables.table_student)) {
                return studentDAO.deleteByUserID(id, userDAO) && userDAO.delete(id);
            }
        } catch (SQLException e) {
            Notification.show("Keine Verbindung zur Datenbank!\n" + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean updateProfil(StudentDTO studentDTO) {
        try (UserDAO userDAO = (UserDAO) ConnectionFactory.getDAO(Tables.table_user)) {
            try (StudentDAO studentDAO = (StudentDAO) ConnectionFactory.getDAO(Tables.table_student)) {
                return userDAO.update(new Student(studentDTO)) && studentDAO.update(new Student(studentDTO));
            }
        } catch (SQLException e) {
            Notification.show("Keine Verbindung zur Datenbank!\n" + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }
}
