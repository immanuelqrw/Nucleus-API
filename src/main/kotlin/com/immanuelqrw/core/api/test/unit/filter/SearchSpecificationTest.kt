package com.immanuelqrw.core.api.test.unit.filter

import com.immanuelqrw.core.api.filter.SearchCriterion
import com.immanuelqrw.core.api.filter.SearchSpecification
import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.*
import org.mockito.Mockito.`when`
import javax.persistence.criteria.*

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
        fun `given valid greater than search criterion - when values processed - returns Predicate`() {
            `when`(root.get<String>(validGreaterThanSearchCriterion.key)).thenReturn(key)
            `when`(builder.greaterThanOrEqualTo(key, validGreaterThanSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validGreaterThanSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        fun `given valid less than search criterion - when values processed - returns Predicate`() {
            `when`(root.get<String>(validLessThanSearchCriterion.key)).thenReturn(key)
            `when`(builder.lessThanOrEqualTo(key, validLessThanSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validLessThanSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        fun `given valid equality search criterion - when values processed - returns Predicate`() {
            `when`(root.get<String>(validEqualitySearchCriterion.key)).thenReturn(key)
            `when`(builder.equal(key, validEqualitySearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validEqualitySearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        fun `given valid like search criterion - when values processed - returns Predicate`() {
            `when`(root.get<String>(validLikeSearchCriterion.key)).thenReturn(key)
            `when`(builder.like(key, validLikeSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validLikeSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `given invalid search key - when values processed - throws IllegalArgumentException`() {
            `when`(root.get<String>(invalidKeySearchCriterion.key)).thenThrow(IllegalArgumentException::class.java)

            Assertions.assertThrows(IllegalArgumentException::class.java) {
                SearchSpecification<T>(invalidKeySearchCriterion)
            }
        }

        @Test
        fun `given invalid search operation - when values processed - returns null`() {
            `when`(root.get<String>(invalidOperationSearchCriterion.key)).thenReturn(key)
            `when`(builder.equal(key, invalidOperationSearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(invalidOperationSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate.shouldBeNull()
        }
    }
}