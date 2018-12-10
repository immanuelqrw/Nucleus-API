package com.immanuelqrw.nucleus.core.api.filter

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import org.springframework.data.jpa.domain.Specification

class SearchSpecificationsBuilder<T : BaseEntity> {

    private val params: MutableList<SearchCriteria> = mutableListOf()

    fun with(key: String, operation: String, value: Any): SearchSpecificationsBuilder<T> {
        params.add(SearchCriteria(key, operation, value))
        return this
    }

    fun build(): Specification<T>? {
        if (params.isEmpty()) {
            return null
        }

        // FIXME Issue with generating specifications from criteria
        val specifications: List<Specification<T>> = params.map {
            SearchSpecification<T>()
        }



        return specifications.reduce { groupedSpecification, specification ->
            groupedSpecification.and(specification)
        }
    }
}