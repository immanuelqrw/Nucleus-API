package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.test.integration.controller.BaseControllerIntegrationTest
import com.immanuelqrw.core.api.test.example.Solid
import org.junit.jupiter.api.TestInstance

/**
 * Integration tests for SolidController
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidControllerIntegrationTest : BaseControllerIntegrationTest<Solid>()