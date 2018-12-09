package com.immanuelqrw.nucleus.core.api.repository

import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

/**
 * Base Repository class
 *
 * @property T Entity type being manipulated
 */
abstract class BaseRepository<T>(domainClass: Class<T>, em: EntityManager) : SimpleJpaRepository<T, Long>(domainClass, em)