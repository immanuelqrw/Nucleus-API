package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.UniqueEntityable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

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
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to retrieve
     * @return [Page] of entities
     */
    fun findAll(page: Pageable, search: String?): Page<T>
}
