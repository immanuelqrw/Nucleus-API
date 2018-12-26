package com.immanuelqrw.core.api.filter

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.unit.filter.SearchSpecificationTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.persistence.criteria.*

/**
 * Unit tests for SolidSearchSpecification
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchSpecificationTest: SearchSpecificationTest<Solid>() {

    @Mock
    override lateinit var root: Root<Solid>

    @Mock
    override lateinit var query: CriteriaQuery<*>

    @Mock
    override lateinit var builder: CriteriaBuilder

    @Mock
    override lateinit var key: Path<String>

    @Mock
    override lateinit var validPredicate: Predicate

    override val validGreaterThanSearchCriterion = SearchCriterion("id", ">", 2)
    override val validLessThanSearchCriterion = SearchCriterion("id", "<", 2)
    override val validEqualitySearchCriterion = SearchCriterion("id", ":", 2)
    override val validLikeSearchCriterion = SearchCriterion("id", "~", 2)

    override val invalidKeySearchCriterion = SearchCriterion("ig", ":", 2)
    override val invalidOperationSearchCriterion = SearchCriterion("id", "^", 2)
}