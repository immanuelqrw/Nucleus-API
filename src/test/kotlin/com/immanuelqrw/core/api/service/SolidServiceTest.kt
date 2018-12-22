package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.unit.service.BaseServiceTest
import org.junit.jupiter.api.TestInstance

/**
 * Unit tests for SolidService
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidServiceTest : BaseServiceTest<Solid>()