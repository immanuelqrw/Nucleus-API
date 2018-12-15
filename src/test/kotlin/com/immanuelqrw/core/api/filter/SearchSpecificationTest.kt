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
abstract class SearchSpecificationTest<T : BaseEntity> : Testable {

    // TODO Setup input data for test use cases

    @BeforeAll
    override fun prepare() {}

    @BeforeEach
    override fun setUp() {}

    // TODO Find out how to test Predicate
    @Nested
    inner class Success {
        @Test
        fun `given valid search criterion - when values processed - returns Predicate`() {
            assert(false)
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `given invalid search criterion - when values processed - throws RuntimeException`() {
            assert(false)
        }
    }
}