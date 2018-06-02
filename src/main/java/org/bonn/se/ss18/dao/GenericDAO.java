package org.bonn.se.ss18.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author rjourd2s
 */
/*
CRUD: Create Read Update Delete
 */

public abstract class GenericDAO<T> {

    //  SELECT FROM
    public abstract T readbyId(int id) throws SQLException;

    // SELECT FROM List all
    public abstract Set<T> getAllbyId(int id) throws SQLException;

    //  INSERT INTO
    public abstract boolean create(T user) throws SQLException;

    //  UPDATE ..SET
    public abstract boolean update(T user) throws SQLException;

    //  DELETE FROM
    public abstract boolean delete(T user) throws SQLException;


    //Protected
    protected final String tableName;
    protected Connection con;

    public GenericDAO(Connection con, String tableName) {
        this.tableName = tableName;
        this.con = con;
    }

}

//public interface GenericDao<T extends AbstractEntity> {
//    boolean create(T t);
//
//    T read(int id);
//
//    boolean update(T t);
//
//    boolean delete(T t);
//}
