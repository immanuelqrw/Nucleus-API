package com.immanuelqrw.core.api.exception

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.unit.exception.BaseResponseEntityExceptionHandlerTest
import org.junit.jupiter.api.TestInstance

/**
 * Unit tests for Solid ExceptionHandler
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolidResponseEntityExceptionHandlerTest : BaseResponseEntityExceptionHandlerTest<Solid>()