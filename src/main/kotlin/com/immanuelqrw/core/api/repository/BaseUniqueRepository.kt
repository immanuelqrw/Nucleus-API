package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.entity.UniqueEntityable
import java.util.*

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseUniqueRepository<T : UniqueEntityable> : BaseRepository<T, UUID>
