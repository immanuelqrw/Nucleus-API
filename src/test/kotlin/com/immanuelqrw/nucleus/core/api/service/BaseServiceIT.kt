package com.immanuelqrw.nucleus.core.api.service

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Integration tests for Service
 */
abstract class BaseServiceIT<T : BaseEntity> {

    // TODO Setup input data for test use cases
    @BeforeEach
    fun setUp() {}

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
        fun `given valid id - when DELETE entity - removes entity`() {
            assert(false)
        }

        @Test
        fun `given valid page, sort, and search parameters - when DELETE entities - removes entities`() {
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