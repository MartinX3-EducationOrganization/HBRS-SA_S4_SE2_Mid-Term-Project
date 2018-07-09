package org.bonn.se.ss18.controller;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import org.bonn.se.ss18.dao.*;
import org.bonn.se.ss18.service.Tables;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class ConnectionFactory {
    public static GenericDAO getDAO(Tables tables) throws SQLException {
        String url = null;
        String username = null;
        String password = null;

        YAMLParser parser;
        try {
            if (VaadinService.getCurrent() == null) {
                parser = new YAMLFactory().createParser(Paths.get("src", "main", "resources", "properties.yaml").toFile());
            } else {
                parser = new YAMLFactory().createParser(Paths.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath(), "WEB-INF", "classes", "properties.yaml").toFile());
            }
            while (parser.nextToken() != null) {
                if ("VALUE_STRING".equals(parser.getCurrentToken().toString())) {
                    switch (parser.getCurrentName()) {
                        case "url": {
                            url = parser.getText();
                            break;
                        }
                        case "username": {
                            username = parser.getText();
                            break;
                        }
                        case "password": {
                            password = parser.getText();
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            Notification.show("/WEB-INF/classes/properties.yaml konnte nicht gelesen werden!\n" + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
        switch (tables) {
            case table_user:
                return new UserDAO(connection);
            case table_student:
                return new StudentDAO(connection);
            case table_unternehmen:
                return new UnternehmerDAO(connection);
            case table_qualifikation:
                return new QualifikationDAO(connection);
            case table_dokument:
                return new DokumentDAO(connection);
            case table_branche:
                return new BrancheDAO(connection);
            case table_bewerbunganlage:
                return new BewerbunganlageDAO(connection);
            case table_bewerbung:
                return new BewerbungDAO(connection);
            case table_anzeige:
                return new AnzeigeDAO(connection);
            default:
                throw new SQLException("Trying to link to an unexistant table.");
        }
    }
}