package com.immanuelqrw.nucleus.core.api.controller

/**
 * Methods which allow PATCH on endpoint
 *
 * @property T Type of entity
 */
interface Patchable<T> {
    /**
     * Modifies fields on entity with [id]
     *
     * @param id ID of entity to modify
     * @param patches Map of fields to change
     * @return Modified entity
     */
    fun modify(id: Long, patches: Map<String, Any>): T
}