package com.immanuelqrw.core.api.model

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
abstract class BaseEntity : Entityable {
    abstract override var id: Long?
    abstract override var createdOn: LocalDateTime
    abstract override var modifiedOn: LocalDateTime
    abstract override var removedOn: LocalDateTime?
}