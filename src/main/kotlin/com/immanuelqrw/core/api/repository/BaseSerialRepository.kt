package com.immanuelqrw.core.api.repository

import com.immanuelqrw.core.entity.SerialEntityable

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseSerialRepository<T : SerialEntityable> : BaseRepository<T, Long>
