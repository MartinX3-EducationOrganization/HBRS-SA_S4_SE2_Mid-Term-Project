package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rjourd2s
 */
/*
CRUD: Create Read Update Delete
 */

public abstract class GenericDAO<T extends AbstractEntity> implements IGenericDAO<T> {
    //Protected
    final String tableName;
    protected Connection con;

    public GenericDAO(Connection con, String tableName) {
        this.tableName = tableName;
        this.con = con;
    }

    //  DELETE FROM
    public boolean delete(T entity) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + entity.getId());
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}