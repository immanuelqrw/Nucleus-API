package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.integration.service.BaseServiceTest
import com.immanuelqrw.core.api.test.example.Solid
import org.junit.jupiter.api.TestInstance

/**
 * Unit tests for SolidService
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolidServiceTest : BaseServiceTest<Solid>()