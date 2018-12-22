package com.immanuelqrw.core.api.test.unit.service

import com.immanuelqrw.core.api.filter.SearchSpecificationsBuilder
import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.SearchService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchService
 */
abstract class SearchServiceTest<T : BaseEntity> : Testable {

    protected abstract val searchService: SearchService<T>

    protected abstract val searchSpecificationsBuilder: SearchSpecificationsBuilder<T>

    // TODO Setup input data for test use cases

    @BeforeAll
    override fun prepare() {}

    @BeforeEach
    override fun setUp() {}

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