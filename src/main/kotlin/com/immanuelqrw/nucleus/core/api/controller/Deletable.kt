package com.immanuelqrw.nucleus.core.api.controller

import org.springframework.data.domain.Pageable

/**
 * Methods which allow DELETE on endpoint
 *
 * @property T Type of entity
 */
interface Deletable<T> {
    /**
     * Removes Entity with [id]
     *
     * @param id ID of entity to remove
     */
    fun remove(id: Long)

    /**
     * Removes all entities
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to remove
     */
    fun removeAll(page: Pageable, search: String)
}