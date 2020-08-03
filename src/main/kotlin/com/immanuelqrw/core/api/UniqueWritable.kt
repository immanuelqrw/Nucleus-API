package com.immanuelqrw.core.api

import com.immanuelqrw.core.entity.UniqueEntityable

/**
 * Interface which supports all CU actions for unique id entities
 */
interface UniqueWritable<T : UniqueEntityable> : Postable<T>, UniquePutable<T>, UniquePatchable<T>
