package com.immanuelqrw.core.api

import org.springframework.data.domain.Pageable

/**
 * Methods which allow DELETE on endpoint
 *
 * @property T Type of entity
 */
interface Deletable<T> {
    /**
     * Removes Entity with [id] by setting removedOn field to a datetime
     *
     * @param id ID of entity to remove
     */
    fun remove(id: Long)

    /**
     * Removes all entities by setting removedOn field to a datetime
     *
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to remove
     */
    fun removeAll(page: Pageable, search: String?)
}