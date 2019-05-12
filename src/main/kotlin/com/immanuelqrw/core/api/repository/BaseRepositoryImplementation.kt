package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.api.model.BaseEntity
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

// - Look into non-Spring DI
/**
 * Base Repository implementation class
 *
 * @property T Entity type being manipulated
 */
abstract class BaseRepositoryImplementation<T : BaseEntity>(domainClass: Class<T>, entityManager: EntityManager)
    : SimpleJpaRepository<T, Long>(domainClass, entityManager), BaseRepository<T>
