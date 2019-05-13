package com.immanuelqrw.core.api

/**
 * Methods which allow PUT on endpoint
 *
 * @property T Type of entity
 */
interface Putable<T> {
    /**
     * Replaces entity with [id]
     *
     * @param id ID of entity to replace
     * @param entity Entity
     * @return Replaced entity
     */
    fun replace(id: Long, entity: T) : T
}
