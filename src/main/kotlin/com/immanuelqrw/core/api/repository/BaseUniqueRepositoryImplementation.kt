package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.entity.BaseEntity
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

/**
 * Base Repository implementation class
 *
 * @property T Entity type being manipulated
 */
abstract class BaseUniqueRepositoryImplementation<T : BaseEntity>(domainClass: Class<T>, entityManager: EntityManager)
    : SimpleJpaRepository<T, Long>(domainClass, entityManager), BaseRepository<T>
