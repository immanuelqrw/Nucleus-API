package com.immanuelqrw.nucleus.core.api.filter

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import org.springframework.data.jpa.domain.Specification

class SearchSpecificationsBuilder<T : BaseEntity> {

    private val params: MutableList<SearchCriterion> = mutableListOf()

    fun with(key: String, operation: String, value: Any): SearchSpecificationsBuilder<T> {
        params.add(SearchCriterion(key, operation, value))
        return this
    }

    fun build(): Specification<T>? {
        if (params.isEmpty()) {
            return null
        }

        val specifications: List<Specification<T>> = params.map { searchCriterion ->
            SearchSpecification<T>(searchCriterion)
        }

        return specifications.reduce { groupedSpecification, specification ->
            groupedSpecification.and(specification)
        }
    }
}