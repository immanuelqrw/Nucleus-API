package com.immanuelqrw.core.api.test.unit.exception

import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.test.Testable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Unit tests for ExceptionHandler
 */
abstract class BaseResponseEntityExceptionHandlerTest<T : BaseEntity> : Testable {

    // - Look into how to test ExceptionHandler

    @BeforeAll
    override fun prepare() {
        // Subclass implementation
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }

    @Test
    @DisplayName("given invalid request on entity - when EntityNotFoundException thrown - returns NotFound response")
    fun testThrownEntityNotFoundExceptionReturnsNotFound() {}

    @Test
    @DisplayName("given invalid request on entity - when RollbackException thrown - returns BadRequest response")
    fun testThrownRollbackExceptionReturnsBadRequest() {}

    @Test
    @DisplayName("given invalid request on entity - when {INSERT EXCEPTION HERE} thrown - returns Forbidden response")
    fun testThrownXXXExceptionReturnsForbidden() {}

    @Test
    @DisplayName("given invalid request on entity - when IllegalArgumentException thrown - returns Conflict response")
    fun testThrownIllegalArgumentExceptionReturnsConflict() {}

    @Test
    @DisplayName("given invalid request on entity - when IllegalStateException thrown - returns Conflict response")
    fun testThrownIllegalStateExceptionReturnsConflict() {}

}
