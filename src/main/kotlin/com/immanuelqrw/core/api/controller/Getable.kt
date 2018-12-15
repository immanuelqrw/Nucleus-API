package com.immanuelqrw.core.api.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Methods which allow GET on endpoint
 *
 * @property T Type of entity
 */
interface Getable<T> {
    /**
     * Gets Entity with [id]
     *
     * @param id ID of entity to get
     * @return Retrieved entity
     */
    fun find(id: Long): T

    /**
     * Get all entities
     *
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to retrieve
     * @return [Page] of entities
     */
    fun findAll(page: Pageable, search: String?): Page<T>
}