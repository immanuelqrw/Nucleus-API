package com.immanuelqrw.nucleus.core.api.filter

import org.springframework.data.jpa.domain.Specification

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import javax.persistence.criteria.*

class SearchSpecification<T: BaseEntity> : Specification<T> {

    private lateinit var criteria: SearchCriteria

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        val key: Path<String> = root.get(criteria.key)
        val operation = criteria.operation
        val value = criteria.value.toString()

        return  when (operation) {
            ">" -> builder.greaterThanOrEqualTo(key, value)
            "<" -> builder.lessThanOrEqualTo(key, value)
            ":" -> builder.equal(key, criteria.value)
            "~" -> builder.like(key, value.replace("*", "%"))
            else -> null
        }
    }
}