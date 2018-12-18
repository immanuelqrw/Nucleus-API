package com.immanuelqrw.core.api

/**
 * Interface which supports all CU actions
 */
interface Writable<T> : Postable<T>, Putable<T>,
    Patchable<T>