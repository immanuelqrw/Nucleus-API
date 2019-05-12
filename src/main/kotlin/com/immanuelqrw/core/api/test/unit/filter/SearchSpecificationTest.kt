package com.immanuelqrw.core.api.test.unit.filter

import com.immanuelqrw.core.api.filter.SearchCriterion
import com.immanuelqrw.core.api.filter.SearchSpecification
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.test.Testable
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationTest<T : BaseEntity> : Testable {

    protected abstract val root: Root<T>
    protected abstract val query: CriteriaQuery<*>
    protected abstract val builder: CriteriaBuilder

    protected abstract val key: Path<String>

    protected abstract val validPredicate: Predicate

    protected abstract val validGreaterThanSearchCriterion: SearchCriterion
    protected abstract val validLessThanSearchCriterion: SearchCriterion
    protected abstract val validEqualitySearchCriterion: SearchCriterion
    protected abstract val validLikeSearchCriterion: SearchCriterion

    protected abstract val invalidKeySearchCriterion: SearchCriterion
    protected abstract val invalidOperationSearchCriterion: SearchCriterion

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
        @DisplayName("given valid greater than search criterion - when values processed - returns Predicate")
        fun testProcessValidGreaterThanSearchCriterion() {
            whenever(root.get<String>(validGreaterThanSearchCriterion.key)).thenReturn(key)
            whenever(builder.greaterThanOrEqualTo(key, validGreaterThanSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validGreaterThanSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid less than search criterion - when values processed - returns Predicate")
        fun testProcessValidLessThanSearchCriterion() {
            whenever(root.get<String>(validLessThanSearchCriterion.key)).thenReturn(key)
            whenever(builder.lessThanOrEqualTo(key, validLessThanSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validLessThanSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid equality search criterion - when values processed - returns Predicate")
        fun testProcessValidEqualitySearchCriterion() {
            whenever(root.get<String>(validEqualitySearchCriterion.key)).thenReturn(key)
            whenever(builder.equal(key, validEqualitySearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validEqualitySearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid like search criterion - when values processed - returns Predicate")
        fun testProcessValidLikeSearchCriterion() {
            whenever(root.get<String>(validLikeSearchCriterion.key)).thenReturn(key)
            whenever(builder.like(key, validLikeSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validLikeSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }
    }

    @Nested
    inner class Failure {
        @Test
        @DisplayName("given invalid search key - when values processed - throws IllegalArgumentException")
        fun testProcessInvalidKeySearchCriterion() {
            whenever(root.get<String>(invalidKeySearchCriterion.key)).thenThrow(IllegalArgumentException::class.java)

            Assertions.assertThrows(IllegalArgumentException::class.java) {
                val searchSpecification: SearchSpecification<T> = SearchSpecification(invalidKeySearchCriterion)
                searchSpecification.toPredicate(root, query, builder)
            }
        }

        @Test
        @DisplayName("given invalid search operation - when values processed - returns null")
        fun testProcessInvalidOperationSearchCriterion() {
            whenever(root.get<String>(invalidOperationSearchCriterion.key)).thenReturn(key)
            whenever(builder.equal(key, invalidOperationSearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(invalidOperationSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate.shouldBeNull()
        }
    }

}
