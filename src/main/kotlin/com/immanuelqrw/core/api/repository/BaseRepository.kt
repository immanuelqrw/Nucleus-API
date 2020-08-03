package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.entity.Entityable
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseRepository<T : Entityable, V: Any> : JpaRepositoryImplementation<T, V>
