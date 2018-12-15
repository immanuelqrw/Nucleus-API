package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.service.BaseServiceIT
import com.immanuelqrw.nucleus.core.api.test.example.Solid
import org.junit.jupiter.api.TestInstance

/**
 * Integration tests for SolidService
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolidServiceIT : BaseServiceIT<Solid>()