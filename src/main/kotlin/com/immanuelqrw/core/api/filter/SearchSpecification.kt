package com.immanuelqrw.core.api.filter

import com.immanuelqrw.core.entity.Entityable
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Search Specification class
 */
open class SearchSpecification<T: Entityable>(searchCriterion: SearchCriterion) : Specification<T> {

    private val criterion: SearchCriterion = searchCriterion

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        val key: Path<String> = root.get(criterion.key)
        val operation = criterion.operation
        val value: String? = criterion.value.let { value ->
            val regexValue = value.toString()

            if (regexValue.isNotBlank()) {
                regexValue
            } else {
                null
            }
        }

        return when (operation) {
            ">" -> builder.greaterThanOrEqualTo(key, value!!)
            "<" -> builder.lessThanOrEqualTo(key, value!!)
            ":" -> builder.equal(key, criterion.value)
            "~" -> builder.like(key, value!!.replace("*", "%"))
            "!" -> builder.notEqual(key, criterion.value)
            "^" -> builder.notLike(key, value!!.replace("*", "%"))
            else -> null
        }
    }

}
