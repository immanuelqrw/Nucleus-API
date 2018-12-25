package com.immanuelqrw.core.api.service

import com.immanuelqrw.core.api.filter.SearchSpecificationsBuilder
import com.immanuelqrw.core.api.model.BaseEntity
import org.springframework.data.jpa.domain.Specification
import java.util.regex.Pattern

/**
 * Service which parses search query parameter
 */
abstract class SearchService<T : BaseEntity> {

    /**
     * Parses search query parameter to generate Specification
     *
     * @param search Query parameter to parse
     * @return Specification
     */
    fun generateSpecification(search: String?): Specification<T>? {

        val builder = SearchSpecificationsBuilder<T>()

        // TODO Move Pattern to configuration file
        val pattern = Pattern.compile("(\\w+?)([~:<>])(\\w+?);")
        val matcher = pattern.matcher("$search;")

        while (matcher.find()) {
            val key: String = matcher.group(1)
            val operation: String = matcher.group(2)
            val value: Any = matcher.group(3)

            builder.with(key, operation, value)
        }

        return builder.build()
    }

}