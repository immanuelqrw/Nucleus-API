package com.immanuelqrw.core.api

/**
 * Methods which allow POST on endpoint
 *
 * @property T Type of entity
 */
interface Postable<T> {
    /**
     * Creates [entity]
     *
     * @param entity Entity
     * @return Created entity
     */
    fun create(entity: T): T
}
