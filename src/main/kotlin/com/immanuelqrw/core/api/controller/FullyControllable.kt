package com.immanuelqrw.core.api.controller

/**
 * Interface which supports all CRUD actions
 */
interface FullyControllable<T> : Postable<T>, Getable<T>, Putable<T>, Patchable<T>, Deletable<T>