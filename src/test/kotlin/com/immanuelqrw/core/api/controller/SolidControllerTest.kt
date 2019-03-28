package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidController
import com.immanuelqrw.core.api.test.example.SolidService
import com.immanuelqrw.core.api.test.unit.controller.BaseControllerTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

/**
 * Unit tests for SolidController
// */
@ExtendWith(SpringExtension::class)
@WebMvcTest(Controller::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidControllerTest : BaseControllerTest<Solid>() {

    @Autowired
    override lateinit var mvc: MockMvc

    @InjectMocks
    override lateinit var controller: SolidController

    @MockBean
    override lateinit var service: SolidService

    override val validId: Long = 1
    override val invalidId: Long = -1

    @Mock
    override lateinit var validEntity: Solid

    @Mock
    override lateinit var invalidEntity: Solid

    @Mock
    override lateinit var replacedEntity: Solid

    @Mock
    override lateinit var validPatchedFields: Map<String, Any>

    @Mock
    override lateinit var invalidPatchedFields: Map<String, Any>

    @Mock
    override lateinit var validPageable: Pageable

    @Mock
    override lateinit var invalidPageable: Pageable

    @Mock
    override lateinit var validPage: Page<Solid>

    override val validSearch: String = "id:2"
    override val invalidSearch: String = "id@2"

    override val entityName: String = "solid"
}