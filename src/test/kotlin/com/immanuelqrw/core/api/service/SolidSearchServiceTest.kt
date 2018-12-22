package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidSearchService
import com.immanuelqrw.core.api.test.example.SolidSearchSpecificationsBuilder
import com.immanuelqrw.core.api.test.unit.service.SearchServiceTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SearchService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchServiceTest : SearchServiceTest<Solid>() {

    @InjectMocks
    override lateinit var searchService: SolidSearchService

    @MockBean
    override lateinit var searchSpecificationsBuilder: SolidSearchSpecificationsBuilder
}