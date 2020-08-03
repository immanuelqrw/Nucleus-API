package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.UniqueEntityable

/**
 * Interface which supports all CRUD actions for unique id entities
 */
interface FullyUniqueControllable<T : UniqueEntityable> : Postable<T>, UniqueGetable<T>, UniquePutable<T>, UniquePatchable<T>, UniqueDeletable<T>, Countable
