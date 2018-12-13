package com.immanuelqrw.nucleus.core.api.repository

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

/**
 * Base Repository implementatio class
 *
 * @property T Entity type being manipulated
 */
abstract class BaseRepositoryImplementation<T : BaseEntity>(domainClass: Class<T>, entityManager: EntityManager)
    : SimpleJpaRepository<T, Long>(domainClass, entityManager), BaseRepository<T>