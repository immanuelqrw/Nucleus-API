package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidController
import com.immanuelqrw.core.api.test.example.SolidService
import com.immanuelqrw.core.api.test.unit.controller.BaseControllerTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.stereotype.Controller
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SolidController
// */
@ExtendWith(SpringExtension::class)
@WebMvcTest(Controller::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidControllerTest : BaseControllerTest<Solid>() {

    @InjectMocks
    override lateinit var controller: SolidController

    @MockBean
    override lateinit var service: SolidService
}