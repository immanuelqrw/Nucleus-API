package com.immanuelqrw.core.api.filter

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidSearchSpecification
import com.immanuelqrw.core.api.test.example.SolidSearchSpecificationsBuilder
import com.immanuelqrw.core.api.test.unit.filter.SearchSpecificationsBuilderTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SolidSearchSpecification
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchSpecificationsBuilderTest: SearchSpecificationsBuilderTest<Solid>() {

    @InjectMocks
    override lateinit var searchSpecificationsBuilder: SolidSearchSpecificationsBuilder

    @Mock
    override lateinit var searchSpecifications: List<SolidSearchSpecification>

    @MockBean
    override lateinit var searchSpecification: SolidSearchSpecification

    @Mock
    override lateinit var groupedSpecification: SolidSearchSpecification

    @Mock
    override lateinit var params: MutableList<SearchCriterion>

    override val validSearchCriterion: SearchCriterion = SearchCriterion("id", ":", 2)
    override val invalidSearchCriterion: SearchCriterion = SearchCriterion("ig", "@", "fire")

}
