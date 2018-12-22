package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidRepository
import com.immanuelqrw.core.api.test.example.SolidSearchService
import com.immanuelqrw.core.api.test.example.SolidService
import com.immanuelqrw.core.api.test.unit.service.BaseServiceTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SolidService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidServiceTest : BaseServiceTest<Solid>() {

    @Autowired
    override lateinit var service: SolidService

    @MockBean
    override lateinit var repository: SolidRepository

    @MockBean
    override lateinit var searchService: SolidSearchService
}