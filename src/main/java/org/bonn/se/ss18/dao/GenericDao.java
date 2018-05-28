package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.AbstractEntity;

/**
 * @author rjourd2s
 */
public interface GenericDao<T extends AbstractEntity> {
    boolean create(T t);

    T read(int id);

    boolean update(T t);

    boolean delete(T t);
}
