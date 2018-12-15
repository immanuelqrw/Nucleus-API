package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.test.controller.BaseControllerIT
import com.immanuelqrw.nucleus.core.api.test.example.Solid
import org.junit.jupiter.api.TestInstance

/**
 * Integration tests for SolidController
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidControllerIT : BaseControllerIT<Solid>()