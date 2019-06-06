package com.immanuelqrw.core.api

/**
 * Methods which allow Count
 */
interface Countable {
    /**
     * Count all entities
     *
     * @return Count of all entities
     */
    fun count(): Long

    /**
     * Count all entities after filtering
     *
     * @param search Used for specifying entities to retrieve
     * @return Count of filtered entities
     */
    fun count(search: String): Long
}
