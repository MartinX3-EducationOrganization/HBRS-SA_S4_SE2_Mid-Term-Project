/*
 * Created by Martin Dünkelmann on 23.06.18 17:41
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.06.18 17:41
 */

package org.bonn.se.ss18.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author martin on 23.06.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public interface IGenericDAO<T> {
    ResultSet getRsByID(int id) throws SQLException;

    T getByID(int id) throws SQLException;

    Set<T> getAllByID(int id) throws SQLException;

    boolean create(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;
}
