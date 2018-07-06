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
        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        UserDAO uDAO;
        StudentDAO sDAO;
        try {
            uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }

        return sDAO.deleteByUserID(id) && uDAO.delete(id);
    }

    public boolean updateProfil(StudentDTO studentDTO) {
        ConnectionFactory dao;
        try {
            dao = ConnectionFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        UserDAO uDAO;
        StudentDAO sDAO;
        try {
            uDAO = (UserDAO) dao.getDAO(Tables.table_user);
            sDAO = (StudentDAO) dao.getDAO(Tables.table_student);
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.show("Keine Verbindung zur Datenbank!", Notification.Type.ERROR_MESSAGE);
            return false;
        }

        return uDAO.update(studentDTO.toEntity()) && sDAO.update(new Student(studentDTO));
    }
}
