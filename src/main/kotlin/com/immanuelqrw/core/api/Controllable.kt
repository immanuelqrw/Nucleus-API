package com.immanuelqrw.core.api

/**
 * Interface which supports all standard CRUD actions
 */
interface Controllable<T> : Postable<T>, Getable<T>, Putable<T>, Deletable<T>
