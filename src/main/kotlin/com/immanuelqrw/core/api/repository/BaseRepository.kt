package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.api.model.BaseEntity
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseRepository<T : BaseEntity> : JpaRepositoryImplementation<T, Long>
