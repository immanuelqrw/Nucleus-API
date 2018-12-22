package com.immanuelqrw.core.api.filter

import org.springframework.data.jpa.domain.Specification

import com.immanuelqrw.core.api.model.BaseEntity
import javax.persistence.criteria.*

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