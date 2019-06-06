package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.SerialEntityable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Methods which allow GET on endpoint for serial entities
 *
 * @property T Type of entity
 */
interface SerialGetable<T : SerialEntityable> {
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
     * @return All [T] entities
     */
    fun findAll(): List<T>

    /**
     * Get all entities by input id
     *
     * @return All [T] entities
     */
    fun findAllById(ids: Iterable<Long>): List<T>

    /**
     * Get all filtered entities
     *
     * @param search Used for specifying entities to retrieve
     * @return [List] of filtered entities
     */
    fun findAll(search: String): List<T>

    /**
     * Get all filtered entities
     *
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to retrieve
     * @return [Page] of filtered entities
     */
    fun findAll(page: Pageable, search: String?): Page<T>
}
