package com.immanuelqrw.nucleus.core.api.service

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchService
 */
abstract class SearchServiceTest<T : BaseEntity> {

    // TODO Setup input data for test use cases
    @BeforeEach
    fun setUp() {}


    @Nested
    inner class Success {
        @Test
        fun `given valid search - when Specification generated - returns Specification`() {
            assert(false)
        }

        @Test
        fun `given null search - when Specification generated - returns null`() {
            assert(false)
        }
    }

    // TODO Find out if there are any possible cases of failure
    @Nested
    inner class Failure
}