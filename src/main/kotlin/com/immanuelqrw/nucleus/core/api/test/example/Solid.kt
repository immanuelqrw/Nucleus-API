package com.immanuelqrw.nucleus.core.api.model

import com.immanuelqrw.nucleus.core.api.utility.DateTimeFormatter
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
internal data class Solid (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    override var id: Long?,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @CreatedDate
    @CreationTimestamp
    @Column(name = "`createdOn`", updatable = false)
    override var createdOn: LocalDateTime,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "`modifiedOn`")
    override var modifiedOn: LocalDateTime,

    @DateTimeFormat(pattern = DateTimeFormatter.DATE_TIME_PATTERN)
    @Column(name = "`removedOn`")
    override var removedOn: LocalDateTime?
) : Entityable