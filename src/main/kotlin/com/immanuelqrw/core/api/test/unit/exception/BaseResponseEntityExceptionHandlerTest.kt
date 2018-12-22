package com.immanuelqrw.core.api.test.unit.exception

import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.test.Testable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Unit tests for ExceptionHandler
 */
abstract class BaseResponseEntityExceptionHandlerTest<T : BaseEntity> : Testable {

    @BeforeAll
    override fun prepare() {}

    @BeforeEach
    override fun setUp() {}

    @Test
    fun `given invalid request on entity - when EntityNotFoundException thrown - returns NotFound response`() {}

    @Test
    fun `given invalid request on entity - when RollbackException thrown - returns BadRequest response`() {}

    @Test
    fun `given invalid request on entity - when {INSERT EXCEPTION HERE} thrown - returns Forbidden response`() {}

    @Test
    fun `given invalid request on entity - when IllegalArgumentException thrown - returns Conflict response`() {}

    @Test
    fun `given invalid request on entity - when IllegalStateException thrown - returns Conflict response`() {}
}