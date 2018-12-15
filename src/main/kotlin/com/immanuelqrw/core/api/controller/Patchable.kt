package com.immanuelqrw.core.api.controller

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
     * @param patchedFields Map of fields to change
     * @return Modified entity
     */
    fun modify(id: Long, patchedFields: Map<String, Any>): T
}