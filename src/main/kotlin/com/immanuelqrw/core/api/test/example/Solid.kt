package com.immanuelqrw.core.api.test.example

import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.utility.DateTimeFormatter
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Solid entity class
 *
 * @property id Unique identifier for entity
 * @property createdOn When entity's entry was created
 * @property modifiedOn When entity's entry was last modified
 * @property removedOn When entity's entry was removed
 */
@Entity
@Table(name = "`Solid`")
class Solid : BaseEntity()