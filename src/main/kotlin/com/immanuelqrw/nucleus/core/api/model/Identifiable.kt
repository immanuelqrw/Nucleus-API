package com.immanuelqrw.nucleus.core.api.model

import java.time.LocalDateTime

/**
 * Interface specifying required field to be identifiable
 *
 * @property id Unique identifier for entity
 */
interface Identifiable {
    val id: Long
}