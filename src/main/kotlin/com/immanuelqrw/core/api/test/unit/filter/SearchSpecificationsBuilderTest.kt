package com.immanuelqrw.core.api.test.unit.filter

import com.immanuelqrw.core.api.filter.SearchCriterion
import com.immanuelqrw.core.api.filter.SearchSpecification
import com.immanuelqrw.core.api.filter.SearchSpecificationsBuilder
import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*
import org.mockito.Mockito.`when`

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationsBuilderTest<T : BaseEntity> : Testable {

    protected abstract val searchSpecificationsBuilder: SearchSpecificationsBuilder<T>

    protected abstract val searchSpecification: SearchSpecification<T>
    protected abstract val searchSpecifications: List<SearchSpecification<T>>

    protected abstract val params: MutableList<SearchCriterion>

    protected abstract val validSearchCriterion: SearchCriterion
    protected abstract val invalidSearchCriterion: SearchCriterion

    @BeforeAll
    override fun prepare() {
        // Subclass implementation
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }

    @Nested
    inner class Success {
        @Test
        fun `given valid search parameters - when Specification built - returns Specification`() {
            `when`(params.map<SearchCriterion, Any>{}).thenReturn(searchSpecifications)
            `when`(searchSpecifications.reduce<Any, Any>{ _, _ ->}).thenReturn(searchSpecification)

            searchSpecificationsBuilder.build() shouldEqual searchSpecification
        }

        @Test
        fun `given no search parameters - when Specification built - returns null`() {
            `when`(searchSpecifications.reduce<Any, Any>{ _, _ ->}).thenReturn(null)

            searchSpecificationsBuilder.build() shouldEqual null
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `given invalid search parameters - when Specification built - throws RuntimeException`() {
            `when`(params.map<SearchCriterion, Any>{}).thenThrow(RuntimeException::class.java)

            Assertions.assertThrows(RuntimeException::class.java) {
                searchSpecificationsBuilder.build()
            }
        }
    }
}