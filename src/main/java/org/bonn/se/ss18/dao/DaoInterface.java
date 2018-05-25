package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.User;

/**
 * @author rjourd2s
 */
public interface DaoInterface {

    // CRUD
    User getUSerbyUserID();

    boolean insertUser();

    boolean updateUser();

    boolean deleteUser();
}
