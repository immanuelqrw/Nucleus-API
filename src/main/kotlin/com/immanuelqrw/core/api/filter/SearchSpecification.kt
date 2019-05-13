package com.immanuelqrw.core.api.filter

import com.immanuelqrw.core.api.model.BaseEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Search Specification class
 */
open class SearchSpecification<T: BaseEntity>(searchCriterion: SearchCriterion) : Specification<T> {

    private val criterion: SearchCriterion = searchCriterion

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        val key: Path<String> = root.get(criterion.key)
        val operation = criterion.operation
        val value = criterion.value.toString()

        return when (operation) {
            ">" -> builder.greaterThanOrEqualTo(key, value)
            "<" -> builder.lessThanOrEqualTo(key, value)
            ":" -> builder.equal(key, criterion.value)
            "~" -> builder.like(key, value.replace("*", "%"))
            else -> null
        }
    }

}
