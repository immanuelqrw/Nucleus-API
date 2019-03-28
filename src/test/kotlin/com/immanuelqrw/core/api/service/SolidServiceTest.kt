package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidRepository
import com.immanuelqrw.core.api.test.example.SolidSearchService
import com.immanuelqrw.core.api.test.example.SolidService
import com.immanuelqrw.core.api.test.unit.service.BaseServiceTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SolidService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidServiceTest : BaseServiceTest<Solid>() {

    @InjectMocks
    override lateinit var service: SolidService

    @MockBean
    override lateinit var repository: SolidRepository

    @MockBean
    override lateinit var searchService: SolidSearchService

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

    @Mock
    override lateinit var validSearchSpecification: Specification<Solid>

    @Mock
    override lateinit var invalidSearchSpecification: Specification<Solid>
}