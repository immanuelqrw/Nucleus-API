package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.entity.BaseEntity
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseSerialRepository<T : BaseEntity> : JpaRepositoryImplementation<T, Long>
