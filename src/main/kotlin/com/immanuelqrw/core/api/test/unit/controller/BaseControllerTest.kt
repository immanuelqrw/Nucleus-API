package com.immanuelqrw.core.api.test.unit.controller

import com.immanuelqrw.core.api.controller.BaseController
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.core.api.test.Testable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Pageable

/**
 * Unit tests for Controller
 */
abstract class BaseControllerTest<T : BaseEntity> : Testable {

    protected abstract val controller: BaseController<T>

    protected abstract val service: BaseService<T>

    // TODO Setup input data for test use cases

    protected abstract val validId: Long
    protected abstract val invalidId: Long
    protected abstract val validEntity: T
    protected abstract val invalidEntity: T
    protected abstract val validPage: Pageable
    protected abstract val invalidPage: Pageable
    protected abstract val validSearch: String
    protected abstract val invalidSearch: String

    @BeforeAll
    override fun prepare() {}

    @BeforeEach
    override fun setUp() {}

    @Nested
    inner class Success {
        @Test
        fun `given valid id - when GET entity - returns entity`() {
            assert(false)
        }

        @Test
        fun `given valid page, sort, and search parameters - when GET entities - returns entities`() {
            assert(false)
        }

        @Test
        fun `given valid entity - when POST entity - persists entity`() {
            assert(false)
        }

        @Test
        fun `given valid id and entity to replace - when PUT entity - replaces entity`() {
            assert(false)
        }

        @Test
        fun `given valid id and fields to replace - when PATCH entity - modifies entity`() {
            assert(false)
        }

        @Test
        fun `given valid id - when DELETE entity - sets entity removedOn to now`() {
            assert(false)
        }

        @Test
        fun `given valid page, sort, and search parameters - when DELETE entities - sets entities' removedOn to now`() {
            assert(false)
        }
    }

    @Nested
    inner class Failure {
        @Nested
        inner class InvalidUriPath {
            @Test
            fun `given invalid id - when GET entity - returns NotFound response`() {
                assert(false)
            }

            @Test
            fun `given invalid id - when PUT entity - returns NotFound response`() {
                assert(false)
            }

            @Test
            fun `given invalid id - when PATCH entity - returns NotFound response`() {
                assert(false)
            }

            @Test
            fun `given invalid id - when DELETE entity - returns NotFound response`() {
                assert(false)
            }
        }

        @Nested
        inner class InvalidBody {
            @Test
            fun `given invalid entity - when POST entity - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid entity - when PUT entity - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid partial entity - when PATCH entity - returns BadRequest response`() {
                assert(false)
            }
        }

        @Nested
        inner class InvalidQueryParam {
            @Test
            fun `given invalid page parameter - when GET entities - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid sort parameter - when GET entities - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid search parameters - when GET entities - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid page parameter - when DELETE entities - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid sort parameter - when DELETE entities - returns BadRequest response`() {
                assert(false)
            }

            @Test
            fun `given invalid search parameters - when DELETE entities - returns BadRequest response`() {
                assert(false)
            }
        }
    }
}