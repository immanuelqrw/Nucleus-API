package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.unit.service.SearchServiceTest
import org.junit.jupiter.api.*

/**
 * Unit tests for SearchService
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchServiceTest : SearchServiceTest<Solid>()