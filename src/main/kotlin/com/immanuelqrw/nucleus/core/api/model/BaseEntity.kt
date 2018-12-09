package com.immanuelqrw.nucleus.core.api.model

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

/**
 * Abstract class for the base of entities
 *
 * @property id Unique identifier for entity
 * @property createdOn When entity's entry was created
 * @property modifiedOn When entity's entry was last modified
 * @property removedOn When entity's entry was removed
 */
@MappedSuperclass
abstract class BaseEntity : Identifiable, Entryable {
    abstract override var id: Long
    abstract override val createdOn: LocalDateTime
    abstract override val modifiedOn: LocalDateTime
    abstract override val removedOn: LocalDateTime
}