package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.api.model.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseRepository<T : BaseEntity> : JpaRepositoryImplementation<T, Long>