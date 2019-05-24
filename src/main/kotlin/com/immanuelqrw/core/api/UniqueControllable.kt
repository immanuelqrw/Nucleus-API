package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.UniqueEntityable

/**
 * Interface which supports all standard CRUD actions for unique id entities
 */
interface UniqueControllable<T : UniqueEntityable> : Postable<T>, UniqueGetable<T>, UniquePutable<T>, UniqueDeletable<T>
