package org.bonn.se.ss18.entity;

import java.util.UUID;

/**
 * @author rjourd2s
 */
public class AbstractEntity {
    private UUID id;

    public AbstractEntity() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
