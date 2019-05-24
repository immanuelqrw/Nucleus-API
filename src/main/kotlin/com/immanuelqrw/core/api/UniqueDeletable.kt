package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.UniqueEntityable
import org.springframework.data.domain.Pageable
import java.util.*

/**
 * Methods which allow DELETE on endpoint for unique id entities
 *
 * @property T Type of entity
 */
interface UniqueDeletable<T : UniqueEntityable> {
    /**
     * Removes Entity with [id] by setting removedOn field to a datetime
     *
     * @param id ID of entity to remove
     */
    fun remove(id: UUID)

    /**
     * Removes all entities by setting removedOn field to a datetime
     *
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to remove
     */
    fun removeAll(page: Pageable, search: String?)
}
