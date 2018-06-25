package org.bonn.se.ss18.dao;

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
    protected final String primaryKey;
    //Protected
    final String tableName;
    protected Connection con;

    public GenericDAO(Connection con, String tableName, String primaryKey) {
        this.con = con;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    @Override
    public ResultSet getRsByID(String id) {
        try {
            return con.createStatement().executeQuery(String.format("SELECT * FROM %s WHERE %s='%s'", tableName, primaryKey, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(T entity) {
        try {
            if (con.createStatement().executeUpdate("DELETE FROM " + tableName + " WHERE " + primaryKey + "=" + entity.getId()) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}