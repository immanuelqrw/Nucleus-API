package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.UniqueEntityable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

/**
 * Methods which allow GET on endpoint for unique id entities
 *
 * @property T Type of entity
 */
interface UniqueGetable<T : UniqueEntityable> {
    /**
     * Gets Entity with [id]
     *
     * @param id ID of entity to get
     * @return Retrieved entity
     */
    fun find(id: UUID): T

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
    fun findAllById(ids: Iterable<UUID>): List<T>

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
