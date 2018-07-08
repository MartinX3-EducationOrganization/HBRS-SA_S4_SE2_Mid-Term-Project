package org.bonn.se.ss18.dao;

import com.vaadin.ui.Notification;
import org.bonn.se.ss18.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author rjourd2s
 */
/*
CRUD: Create Read Update Delete
 */

public abstract class GenericDAO<T extends AbstractEntity> implements IGenericDAO<T> {
    final String primaryKey;
    final String tableName;
    protected Connection con;
    public GenericDAO(Connection con, String tableName, String primaryKey) {
        this.con = con;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    @Override
    public void close() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public ResultSet getRsByID(String id) {
        try {
            return con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE %s='%s'", tableName, primaryKey, id));
        } catch (SQLException e) {
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public boolean delete(T entity) {
        try {
            if (con.createStatement().executeUpdate(String.format("DELETE FROM %s WHERE %s=%d", tableName, primaryKey, entity.getId())) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return false;
    }
}