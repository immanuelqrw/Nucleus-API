package com.immanuelqrw.core.api.controller

/**
 * Interface which supports all CU actions
 */
interface Writable<T> : Postable<T>, Putable<T>,
    Patchable<T>