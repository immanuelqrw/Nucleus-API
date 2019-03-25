package com.immanuelqrw.core.api.test.unit.service

import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.SearchService
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchService
 */
abstract class SearchServiceTest<T : BaseEntity> : Testable {

    protected abstract val searchService: SearchService<T>

    protected abstract val validSearch: String?
    protected abstract val invalidSearch: String?
    protected abstract val nullSearch: String?

    @BeforeAll
    override fun prepare() {
        // Subclass implementation
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }

    @Nested
    inner class Success {
        @Test
        fun `given valid search - when Specification generated - returns Specification`() {
            val validSpecification = searchService.generateSpecification(validSearch)

            validSpecification.shouldNotBeNull()
        }

        @Test
        fun `given invalid search - when Specification generated - returns null`() {
            val invalidSpecification = searchService.generateSpecification(invalidSearch)

            invalidSpecification.shouldBeNull()
        }

        @Test
        fun `given null search - when Specification generated - returns null`() {
            val nullSpecification = searchService.generateSpecification(nullSearch)

            nullSpecification.shouldBeNull()
        }
    }

    // TODO Find out if there are any possible cases of failure
    @Nested
    inner class Failure
}