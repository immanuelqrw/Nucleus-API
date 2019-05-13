package com.immanuelqrw.core.api.test

/**
 * Interface specifying methods required for tests
 */
interface Testable {

    /**
     * Method to prepare data before test class
     */
    fun prepare()

    /**
     * Method to prepare data before test
     */
    fun setUp()

}
