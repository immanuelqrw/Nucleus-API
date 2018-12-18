package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.test.integration.controller.BaseControllerTest
import com.immanuelqrw.core.api.test.example.Solid
import org.junit.jupiter.api.TestInstance

/**
 * Unit tests for SolidController
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidControllerTest : BaseControllerTest<Solid>()