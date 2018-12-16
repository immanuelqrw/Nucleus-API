package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.service.BaseServiceIntegrationTest
import com.immanuelqrw.core.api.test.example.Solid
import org.junit.jupiter.api.TestInstance

/**
 * Integration tests for SolidService
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolidServiceIntegrationTest : BaseServiceIntegrationTest<Solid>()