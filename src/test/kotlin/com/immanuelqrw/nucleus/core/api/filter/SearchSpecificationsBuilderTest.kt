package com.immanuelqrw.nucleus.core.api.filter

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationsBuilderTest<T : BaseEntity> {

    // TODO Setup input data for test use cases
    @BeforeEach
    fun setUp() {}

    @Nested
    inner class Success {
        @Test
        fun `given valid search parameters - when Specification built - returns Specification`() {
            assert(false)
        }

        @Test
        fun `given no search parameters - when Specification built - returns null`() {
            assert(false)
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `given invalid search parameters - when Specification built - throws RuntimeException`() {
            assert(false)
        }
    }
}