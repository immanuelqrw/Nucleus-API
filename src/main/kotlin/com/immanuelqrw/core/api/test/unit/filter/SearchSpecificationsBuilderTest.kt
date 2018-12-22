package com.immanuelqrw.core.api.filter

import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationsBuilderTest<T : BaseEntity> : Testable {

    protected abstract val searchSpecificationBuilder: SearchSpecificationsBuilder<T>

    protected abstract val searchSpecification: SearchSpecification<T>

    // TODO Setup input data for test use cases

    @BeforeAll
    override fun prepare() {}

    @BeforeEach
    override fun setUp() {}

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