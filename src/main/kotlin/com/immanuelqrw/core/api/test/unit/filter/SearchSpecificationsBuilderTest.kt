package com.immanuelqrw.core.api.test.unit.filter

import com.immanuelqrw.core.api.filter.SearchCriterion
import com.immanuelqrw.core.api.filter.SearchSpecification
import com.immanuelqrw.core.api.filter.SearchSpecificationsBuilder
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.test.Testable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationsBuilderTest<T : BaseEntity> : Testable {

    protected abstract val searchSpecificationsBuilder: SearchSpecificationsBuilder<T>

    protected abstract val searchSpecification: SearchSpecification<T>
    protected abstract val searchSpecifications: List<SearchSpecification<T>>
    protected abstract val groupedSpecification: SearchSpecification<T>

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

    // ! Modify class or tests to actually use mocks

    @Nested
    inner class Success {
        @Test
        fun `given valid search parameters - when Specification built - returns Specification`() {
//            whenever(params.isEmpty()).thenReturn(false)
//            whenever(SearchSpecification<T>(validSearchCriterion)).thenReturn(searchSpecification)
//            whenever(searchSpecification.and(searchSpecification)).thenReturn(groupedSpecification)
//
//            searchSpecificationsBuilder.build() shouldEqual searchSpecification
            assert(true)
        }

        @Test
        fun `given no search parameters - when Specification built - returns null`() {
//            whenever(searchSpecifications.reduce<Any, Any>{ _, _ ->}).thenReturn(null)
//
//            searchSpecificationsBuilder.build() shouldEqual null
            assert(true)
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `given invalid search parameters - when Specification built - throws RuntimeException`() {
//            whenever(params.map<SearchCriterion, Any>{}).thenThrow(RuntimeException::class.java)
//
//            Assertions.assertThrows(RuntimeException::class.java) {
//                searchSpecificationsBuilder.build()
//            }
            assert(true)
        }
    }

}
